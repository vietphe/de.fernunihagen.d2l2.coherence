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

import de.fernunihagen.d2l2.coherence.classes.ForwardLookingCenterEntity;
import de.fernunihagen.d2l2.coherence.types.CFEntity;
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
		int sentenceIndex=1;		
		Collection<Dependency> deps = JCasUtil.select(aJCas, Dependency.class);
		for (Dependency dep : deps) {
//			System.out.println(dep.getGovernor().getCoveredText() +" "+dep.getDependencyType()+" "+dep.getDependent().getCoveredText());
		}
		for (final Sentence sentence : JCasUtil.select(aJCas, Sentence.class)) {
//			System.out.println("----Sentence "+sentenceIndex +"---: "+sentence.getCoveredText() );
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
						ForwardLookingCenterEntity cfe2 = new ForwardLookingCenterEntity();
						cfe2.setName(dep.getGovernor().getCoveredText());
						cfe2.setBegin(dep.getGovernor().getBegin());
						cfe2.setEnd(dep.getGovernor().getEnd());
						cfe2.setDependencyType("");
						if (!listNER.contains(cfe2)) {
							listNER.add(cfe2);
						}
						
						ForwardLookingCenterEntity cfe1 = new ForwardLookingCenterEntity();
						cfe1.setName(dep.getDependent().getCoveredText());
						cfe1.setBegin(dep.getDependent().getBegin());
						cfe1.setEnd(dep.getDependent().getEnd());
						cfe1.setDependencyType("");
						if (!listNER.contains(cfe1)) {
							listNER.add(cfe1);
						}
					}			 
//					System.out.println(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType() + " " + dep.getDependent().getCoveredText());	
				}
					
			}
			int begin = -1;
			int end = -1;
			if (!listNER.isEmpty()) {
				begin = listNER.get(1).getBegin();
				end = listNER.get(0).getEnd();
			}
			//get dependencyType for NER
			String dependencyTypeOfNER = "";
			outerloop1:
			for (Dependency dep : dependencies){
				if (!listNER.isEmpty()) {
					if (listNER.get(0).getName().equals(dep.getDependent().getCoveredText()) && listNER.get(0).getBegin() == dep.getDependent().getBegin()) {
						dependencyTypeOfNER = dep.getDependencyType();
						break outerloop1;
					}					
				}
				
			}
			//bind names together
			String name = "";
			if (!listNER.isEmpty()) {
				for (int i = 1; i < listNER.size(); i++) {
					name += listNER.get(i).getName()+" ";
				}
				name += listNER.get(0).getName();
			}			
			ArrayList<ForwardLookingCenterEntity> forwardLookingCentersCopie = new ArrayList<>();
			for (ForwardLookingCenterEntity cfe : forwardLookingCenters) {
				forwardLookingCentersCopie.add(cfe);
			}
			//remove CFEntity with false dependencyType in forwardLookingCenters
			for (ForwardLookingCenterEntity cfe : forwardLookingCentersCopie) {
				for (ForwardLookingCenterEntity cfe2 : listNER) {
					if (cfe.getName().equals(cfe2.getName()) && cfe.getBegin() == cfe2.getBegin()) {
						forwardLookingCenters.remove(cfe);
					}
				}
			}
			ForwardLookingCenterEntity cfe = new ForwardLookingCenterEntity(begin, end, name, dependencyTypeOfNER+"NER");			
			//add the new solved CFEntity to forwardLookingCenters
			forwardLookingCenters.add(cfe);
			
			
			// solve the problem "and" and "or" 
			//create a list that contains only name of CFEntity.
			ArrayList<String> forwardlookingCentersString = new ArrayList<>();
			for (ForwardLookingCenterEntity entity : forwardLookingCenters) {
				forwardlookingCentersString.add(entity.getName());
			}
			
			ArrayList<ForwardLookingCenterEntity> listConj = new ArrayList<>();
			for (Dependency dep : dependencies){
				if (!dep.getDependencyType().equals("punct")) {		  
					if (dep.getDependencyType().contains("conj")) {
						//check if entities are in forwardlookingcenters. because exp. against has dependency "conj" type too
						if (forwardlookingCentersString.contains(dep.getDependent().getCoveredText())) {
							ForwardLookingCenterEntity e1 = new ForwardLookingCenterEntity(dep.getDependent().getBegin(), dep.getDependent().getEnd(), dep.getDependent().getCoveredText(), "");
							ForwardLookingCenterEntity e2 = new ForwardLookingCenterEntity(dep.getGovernor().getBegin(), dep.getGovernor().getEnd(), dep.getGovernor().getCoveredText(), "");
							if (!listConj.contains(e2)) {
								listConj.add(e2);
							}
							if (!listConj.contains(e1)) {
								listConj.add(e1);
							}
						}
					  
					} 
				} 
			}

			String dependencyTypeForConj = ""; 
			ForwardLookingCenterEntity firstEntityOfConj = new ForwardLookingCenterEntity() ;
			if (!listConj.isEmpty()) {
				firstEntityOfConj= (ForwardLookingCenterEntity) listConj.get(0);
			}
			for (Dependency dep : dependencies){
				if (dep.getDependent().getCoveredText().equals(firstEntityOfConj.getName()) && dep.getDependent().getBegin() == firstEntityOfConj.getBegin()){
					dependencyTypeForConj = dep.getDependencyType();
					break;
				} 
			}
			//add dependency type for all entities in listConj
			//TODO: remove #
			for (ForwardLookingCenterEntity e : listConj) { 
				e.setDependencyType(dependencyTypeForConj+"#");			
			} 
			
			for (ForwardLookingCenterEntity cfe2 : forwardLookingCentersCopie) { 
				for (ForwardLookingCenterEntity e : listConj) {
					if (cfe2.getName().equals(e.getName())&& cfe2.getBegin()==e.getBegin()) { 
						forwardLookingCenters.remove(cfe2); 
					} 
				} 
			}
			for(ForwardLookingCenterEntity e : listConj) { 
			  forwardLookingCenters.add(e); 
			}
			 
			 
			//solve the subordinate clause
			String rootVerb = "";
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("root")){
					rootVerb = dep.getDependent().getCoveredText();
					break;
				}
			}
			ArrayList<ForwardLookingCenterEntity> listWrongNsubj = new ArrayList<>();
			for(Dependency dep : dependencies) {
				if(dep.getDependencyType().equals("nsubj")&&!dep.getGovernor().getCoveredText().equals(rootVerb)) {				
					ForwardLookingCenterEntity e = new ForwardLookingCenterEntity(dep.getDependent().getBegin(), dep.getDependent().getEnd(), dep.getDependent().getCoveredText(), "wrongNsub");
					if(!listWrongNsubj.contains(e)) {
						listWrongNsubj.add(e);
					}
					
				}
			}
			for (ForwardLookingCenterEntity cfe2 : forwardLookingCentersCopie) {
				for(ForwardLookingCenterEntity e : listWrongNsubj) {
					if(cfe2.getName().equals(e.getName())&& cfe2.getBegin()==e.getBegin()) {
						forwardLookingCenters.remove(cfe2);
					}
				}
			}
			for(ForwardLookingCenterEntity e : listWrongNsubj) {
				forwardLookingCenters.add(e);
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
					entity.setDependencyType(cfEntity.getDependencyType());
					entity.addToIndexes();
				}
				
			}
			sentenceIndex++;
		}
	}	
}


