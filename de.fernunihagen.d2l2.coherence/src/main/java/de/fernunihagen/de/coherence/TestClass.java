package de.fernunihagen.de.coherence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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


public class TestClass extends JCasAnnotator_ImplBase {
	
	
	@Override
	public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);	    
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
		String content = meta.getDocumentTitle();
		System.out.println("---------Printing essay-----------: "+content);		
		int sentenceIndex=1;
		
		Collection<CoreferenceEntity> ces = JCasUtil.select(aJCas, CoreferenceEntity.class);
		ArrayList<Object[]> CfAndCpList = new ArrayList<>();
		for (final Sentence sentence : JCasUtil.select(aJCas, Sentence.class)) {
			System.out.println("----Sentence "+sentenceIndex +"---: "+sentence.getCoveredText() );
			ArrayList<ForwardLookingCenterEntity> possibleCandidate = new ArrayList<ForwardLookingCenterEntity>();
			final Collection<Token> tokens = JCasUtil.selectCovered(aJCas, Token.class, sentence);
			for (Token t: tokens) {
				if(t.getPos().getCoarseValue()!=null) {
					if(t.getPos().getCoarseValue().equals("PROPN")||t.getPos().getCoarseValue().equals("NOUN")||t.getPos().getCoarseValue().equals("PRON")) {
						ForwardLookingCenterEntity cfe = new ForwardLookingCenterEntity();
						cfe.setName(t.getCoveredText());
						cfe.setBegin(t.getBegin());
						cfe.setEnd(t.getEnd());
						possibleCandidate.add(cfe);
//						System.out.println(t.getCoveredText() + " " + t.getPos().getCoarseValue() + " " + t.getLemmaValue());				
					}
				}
//				System.out.println(t.getCoveredText() + " " + t.getPos().getCoarseValue() + " " + t.getLemmaValue());
			}
			for(ForwardLookingCenterEntity cfEntity:possibleCandidate) {
//				System.out.print(cfEntity.getName()+" ");
			}
//			System.out.println();
			final Collection<Dependency> dependencies = JCasUtil.selectCovered(aJCas, Dependency.class, sentence);
			
			//create a list of forward-looking centers
			ArrayList<ForwardLookingCenterEntity> forwardLookingCenters = new ArrayList<>();
			//get Dependency Type
			for(ForwardLookingCenterEntity cfe : possibleCandidate) {
				for (Dependency dep : dependencies){
					if(!dep.getDependencyType().equals("compound")&&!dep.getDependencyType().equals("punct")) {
						if(cfe.getName().equals(dep.getDependent().getCoveredText())&&cfe.getBegin()==dep.getDependent().getBegin()) {
							cfe.setDependencyType(dep.getDependencyType());
							forwardLookingCenters.add(cfe);
							break;
						}				
					}
					
				}	
			}		
			//Named Entity bind together(90-136)
			ArrayList<ForwardLookingCenterEntity> listNER = new ArrayList<>();
			 
			for (Dependency dep : dependencies){
				if(!dep.getDependencyType().equals("punct")) {					
					if(dep.getDependencyType().equals("compound")) {
						ForwardLookingCenterEntity cfe1 = new ForwardLookingCenterEntity();
						cfe1.setName(dep.getDependent().getCoveredText());
						cfe1.setBegin(dep.getDependent().getBegin());
						cfe1.setEnd(dep.getDependent().getEnd());
						cfe1.setDependencyType(dep.getDependencyType());
						listNER.add(cfe1);
						ForwardLookingCenterEntity cfe2 = new ForwardLookingCenterEntity();
						cfe2.setName(dep.getGovernor().getCoveredText());
						cfe2.setBegin(dep.getGovernor().getBegin());
						cfe2.setEnd(dep.getGovernor().getEnd());
						cfe2.setDependencyType(dep.getDependencyType());
						listNER.add(cfe2);
					}			 
//					System.out.println(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType() + " " + dep.getDependent().getCoveredText());	
				}
					
			}
			ArrayList<ForwardLookingCenterEntity> listNERCopy = new ArrayList<ForwardLookingCenterEntity>();
			for (ForwardLookingCenterEntity string : listNER) {
				listNERCopy.add(string);
			}
			//delete repeated CFEntities in listNERCopy
			for (int i = listNERCopy.size()-2; i >=0 ; i--) {
				if(listNERCopy.get(listNERCopy.size()-1).getName().equals(listNERCopy.get(i).getName())) {
					listNER.remove(i);
				}
			}
			//get dependencyType for NER
			String dependencyTypeOfNER = "";
			for (Dependency dep : dependencies){
				if(!listNER.isEmpty()) {
					if(listNER.get(listNER.size()-1).getName().equals(dep.getDependent().getCoveredText()))
						dependencyTypeOfNER = dep.getDependencyType();
				}
				
			}
			//bind together
			String namedEntity = "";
			for (ForwardLookingCenterEntity cfe : listNER) {
				namedEntity += cfe.getName() +" ";
			}
			ArrayList<ForwardLookingCenterEntity> forwardLookingCentersCopie = new ArrayList<>();
			for(ForwardLookingCenterEntity cfe : forwardLookingCenters) {
				forwardLookingCentersCopie.add(cfe);
			}
			//remove CFEntity with false dependencyType in forwardLookingCenters
			for (ForwardLookingCenterEntity cfe : forwardLookingCentersCopie) {
				for(ForwardLookingCenterEntity cfe2 : listNER) {
					if(cfe.getName().equals(cfe2.getName())) {
						forwardLookingCenters.remove(cfe);
					}
				}
			}
			ForwardLookingCenterEntity cfe = new ForwardLookingCenterEntity();
			cfe.setName(namedEntity);
			cfe.setDependencyType(dependencyTypeOfNER);
			//add the new solved CFEntity to forwardLookingCenters
			forwardLookingCenters.add(cfe);
			
			/*
			 * // solve the problem "and" and "or" Set<String> setConj = new HashSet<>();
			 * for (Dependency dep : dependencies){
			 * if(!dep.getDependencyType().equals("punct")) {
			 * 
			 * if(dep.getDependencyType().contains("conj")) {
			 * setConj.add(dep.getDependent().getCoveredText());
			 * setConj.add(dep.getGovernor().getCoveredText()); } } } //convert set to
			 * arraylist ArrayList<String> listConj = new ArrayList<>(); for(String str :
			 * setConj) { listConj.add(str); } String dependencyTypeForConj = ""; String
			 * firstElementOfConj = ""; for (Dependency dep : dependencies){
			 * if(dep.getDependencyType().equals("conj")){
			 * firstElementOfConj=dep.getGovernor().getCoveredText(); }
			 * 
			 * } for (Dependency dep : dependencies){
			 * if(dep.getDependent().getCoveredText().equals(firstElementOfConj) ){
			 * dependencyTypeForConj = dep.getDependencyType(); } } for (CFEntity cfe2 :
			 * forwardLookingCentersCopie) { for(String str : listConj) {
			 * if(cfe2.getName().equals(str)) { forwardLookingCenters.remove(cfe2); } } }
			 * for(String str : listConj) { CFEntity cfe2 = new CFEntity();
			 * cfe2.setName(str); cfe2.setDependencyType(dependencyTypeForConj);
			 * forwardLookingCenters.add(cfe2); }
			 */
			 
			//solve the subordinate clause
			String rootVerb = "";
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("root")){
					rootVerb = dep.getDependent().getCoveredText();
					break;
				}
			}
			ArrayList<String> listFalseNsubj = new ArrayList<>();
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("nsubj")&&!dep.getGovernor().getCoveredText().equals(rootVerb)) {				
					listFalseNsubj.add(dep.getDependent().getCoveredText());
					
					
				}
			}
			for (ForwardLookingCenterEntity cfe2 : forwardLookingCentersCopie) {
				for(String str : listFalseNsubj) {
					if(cfe2.getName().equals(str)) {
						forwardLookingCenters.remove(cfe2);
					}
				}
			}
			for(String str : listFalseNsubj) {
				ForwardLookingCenterEntity cfe2 = new ForwardLookingCenterEntity();
				cfe2.setName(str);	
				cfe2.setDependencyType("other");
				forwardLookingCenters.add(cfe2);
			}
			ArrayList<ForwardLookingCenterEntity> possibleSubject = new ArrayList<>();
			ArrayList<ForwardLookingCenterEntity> possibleObject = new ArrayList<>();
			ArrayList<ForwardLookingCenterEntity> possibleOthers = new ArrayList<>();
			for(ForwardLookingCenterEntity cfe2 : forwardLookingCenters) {
				if(cfe2.getDependencyType().contains("subj")) {
					possibleSubject.add(cfe2);
				}else if(cfe2.getDependencyType().contains("obj")) {
					possibleObject.add(cfe2);	
				}else{
					possibleOthers.add(cfe2);				
				}
			}
			//CF of current sentence in correct order
			ArrayList<ForwardLookingCenterEntity> cF = new ArrayList<>();
			cF.addAll(possibleSubject);
			cF.addAll(possibleObject);
			cF.addAll(possibleOthers);
			
			//remove duplicate entity in cF
			ArrayList<ForwardLookingCenterEntity> cFFinal = new ArrayList<>();
			for(ForwardLookingCenterEntity centerEntity : cF) {
				if(!cFFinal.contains(centerEntity)) {
					cFFinal.add(centerEntity);
				}
			}
			
			//add to CFEntity UIMA type system
			for(ForwardLookingCenterEntity cfEntity: cFFinal) {
				if(!cfEntity.getName().equals("")) {
					CFEntity entity = new CFEntity(aJCas);
					entity.setSentenceIndex(sentenceIndex);
					entity.setName(cfEntity.getName());
					entity.setBeginPosition(cfEntity.getBegin());
					entity.setEndPosition(cfEntity.getEnd());
					entity.addToIndexes();
				}
				
			}
			
			//Use Coreference Resolution
			ArrayList<ForwardLookingCenterEntity> cFWithCoref = new ArrayList<>();
			//copy CF list
			for(ForwardLookingCenterEntity cfEntity: cF) {
				cFWithCoref.add(cfEntity);
			}
			if(sentenceIndex != 1) {
				for(ForwardLookingCenterEntity cFEntity: cFWithCoref) {
					for(CoreferenceEntity coreferenceEntity: ces ) {
						if((cFEntity.getBegin()== coreferenceEntity.getBeginPosition())&&(cFEntity.getEnd()==coreferenceEntity.getEndPosition())) {
							cFEntity.setName(coreferenceEntity.getFirstMention());
							
						}
					}
				}
			}
			/*
			 * System.out.print("CF with Coref = "); for(CFEntity cfEntity: cFWithCoref) {
			 * System.out.print(cfEntity.getName()+" "); } System.out.println();
			 */
			
			//get Cp from Cf
			String cP = "";
			cP = cF.get(0).getName();			
			CfAndCpList.add(new Object[]{sentenceIndex,cF,cP});			
			sentenceIndex++;
		}
		
		ArrayList<Object[]> CbAndCpList = new ArrayList<>();
		//first sentence
		CbAndCpList.add(new Object[] {1,(String)CfAndCpList.get(0)[2],"undefined"});
		
		for (int i = 1; i < CfAndCpList.size(); i++) {
			String cB = "undefined";
			String cP = (String) CfAndCpList.get(i)[2];			
			for (ForwardLookingCenterEntity cfEntity: (ArrayList<ForwardLookingCenterEntity>)CfAndCpList.get(i)[1]) {
				if(CfAndCpList.get(i-1)[2].equals(cfEntity.getName())) {
					cB=cfEntity.getName();
					cP = (String) CfAndCpList.get(i)[2];
				}
			}
			 
		
			/*
			 * for (int j = 0; j < CfAndCpList.size(); j++) {
			 * if(CfAndCpList.get(j)[2]==CfAndCpList.get(i)[2]) { cB = (String)
			 * CfAndCpList.get(j)[2]; cP = (String) CfAndCpList.get(j)[2]; } }
			 */
			CbAndCpList.add(new Object[] {i+1,cP,cB});			
		}
		for(Object[] o : CbAndCpList) {
			System.out.print(o[0]+" ");
			System.out.print(o[1]+" ");
			System.out.print(o[2]+" ");
			System.out.println();
		}
		
		ArrayList<Object[]> CbAndCpListCopy = new ArrayList<>();
		for (Object[] objects : CbAndCpList) {
			CbAndCpListCopy.add(objects);
		}
		//calculate Transition
//		ArrayList<Transition> transitions = new ArrayList<>();
		ArrayList<String> transitions = new ArrayList<>();		
		for (int i = 1; i < CbAndCpList.size(); i++) {
			Transition transition = new Transition(aJCas);
			transition.setSentenceIndex(i+1);
			if(CbAndCpList.get(i-1)[2].equals("undefined")||CbAndCpList.get(i)[2].equals(CbAndCpList.get(i-1)[2])) {
				if(CbAndCpList.get(i)[2].equals(CbAndCpList.get(i)[1])) {
					transition.setName("Continue");
					transitions.add("Continue");
				}else {
					transitions.add("Retain");
					transition.setName("Retain");
				}
			}
			
			if(!CbAndCpList.get(i)[2].equals(CbAndCpList.get(i-1)[2])&&!CbAndCpList.get(i-1)[2].equals("undefined")) {
				if(CbAndCpList.get(i)[2].equals(CbAndCpList.get(i)[1])) {
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
		
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
//		export(output.toString(), outputFile);
		
		
	}
}


