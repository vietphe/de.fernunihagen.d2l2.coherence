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

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

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
		System.out.println("---Printing essay: "+essayText);
		Collection<CoreferenceEntity> ces = JCasUtil.select(aJCas, CoreferenceEntity.class);
		Collection<CFEntity> cfes = JCasUtil.select(aJCas, CFEntity.class);
		
		//Use Coreference Resolution
		for(CFEntity cFEntity: cfes) {
			if(cFEntity.getSentenceIndex()!=1) {
				for(CoreferenceEntity coreferenceEntity: ces ) {
					if((cFEntity.getBeginPosition()== coreferenceEntity.getBeginPosition())&&(cFEntity.getEndPosition()==coreferenceEntity.getEndPosition())) {
						cFEntity.setName(coreferenceEntity.getFirstMention());						
					}
				}
			}
			
		}
		//convert cfes to ArrayList
		ArrayList<CFEntity> cfesList = new ArrayList<>();
		for (CFEntity entity : cfes) {
			cfesList.add(entity);
		}
		//get the last sentence index
		int lastSentence = cfesList.get(cfesList.size()-1).getSentenceIndex();
//		System.out.println(lastSentence);
		// get CF for every sentence
		Map<Integer,ArrayList<CFEntity>> cfeMap = new HashMap<>();
		for (int i = 1; i <= lastSentence; i++) {
			ArrayList<CFEntity> aList = new ArrayList<>();		
			for (CFEntity entity : cfes) {
				if (entity.getSentenceIndex()==i) {
					aList.add(entity);
				}
			}
			cfeMap.put(i, aList);
		}
		for (Map.Entry<Integer, ArrayList<CFEntity>> entry : cfeMap.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<CFEntity> val = entry.getValue();
//			System.out.print(key);
			for (CFEntity cfEntity : val) {
//				System.out.print(" "+cfEntity.getName());
			}
//			System.out.println();
			
		}
		
		// get CP for every sentence
		ArrayList<CFEntity> cPList = new ArrayList<>();
		int temp = 1;
		for (CFEntity entity : cfes) {
			if (entity.getSentenceIndex()==temp) {
				cPList.add(entity);
				temp++;
				continue;
			}
		}
		ArrayList<Object[]> CfAndCpList = new ArrayList<>();
		for (int i = 1; i < cfeMap.size()+1; i++) {
			CfAndCpList.add(new Object[] {i,cfeMap.get(i),cfeMap.get(i).get(0).getName()});
		}	
		ArrayList<Object[]> CpAndCbList = new ArrayList<>();
		//add Cp and Cp of first sentence to the list
		CpAndCbList.add(new Object[] {1,cfeMap.get(1).get(0).getName(),"undefined"});
		for (int i = 1; i < CfAndCpList.size(); i++) {
			String cB = "undefined";
			String cP = (String) CfAndCpList.get(i)[2];			
			for (CFEntity cfEntity: (ArrayList<CFEntity>)CfAndCpList.get(i)[1]) {
				if(CfAndCpList.get(i-1)[2].equals(cfEntity.getName())) {
					cB=cfEntity.getName();
					cP = (String) CfAndCpList.get(i)[2];
				}
			}
			 
			CpAndCbList.add(new Object[] {i+1,cP,cB});			
		}
//		for(Object[] o : CpAndCbList) {
//			System.out.print(o[0]+" ");
//			System.out.print(o[1]+" ");
//			System.out.print(o[2]+" ");
//			System.out.println();
//		}
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


