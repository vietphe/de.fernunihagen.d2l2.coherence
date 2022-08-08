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
import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
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
		ArrayList<CorefEntity> cesCopy = new ArrayList<>();
		for (CoreferenceEntity e: ces) {
			cesCopy.add(new CorefEntity(e.getBeginPosition(), e.getEndPosition(), e.getName(), e.getId(), e.getFirstMention()));
		}
		// a list that contain only coreference entities with POS as pronoun
		ArrayList<CorefEntity> corefEntities = new ArrayList<>();
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token t : tokens) {
			if(t.getPos().getCoarseValue().equals("PRON")) {
				corefEntities.add(new CorefEntity(t.getBegin(),t.getEnd(),t.getCoveredText(),"",""));
			}
		}
		for (CorefEntity e : corefEntities) {
			for (CorefEntity e1 : cesCopy) {
				if (e.equals(e1)) {
					e.setId(e1.getId());
					e.setFirstMention(e1.getFirstMention());
				}
			}
		}
		for (CorefEntity e : corefEntities) {
			if (e.getFirstMention().equals("")) {
				e.setFirstMention(e.getName());
			}
		}
//		System.out.println("-------------------------------");
//		for (CorefEntity e : corefEntities) {
//			System.out.println(e.getName()+" " +e.getId()+ " "+ e.getFirstMention());
//		}
//		System.out.println("------------------");
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
						System.out.print(e.getName()+" ("+e.getDependencyType()+")"+" - ");
					}else {
						System.out.print(e.getName()+" ("+e.getDependencyType()+")"+ "("+e.getFirstMention()+") "+" - ");
					}
					
				}
			}
			System.out.println();
		}
		System.out.println();
		
		//convert cfes to ArrayList
		ArrayList<CFEntity> cfesList = new ArrayList<>();
		for (CFEntity entity : cfes) {
			cfesList.add(entity);
		}
		//get the last sentence index
		int lastSentence = cfesList.get(cfesList.size()-1).getSentenceIndex();
//		System.out.println(lastSentence);
		// get CF for every sentence
		Map<Integer,ArrayList<CFEntityWithCoref>> cfeMap = new HashMap<>();
		for (int i = 1; i <= lastSentence; i++) {
			ArrayList<CFEntityWithCoref> aList = new ArrayList<>();		
			for (CFEntityWithCoref entity : cFEntityWithCoref) {
				if (entity.getSentenceIndex()==i) {
					aList.add(entity);
				}
			}
			cfeMap.put(i, aList);
		}
//		for (Map.Entry<Integer, ArrayList<CFEntity>> entry : cfeMap.entrySet()) {
//			Integer key = entry.getKey();
//			ArrayList<CFEntity> val = entry.getValue();
//			System.out.print(key);
//			for (CFEntity cfEntity : val) {
//				System.out.print(" "+cfEntity.getName());
//			}
//			System.out.println();
//			
//		}
				
		ArrayList<Object[]> CpAndCbList = new ArrayList<>();
		//add Cp and Cb for first sentence
		CpAndCbList.add(new Object[] {1,cfeMap.get(1).get(0).getName(),"undefined"});
		//Logic for calculation of Cbs
		for (int i = 1; i < cfeMap.size(); i++) {
			String cB = "undefined";
			String cP = "";
			if (cfeMap.get(i+1).get(0).getFirstMention().equals("")) {
				cP = cfeMap.get(i+1).get(0).getName();
			}else {
				cP = cfeMap.get(i+1).get(0).getFirstMention();
			}
			ArrayList<CFEntityWithCoref> cFOfActualSentence = cfeMap.get(i+1);
			ArrayList<CFEntityWithCoref> cFOfPreviousSentence = cfeMap.get(i);
			innerloop1:
			for (CFEntityWithCoref e1 : cFOfPreviousSentence) {
				String e1Name = "";
				if (e1.getFirstMention().equals("")) {
					e1Name = e1.getName();
				}
				else {
					e1Name = e1.getFirstMention();
				}
				
				innerloop2:
				for (CFEntityWithCoref e2 : cFOfActualSentence) {
					
					String e2Name = "";
					
					if (e2.getFirstMention().equals("")) {
						e2Name = e2.getName();
					}
					else {
						e2Name = e2.getFirstMention();
					}
					if (e1Name.equals(e2Name)&&!e1Name.toLowerCase().equals("it")&&!e1Name.toLowerCase().equals("this")&&!e1Name.toLowerCase().equals("that")
							&&!e1Name.toLowerCase().equals("something")) { //to eliminate the case that "it" and "this" don't refer to anything but the words are the same
						cB = e2Name;
						break innerloop1;
					}
				}
			}
			CpAndCbList.add(new Object[] {i+1,cP,cB});
		}
		System.out.println("Cp and Cb: ");
		for(Object[] o : CpAndCbList) {
			System.out.print("["+o[0]+"]: ");
			System.out.print(o[1]+" ");
			System.out.print(o[2]+" ");
			System.out.println();
		}
		System.out.println();
		ArrayList<String> transitions = new ArrayList<>();		
		for (int i = 1; i < CpAndCbList.size(); i++) {
			Transition transition = new Transition(aJCas);
			transition.setSentenceIndex(i+1);
			if(CpAndCbList.get(i-1)[2].equals("undefined")||CpAndCbList.get(i)[2].equals(CpAndCbList.get(i-1)[2])) {
				if(CpAndCbList.get(i)[2].equals(CpAndCbList.get(i)[1])) {
					transition.setName("Continue");
					transitions.add("Continue");
				}else {
					transitions.add("Retain");
					transition.setName("Retain");
				}
			}
			
			if(!CpAndCbList.get(i)[2].equals(CpAndCbList.get(i-1)[2])&&!CpAndCbList.get(i-1)[2].equals("undefined")) {
				if(CpAndCbList.get(i)[2].equals(CpAndCbList.get(i)[1])) {
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


