package de.fernunihagen.de.coherence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.classes.CFEntityWithCoref;
import de.fernunihagen.d2l2.coherence.classes.CorefEntity;
import de.fernunihagen.d2l2.coherence.classes.ForwardLookingCenterEntity;
import de.fernunihagen.d2l2.coherence.types.CB;
import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CP;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.fernunihagen.d2l2.coherence.types.Entity;
import de.fernunihagen.d2l2.coherence.types.Transition;
import de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;
import javassist.runtime.Cflow;


public class TransitionAnnotator extends JCasAnnotator_ImplBase {
	
	
	@Override
	public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);	    
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String essayText = aJCas.getDocumentText();
		DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
		
		System.out.println("---Printing essay " +meta.getDocumentId()+": "+essayText );
		
		Collection<CoreferenceEntity> ces = JCasUtil.select(aJCas, CoreferenceEntity.class);
		ArrayList<CorefEntity> corefEntities = new ArrayList<>();
		for (CoreferenceEntity e: ces) {
			CorefEntity corefEntity = new CorefEntity(e.getBeginPosition(), e.getEndPosition(), e.getName(), e.getId(), e.getFirstMention());
			if(!corefEntities.contains(corefEntity)) {
				corefEntities.add(corefEntity);
			}
			
		}

		Collection<CFEntity> cfes = JCasUtil.select(aJCas, CFEntity.class);
		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		int numOfSentences = sentences.size();
//		//printing CFs
//		System.out.println("CFs: ");
//		for (int i = 1; i <= numOfSentences; i++) {
//			System.out.print("CF of ["+i+"]: ");
//			for (CFEntity e : cfes) {
//				if(e.getSentenceIndex() == i) {
//					System.out.print(e.getName()+" ("+e.getDependencyType()+")"+" - ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		// match CFEntity and CorefEntity
		ArrayList<CFEntityWithCoref> cFEntityWithCoref = new ArrayList<>();
		for (CFEntity e : cfes) {
			CFEntityWithCoref e1 = new CFEntityWithCoref(e.getSentenceIndex(), e.getBeginPosition(),e.getEndPosition(),e.getName(),e.getDependencyType(),"","");
			cFEntityWithCoref.add(e1);
		}
		
		for(CFEntityWithCoref e: cFEntityWithCoref) {			
			for(CorefEntity coreferenceEntity: corefEntities ) {
				if((e.getBegin()== coreferenceEntity.getBegin())&&(e.getEnd()==coreferenceEntity.getEnd())) {
					e.setId(coreferenceEntity.getId());
					e.setFirstMention(coreferenceEntity.getFirstMention());
				}
			}
			
		}
		
		System.out.println("CFs with Coref: ");
		for (int i = 1; i <= numOfSentences; i++) {
			System.out.print("CF of ["+i+"]: ");
			for (CFEntityWithCoref e : cFEntityWithCoref) {
				if(e.getSentenceIndex() == i) {
					if(e.getFirstMention().equals("")) {
						System.out.print(e.getName()+" ("+e.getId()+")"+" - ");
					}else {
						System.out.print(e.getName()+" ("+e.getId()+")"+ "("+e.getDependencyType()+") "+" - ");
					}
					
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//remove expletive "it", "this", "that"		
		for (Iterator iterator = cFEntityWithCoref.iterator(); iterator.hasNext();) {
			CFEntityWithCoref e = (CFEntityWithCoref) iterator.next();
			if((e.getName().toLowerCase().equals("it")||e.getName().toLowerCase().equals("that")||e.getName().toLowerCase().equals("this"))&& (e.getFirstMention().toLowerCase().equals(e.getName().toLowerCase())||e.getFirstMention().equals(""))) {
				iterator.remove();
			}
		}
				
//		System.out.println("CFs new: ");
//		for (int i = 1; i <= numOfSentences; i++) {
//			System.out.print("CF of ["+i+"]: ");
//			for (CFEntityWithCoref e : cFEntityWithCoref) {
//				if(e.getSentenceIndex() == i) {
//					if(e.getFirstMention().equals("")) {
//						System.out.print(e.getName()+" ("+e.getId()+")"+" - ");
//					}else {
//						System.out.print(e.getName()+" ("+e.getId()+")"+ "("+e.getFirstMention()+") "+" - ");
//					}
//					
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		//convert cfes to ArrayList
		ArrayList<CFEntity> cfesList = new ArrayList<>();
		for (CFEntity entity : cfes) {
			cfesList.add(entity);
		}
		//get the last sentence index for the case the last sentence has no entity
		int lastSentence = cfesList.get(cfesList.size()-1).getSentenceIndex();
		
//		System.out.println(lastSentence);
		// get CF for every sentence
		Map<Integer,ArrayList<CFEntityWithCoref>> cfeMap = new HashMap<>();
		for (int i = 1; i <= lastSentence; i++) {
			ArrayList<CFEntityWithCoref> aList = new ArrayList<>();		
			for (CFEntityWithCoref entity : cFEntityWithCoref) {
				if (entity.getSentenceIndex()==i) {
					//to remove repeating entity with same coref in a sentence
					if(entity.getFirstMention().equals("")) {
						aList.add(entity);
					} else {
						if(!aList.contains(entity)) {
							aList.add(entity);
						}
					}
				}
			}			
			cfeMap.put(i, aList);
		}
		
		for (Map.Entry<Integer, ArrayList<CFEntityWithCoref>> entry : cfeMap.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<CFEntityWithCoref> val = entry.getValue();
			System.out.print("["+key+"]: ");
			for (CFEntityWithCoref cfEntity : val) {
				System.out.print(" "+cfEntity.getName()+"("+cfEntity.getId()+")"+"("+cfEntity.getFirstMention()+")"+"-");
			}
			System.out.println();
			
		}
		//add to new uima-type  Entity  
		for (Map.Entry<Integer, ArrayList<CFEntityWithCoref>> entry : cfeMap.entrySet()) {
			
			Integer key = entry.getKey();
			ArrayList<CFEntityWithCoref> val = entry.getValue();
			for (CFEntityWithCoref cfEntity : val) {
				Entity entity = new Entity(aJCas);
				entity.setSentenceIndex(key);
				entity.setBeginPosition(cfEntity.getBegin());
				entity.setEndPosition(cfEntity.getEnd());
				entity.setName(cfEntity.getName());
				entity.setDependencyType(cfEntity.getDependencyType());
				entity.setCorefId(cfEntity.getId());
				entity.setFirstMention(cfEntity.getFirstMention());
				entity.addToIndexes();
			}
		}		
		//list of CPs
		Map<Integer,CFEntityWithCoref> cPs = new HashMap<>();
		for (Map.Entry<Integer, ArrayList<CFEntityWithCoref>> entry : cfeMap.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<CFEntityWithCoref> val = entry.getValue();
//			System.out.print(key);
			CFEntityWithCoref e = val.get(0);
			
			cPs.put(key, e);
		}
		//add to new uima-type CP  
		for (Map.Entry<Integer, CFEntityWithCoref> entry : cPs.entrySet()) {
			
			Integer key = entry.getKey();
			CFEntityWithCoref val = entry.getValue();			
			CP entity = new CP(aJCas);			
			entity.setSentenceIndex(key);
			entity.setBeginPosition(val.getBegin());
			entity.setEndPosition(val.getEnd());
			entity.setName(val.getName());
			entity.setDependencyType(val.getDependencyType());
			entity.setCorefId(val.getId());
			entity.setFirstMention(val.getFirstMention());
			entity.addToIndexes();
		}		
//		//Print CP
//		System.out.println("CPs: ");
//		for (Map.Entry<Integer, CFEntityWithCoref> entry : cPs.entrySet()) {
//			Integer key = entry.getKey();
//			CFEntityWithCoref val = entry.getValue();
//			System.out.print(key+"->");
//			System.out.println(val.getName()+"("+val.getId()+")");
//			
//		}
		
		
		//list of CBs
		Map<Integer,CFEntityWithCoref> cBs = new HashMap<>();
		CFEntityWithCoref undefinedEntity = new CFEntityWithCoref(-1,-1,-1,"undefined","","","");
		cBs.put(1, undefinedEntity);
		for (int i = 1; i < cfeMap.size(); i++) {
			CFEntityWithCoref cB = undefinedEntity;
			ArrayList<CFEntityWithCoref> cFOfCurentSentence = cfeMap.get(i+1);
			ArrayList<CFEntityWithCoref> cFOfPreviousSentence = cfeMap.get(i);
			loop1:
			for (CFEntityWithCoref e1 : cFOfPreviousSentence) {
				for (CFEntityWithCoref e2 : cFOfCurentSentence) {
					if(e1.equals(e2)&&!e1.getId().equals("")) {
						cB = e2;
						break loop1;
					}
				}
			}
			cBs.put(i+1, cB);
		}
		//add to new uima-type CP  
		for (Map.Entry<Integer, CFEntityWithCoref> entry : cBs.entrySet()) {			
			Integer key = entry.getKey();
			CFEntityWithCoref val = entry.getValue();			
			CB entity = new CB(aJCas);			
			entity.setSentenceIndex(key);
			entity.setBeginPosition(val.getBegin());
			entity.setEndPosition(val.getEnd());
			entity.setName(val.getName());
			entity.setDependencyType(val.getDependencyType());
			entity.setCorefId(val.getId());
			entity.setFirstMention(val.getFirstMention());
			entity.addToIndexes();
		}		
//		//Print CBs
//		System.out.println("CBs: ");
//		for (Map.Entry<Integer, CFEntityWithCoref> entry : cBs.entrySet()) {
//			Integer key = entry.getKey();
//			CFEntityWithCoref val = entry.getValue();
//			System.out.print(key+"->");
//			System.out.println(val.getName()+"("+val.getId()+")");
//			
//		}
		ArrayList<Object[]> CpAndCbList = new ArrayList<>();
		for (int i = 0; i < cPs.size(); i++) {
			CpAndCbList.add(new Object[] {i+1,cPs.get(i+1),cBs.get(i+1)});
		}
		
		System.out.println("Cp and Cb: ");
		for(Object[] o : CpAndCbList) {
			System.out.print("["+o[0]+"]: ");
			System.out.print((((CFEntityWithCoref) o[1]).getName()+"("+ ((CFEntityWithCoref) o[1]).getId()+")"+" "));
			System.out.print((((CFEntityWithCoref) o[2]).getName()+"("+((CFEntityWithCoref) o[2]).getId()+")"+" "));
			System.out.println();
		}
		System.out.println();
		
//		ArrayList<Object[]> CpAndCbList = new ArrayList<>();
//		//add Cp and Cb for first sentence
//		CpAndCbList.add(new Object[] {1,cfeMap.get(1).get(0).getName(),new CFEntityWithCoref()});
//		//Logic for calculation of Cbs
//		for (int i = 1; i < cfeMap.size(); i++) {
//			String cB = "undefined";
//			String cP = "";
//			if (cfeMap.get(i+1).get(0).getFirstMention().equals("")) {
//				cP = cfeMap.get(i+1).get(0).getName();
//			}else {
//				cP = cfeMap.get(i+1).get(0).getFirstMention();
//			}
//			ArrayList<CFEntityWithCoref> cFOfCurentSentence = cfeMap.get(i+1);
//			ArrayList<CFEntityWithCoref> cFOfPreviousSentence = cfeMap.get(i);
//			innerloop1:
//			for (CFEntityWithCoref e1 : cFOfPreviousSentence) {
//				String e1Name = "";
//				if (e1.getFirstMention().equals("")) {
//					e1Name = e1.getName();
//				}
//				else {
//					e1Name = e1.getFirstMention();
//				}
//				
//				innerloop2:
//				for (CFEntityWithCoref e2 : cFOfCurentSentence) {
//					
//					String e2Name = "";
//					
//					if (e2.getFirstMention().equals("")) {
//						e2Name = e2.getName();
//					}
//					else {
//						e2Name = e2.getFirstMention();
//					}
//					if (e1Name.equals(e2Name)&&!e1Name.toLowerCase().equals("it")&&!e1Name.toLowerCase().equals("this")&&!e1Name.toLowerCase().equals("that")
//							&&!e1Name.toLowerCase().equals("something")) { //to eliminate the case that "it" and "this" don't refer to anything but the words are the same
//						cB = e2Name;
//						break innerloop1;
//					}
//				}
//			}
//			CpAndCbList.add(new Object[] {i+1,cP,cB});
//		}
		
//		System.out.println("Cp and Cb: ");
//		for(Object[] o : CpAndCbList) {
//			System.out.print("["+o[0]+"]: ");
//			System.out.print(o[1]+" ");
//			System.out.print(o[2]+" ");
//			System.out.println();
//		}
//		System.out.println();
		ArrayList<String> transitions = new ArrayList<>();		
		for (int i = 1; i < CpAndCbList.size(); i++) {
			Transition transition = new Transition(aJCas);
			transition.setSentenceIndex(i+1);
			if(((CFEntityWithCoref) CpAndCbList.get(i-1)[2]).getName().equals("undefined")||(((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals(((CFEntityWithCoref)CpAndCbList.get(i-1)[2]).getId()))&&!((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals("")) {
				if(((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals(((CFEntityWithCoref) CpAndCbList.get(i)[1]).getId())&&!((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals("")) {
					transition.setName("Continue");
					transitions.add("Continue");
				}else {
					transitions.add("Retain");
					transition.setName("Retain");
				}
			}
			
			if(!((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals(((CFEntityWithCoref) CpAndCbList.get(i-1)[2]).getId())&&!((CFEntityWithCoref) CpAndCbList.get(i-1)[2]).getName().equals("undefined")) {
				if(((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals(((CFEntityWithCoref) CpAndCbList.get(i)[1]).getId())&& !((CFEntityWithCoref) CpAndCbList.get(i)[2]).getId().equals("") ) {
					transitions.add("Smooth-Shift");
					transition.setName("Smooth-Shift");
				}else {
					transitions.add("Rough-Shift");
					transition.setName("Rough-Shift");
				}
			}
			transition.addToIndexes();
		}
	}
}


