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
import de.fernunihagen.d2l2.coherence.classes.RootVerb;
import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
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
		Collection<CoreferenceEntity> coreferenceEntities = JCasUtil.select(aJCas, CoreferenceEntity.class);
		Collection<Dependency> deps = JCasUtil.select(aJCas, Dependency.class);
		for (Dependency dep : deps) {
//			System.out.println(dep.getGovernor().getCoveredText() +" "+dep.getDependencyType()+" "+dep.getDependent().getCoveredText());
		}
		for (final Sentence sentence : JCasUtil.select(aJCas, Sentence.class)) {
//			System.out.println("----Sentence "+sentenceIndex +"---: "+sentence.getCoveredText() );
			final Collection<Token> tokens = JCasUtil.selectCovered(aJCas, Token.class, sentence);
			for (Token t: tokens) {
//				System.out.println(t.getCoveredText() + " " + t.getPos().getCoarseValue() + " " + t.getLemmaValue());
			}
			final Collection<Dependency> dependencies = JCasUtil.selectCovered(aJCas, Dependency.class, sentence);
			// identify root verb
			RootVerb rv = new RootVerb();
			for (Dependency dep : dependencies) {
				if (dep.getDependencyType().equals("root") && dep.getDependent().getPos().getCoarseValue().equals("VERB")) { // because sometime "root" be a noun.
					rv.setBeginPosition(dep.getDependent().getBegin());
					rv.setEndPosition(dep.getDependent().getEnd());
					rv.setName(dep.getDependent().getCoveredText());
					break;
				}else {
					if (dep.getDependencyType().equals("root")){
						rv.setBeginPosition(dep.getDependent().getBegin());
						rv.setEndPosition(dep.getDependent().getEnd());
						rv.setName(dep.getDependent().getCoveredText());
						break;
					}
				}
			}
//			System.out.println("RV:" + rv.getName());
			//CFs of main clause
			ArrayList<ForwardLookingCenterEntity> cfOfMainClause = new ArrayList<>();
			for (Dependency dep : deps) {
				if(dep.getGovernor().getCoveredText().equals(rv.getName())&& dep.getGovernor().getBegin() == rv.getBeginPosition()) {
					if(dep.getDependent().getPos().getCoarseValue().equals("PROPN")||dep.getDependent().getPos().getCoarseValue().equals("NOUN")||dep.getDependent().getPos().getCoarseValue().equals("PRON")
							|| (dep.getDependent().getPos().getCoveredText().toLowerCase().equals("this") && dep.getDependencyType().contains("nsubj"))) {
						ForwardLookingCenterEntity e = new ForwardLookingCenterEntity(dep.getDependent().getBegin(), dep.getDependent().getEnd(), dep.getDependent().getCoveredText(), dep.getDependencyType()+"MAIN");						
						if(!cfOfMainClause.contains(e)) {
							cfOfMainClause.add(e);
						}
						
					}
						
				}
			}
			//create a list of forward-looking centers
			ArrayList<ForwardLookingCenterEntity> forwardLookingCenters = new ArrayList<>();
			for (Dependency dep : dependencies) {
				if(dep.getDependent().getPos().getCoarseValue().equals("PROPN")||dep.getDependent().getPos().getCoarseValue().equals("NOUN")||dep.getDependent().getPos().getCoarseValue().equals("PRON") || 
						(dep.getDependent().getPos().getCoveredText().toLowerCase().equals("this") && dep.getDependencyType().contains("nsubj")) || 
						(dep.getDependent().getPos().getCoveredText().toLowerCase().equals("that") && dep.getDependencyType().contains("nsubj")) ) { //exception for this, that as nsubj
					ForwardLookingCenterEntity e = new ForwardLookingCenterEntity(dep.getDependent().getBegin(), dep.getDependent().getEnd(), dep.getDependent().getCoveredText(), dep.getDependencyType());
					
					if(!forwardLookingCenters.contains(e)) {
						forwardLookingCenters.add(e);
					}
				}
			}
			//add entities of main clause to cf
			for (ForwardLookingCenterEntity e : cfOfMainClause) {
				for (ForwardLookingCenterEntity e1 : forwardLookingCenters) {
					if (e.getBegin()==e1.getBegin()) {
						e1.setDependencyType(e.getDependencyType());
					}
				}
			}
			
			//Named Entity bind together(90-136)
			ArrayList<ForwardLookingCenterEntity> listNER = new ArrayList<>();
			final Collection<NamedEntity> ners = JCasUtil.selectCovered(aJCas, NamedEntity.class, sentence);
			ArrayList<String> namedEntitys = new ArrayList<>();
			for (NamedEntity ner : ners) {
				if (!ner.getValue().equals("NUMBER")&&!ner.getValue().equals("PERSON")) {
//					System.out.println("NEr:"+ner.getCoveredText()+ " " +ner.getValue());
					namedEntitys.add(ner.getCoveredText());
					
				}				
			}
			for (Dependency dep : dependencies){			
				if(dep.getDependencyType().equals("compound")&& dep.getDependent().getBegin() !=0 ) {						
					ForwardLookingCenterEntity cfe2 = new ForwardLookingCenterEntity();
					cfe2.setName(dep.getGovernor().getCoveredText());
					cfe2.setBegin(dep.getGovernor().getBegin());
					cfe2.setEnd(dep.getGovernor().getEnd());
					cfe2.setDependencyType("");
					if (!listNER.contains(cfe2) && namedEntitys.contains(cfe2.getName())) {
						listNER.add(cfe2);
					}						
					ForwardLookingCenterEntity cfe1 = new ForwardLookingCenterEntity();
					cfe1.setName(dep.getDependent().getCoveredText());
					cfe1.setBegin(dep.getDependent().getBegin());
					cfe1.setEnd(dep.getDependent().getEnd());
					cfe1.setDependencyType("");
					if (!listNER.contains(cfe1) && namedEntitys.contains(cfe1.getName())) {
						listNER.add(cfe1);
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
			
			String dependencyTypeOfNER = "";
			outerloop1:
			for (ForwardLookingCenterEntity e : forwardLookingCenters){
				if (!listNER.isEmpty()) {
					if (listNER.get(0).getBegin() == e.getBegin()) {
						dependencyTypeOfNER = e.getDependencyType(); //dependencyType for new NER is dependency type of the first element in listNER
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

			// replace the old entity with the resolved entity
			for (int i = 0; i < forwardLookingCenters.size(); i++) {
				for (ForwardLookingCenterEntity e : listNER) {
					if (forwardLookingCenters.get(i).getName().equals(e.getName())&& forwardLookingCenters.get(i).getBegin()==e.getBegin()) { 
						forwardLookingCenters.set(i, e);
					}
				}
			}

			// solve the problem "and" and "or" 			
			ArrayList<ForwardLookingCenterEntity> listConj = new ArrayList<>();
			for (Dependency dep : dependencies){ //find entities that match each other with "and" and "or" and add them to listConj
				if (!dep.getDependencyType().equals("punct")) {		  
					if (dep.getDependencyType().contains("conj")) {
						ForwardLookingCenterEntity e1 = new ForwardLookingCenterEntity(dep.getDependent().getBegin(), dep.getDependent().getEnd(), dep.getDependent().getCoveredText(), "");
						ForwardLookingCenterEntity e2 = new ForwardLookingCenterEntity(dep.getGovernor().getBegin(), dep.getGovernor().getEnd(), dep.getGovernor().getCoveredText(), "");
						//check if entities are in forwardlookingcenters. because exp. against has dependency "conj" type too
						if (forwardLookingCenters.contains(e2)) {							
							if (!listConj.contains(e2)) {
								listConj.add(e2);
							}
						}
						if (forwardLookingCenters.contains(e1)) {
							if (!listConj.contains(e1)) {
								listConj.add(e1);
							}
						}
					  
					} 
				} 
			}
			
			//dependency type for all entities in listConj should be the dependency type of the first element in listConj
			String dependencyTypeForConj = ""; 
			ForwardLookingCenterEntity firstEntityOfConj = new ForwardLookingCenterEntity() ;
			if (!listConj.isEmpty()) {
				firstEntityOfConj= (ForwardLookingCenterEntity) listConj.get(0);
			}
			for (ForwardLookingCenterEntity e : forwardLookingCenters){
				if (e.getBegin() == firstEntityOfConj.getBegin()){
					dependencyTypeForConj = e.getDependencyType();
					break;
				} 
			}
			//add dependency type for all entities in listConj
			for (ForwardLookingCenterEntity e : listConj) { 
				e.setDependencyType(dependencyTypeForConj+"CONJ");			
			}
			// replace the old entity with the resolved entity
			for (int i = 0; i < forwardLookingCenters.size(); i++) {
				for (ForwardLookingCenterEntity e : listConj) {
					if (forwardLookingCenters.get(i).getName().equals(e.getName())&& forwardLookingCenters.get(i).getBegin()==e.getBegin()) { 
						forwardLookingCenters.set(i, e);
					}
				}
			}
			
			ArrayList<ForwardLookingCenterEntity> possibleSubject = new ArrayList<>();
			ArrayList<ForwardLookingCenterEntity> possibleObject = new ArrayList<>();
			ArrayList<ForwardLookingCenterEntity> possibleOthers = new ArrayList<>();
			for(ForwardLookingCenterEntity e : forwardLookingCenters) {
				if(e.getDependencyType().contains("MAIN")&&e.getDependencyType().contains("subj")) {
					possibleSubject.add(e);
				}else if(e.getDependencyType().contains("MAIN")&&e.getDependencyType().contains("obj")) {
					possibleObject.add(e);	
				}else{
					possibleOthers.add(e);				
				}
			}
			//CF of current sentence in correct order
			ArrayList<ForwardLookingCenterEntity> cF = new ArrayList<>();
			cF.addAll(possibleSubject);
			cF.addAll(possibleObject);
			cF.addAll(possibleOthers);
			
			//remove duplicate entity in cF
			ArrayList<ForwardLookingCenterEntity> cFFinal = new ArrayList<>();
			for(ForwardLookingCenterEntity e : cF) {
				if(!cFFinal.contains(e)) {
					cFFinal.add(e);
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


