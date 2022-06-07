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
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.classes.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;


public class CFAnnotator extends JCasAnnotator_ImplBase {
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
//			System.out.println("----Sentence "+sentenceIndex +"---: "+sentence.getCoveredText() );
			ArrayList<CFEntity> possibleCandidate = new ArrayList<CFEntity>();
			final Collection<Token> tokens = JCasUtil.selectCovered(aJCas, Token.class, sentence);
			for (Token t: tokens) {
				if(t.getPos().getCoarseValue()!=null) {
					if(t.getPos().getCoarseValue().equals("PROPN")||t.getPos().getCoarseValue().equals("NOUN")||t.getPos().getCoarseValue().equals("PRON")) {
						CFEntity cfe = new CFEntity();
						cfe.setName(t.getCoveredText());
						cfe.setBegin(t.getBegin());
						cfe.setEnd(t.getEnd());
						possibleCandidate.add(cfe);
//						System.out.println(t.getCoveredText() + " " + t.getPos().getCoarseValue() + " " + t.getLemmaValue());				
					}
				}
//				System.out.println(t.getCoveredText() + " " + t.getPos().getCoarseValue() + " " + t.getLemmaValue());
			}
			final Collection<Dependency> dependencies = JCasUtil.selectCovered(aJCas, Dependency.class, sentence);
			StringBuilder sb = new StringBuilder();	
			//create a list of forward-looking centers
			ArrayList<CFEntity> forwardLookingCenters = new ArrayList<>();
			//get Dependency Type
			for(CFEntity cfe : possibleCandidate) {
				for (Dependency dep : dependencies){
					if(!dep.getDependencyType().equals("compound")&&!dep.getDependencyType().equals("punct")) {
						if(cfe.getName().equals(dep.getDependent().getCoveredText())) {
							cfe.setDependencyType(dep.getDependencyType());
							forwardLookingCenters.add(cfe);
							break;
						}				
					}
					
				}	
			}		
			//Named Entity bind together(90-136)
			ArrayList<CFEntity> listNER = new ArrayList<>();
			
			for (Dependency dep : dependencies){
				if(!dep.getDependencyType().equals("punct")) {					
					if(dep.getDependencyType().equals("compound")) {
						CFEntity cfe1 = new CFEntity();
						cfe1.setName(dep.getDependent().getCoveredText());
						cfe1.setBegin(dep.getDependent().getBegin());
						cfe1.setEnd(dep.getDependent().getEnd());
						cfe1.setDependencyType(dep.getDependencyType());
						listNER.add(cfe1);
						CFEntity cfe2 = new CFEntity();
						cfe2.setName(dep.getGovernor().getCoveredText());
						cfe2.setBegin(dep.getGovernor().getBegin());
						cfe2.setEnd(dep.getGovernor().getEnd());
						cfe2.setDependencyType(dep.getDependencyType());
						listNER.add(cfe2);
					}			 
					sb.append(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType() + " " + dep.getDependent().getCoveredText()+"\n");		
					System.out.println(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType() + " " + dep.getDependent().getCoveredText());	
				}
					
			}
			ArrayList<CFEntity> listNERCopie = new ArrayList<CFEntity>();
			for (CFEntity string : listNER) {
				listNERCopie.add(string);
			}
			for (int i = listNERCopie.size()-2; i >=0 ; i--) {
				if(listNERCopie.get(listNERCopie.size()-1).getName().equals(listNERCopie.get(i).getName())) {
					listNER.remove(i);
				}
			}
			String dependencyTypeOfNER = "";
			for (Dependency dep : dependencies){
				if(!listNER.isEmpty()) {
					if(listNER.get(listNER.size()-1).getName().equals(dep.getDependent().getCoveredText()))
						dependencyTypeOfNER = dep.getDependencyType();
				}
				
			}
			String namedEntity = "";
			for (CFEntity cfe : listNER) {
				namedEntity += cfe.getName() +" ";
			}
			ArrayList<CFEntity> forwardLookingCentersCopie = new ArrayList<>();
			for(CFEntity cfe : forwardLookingCenters) {
				forwardLookingCentersCopie.add(cfe);
			}

			for (CFEntity cfe : forwardLookingCentersCopie) {
				for(CFEntity cfe2 : listNER) {
					if(cfe.getName().equals(cfe2.getName())) {
						forwardLookingCenters.remove(cfe);
					}
				}
			}
			CFEntity cfe = new CFEntity();
			cfe.setName(namedEntity);
			cfe.setDependencyType(dependencyTypeOfNER);
			//TODO: Hier muss noch die Begin und End von Token ergänzen
			forwardLookingCenters.add(cfe);
			
			
			// solve the problem "and" and "or" 
			Set<String> setConj = new HashSet<>();
			for (Dependency dep : dependencies){
				if(!dep.getDependencyType().equals("punct")) {
				  
					if(dep.getDependencyType().contains("conj")) {
						setConj.add(dep.getDependent().getCoveredText());
						setConj.add(dep.getGovernor().getCoveredText()); 
					} 
				} 
			} //convert set toarraylist 
				ArrayList<String> listConj = new ArrayList<>(); 
			for(String str : setConj) { 
				listConj.add(str); 
				} 
			String dependencyTypeForConj = ""; 
			String firstElementOfCompound = ""; 
			for (Dependency dep : dependencies){
				if(dep.getDependencyType().equals("conj")){ firstElementOfCompound=
				dep.getGovernor().getCoveredText(); 
			}
				  
				} 
			for (Dependency dep : dependencies){
				if(dep.getDependent().getCoveredText().equals(firstElementOfCompound) ){
				dependencyTypeForConj = dep.getDependencyType(); 
				} 
			} 
			for (CFEntity cfe2 : forwardLookingCentersCopie) { 
				for(String str : listConj) {
					if(cfe2.getName().equals(str)) { 
						forwardLookingCenters.remove(cfe2); 
						} 
				} 
			}
			for(String str : listConj) { 
				CFEntity cfe2 = new CFEntity();
				cfe2.setName(str); cfe2.setDependencyType(dependencyTypeForConj);
				forwardLookingCenters.add(cfe2); 
			}
			 
			//solve the subordinate clause
			String rootVerb = "";
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("root")){
					rootVerb = dep.getDependent().getCoveredText();
				}
			}
			ArrayList<String> listFalseNsubj = new ArrayList<>();
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("nsubj")&&!dep.getGovernor().getCoveredText().equals(rootVerb)) {				
					listFalseNsubj.add(dep.getDependent().getCoveredText());			
				}
			}
			for (CFEntity cfe2 : forwardLookingCentersCopie) {
				for(String str : listFalseNsubj) {
					if(cfe2.getName().equals(str)) {
						forwardLookingCenters.remove(cfe2);
					}
				}
			}
			for(String str : listFalseNsubj) {
				CFEntity cfe2 = new CFEntity();
				cfe2.setName(str);	
				cfe2.setDependencyType("other");
				forwardLookingCenters.add(cfe2);
			}
			ArrayList<CFEntity> possibleSubject = new ArrayList<>();
			ArrayList<CFEntity> possibleObject = new ArrayList<>();
			ArrayList<CFEntity> possibleOthers = new ArrayList<>();
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			for(CFEntity cfe2 : forwardLookingCenters) {
				if(cfe2.getDependencyType().contains("subj")) {
					sb1.append(cfe2.getName()+" ");
					possibleSubject.add(cfe2);
				}else if(cfe2.getDependencyType().contains("obj")) {
					sb2.append(cfe2.getName()+" ");
					possibleObject.add(cfe2);	
				}else{
					sb3.append(cfe2.getName()+" ");
					possibleOthers.add(cfe2);				
				}
			}
			//CF of current sentence
			ArrayList<CFEntity> cF = new ArrayList<>();
			cF.addAll(possibleSubject);
			cF.addAll(possibleObject);
			cF.addAll(possibleOthers);			
			//Use Coreference Resolution
			if(sentenceIndex != 1) {
				for(CFEntity cFEntity: cF) {
					for(CoreferenceEntity coreferenceEntity: ces ) {
						if((cFEntity.getBegin()== coreferenceEntity.getBegin())&&(cFEntity.getEnd()==coreferenceEntity.getEnd())) {
							cFEntity.setName(coreferenceEntity.getFirstMention());
							
						}
					}
				}
			}
			//get Cp from Cf
			String cP = "";
			cP = cF.get(0).getName();
			
			CfAndCpList.add(new Object[]{sentenceIndex,cF,cP});
			
			String result ="CF"+ " in sentence "+sentenceIndex+ " = "+ "{"+sb1.toString()+ " < " +sb2.toString()+" < "+sb3.toString()+"}";		
			System.out.println(result);
			sentenceIndex++;
		}
		//calculate CPs and CBs from CFs
		ArrayList<Object[]> CbAndCpList = new ArrayList<>();
		//for first sentence, CB is "undefined"
		CbAndCpList.add(new Object[] {1,(String)CfAndCpList.get(0)[2],"undefined"});
		
		for (int i = 1; i < CfAndCpList.size(); i++) {
			String cB = "undefined";
			String cP = (String) CfAndCpList.get(i)[2];			
			for (CFEntity cfEntity: (ArrayList<CFEntity>)CfAndCpList.get(i)[1]) {
				if(CfAndCpList.get(i-1)[2].equals(cfEntity.getName())) {
					cB=cfEntity.getName();
					cP = (String) CfAndCpList.get(i)[2];
				}
			}
			
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
			if(CbAndCpList.get(i-1)[2].equals("undefined")||CbAndCpList.get(i)[2].equals(CbAndCpList.get(i-1)[2])) {
				if(CbAndCpList.get(i)[2].equals(CbAndCpList.get(i)[1])) {
					transitions.add("Continue");
				}else {
					transitions.add("Retain");
				}
			}
			
			if(!CbAndCpList.get(i)[2].equals(CbAndCpList.get(i-1)[2])&&!CbAndCpList.get(i-1)[2].equals("undefined")) {
				if(CbAndCpList.get(i)[2].equals(CbAndCpList.get(i)[1])) {
					transitions.add("Smooth-Shift");
				}else {
					transitions.add("Rough-Shift");
				}
			}
		}
		for (String string : transitions) {
			System.out.print(string+"->");			
		}
		System.out.println();
		
		
		
	}
		
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
//		export(output.toString(), outputFile);
		
		
	}
  
}


