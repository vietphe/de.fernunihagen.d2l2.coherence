package de.fernunihagen.de.coherence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.fernunihagen.d2l2.coherence.types.Transition;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

public class Analyzer extends JCasAnnotator_ImplBase {
	public static final String PARAM_OUTPUT_FILE = "outputFile";
	@ConfigurationParameter(name = PARAM_OUTPUT_FILE, mandatory = false)
	protected String outputFile;	
	StringBuilder output;
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);
		output = new StringBuilder();
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		String essayText = aJCas.getDocumentText();
//		System.out.println(essayText);
		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		int numOfSentences = sentences.size();
		Collection<CFEntity> cfEntities = JCasUtil.select(aJCas, CFEntity.class);
		
		//calculate number of 3 Spezialfälle
		int numOfNER = 0;
		int numOfAndOr = 0;
		int numOfSubClause = 0;		
		for (CFEntity entity : cfEntities) {
			if (entity.getDependencyType().contains("NER")) {
				numOfNER +=1;
			}
			if (entity.getDependencyType().contains("CONJ")) {
				numOfAndOr +=1;
			}
			if (entity.getDependencyType().contains("wrongNsub")) {
				numOfSubClause +=1;
			}
//			System.out.println("CF: "+entity.getSentenceIndex()+" "+ entity.getName() + " "+entity.getBeginPosition()+":"+entity.getEndPosition()+" "+entity.getDependencyType()); 
		} 
		
		Collection<CoreferenceEntity> coreferenceEntities = JCasUtil.select(aJCas, CoreferenceEntity.class);
		for (CoreferenceEntity c: coreferenceEntities) {
//			System.out.println("Coref: "+c.getName()+" " +c.getBeginPosition()+":"+c.getEndPosition() +" --> "+c.getFirstMention());
		}		
		ArrayList<CFEntity> cFNotMatchWithCoref = new ArrayList<>();
		for (CFEntity e : cfEntities) {
			cFNotMatchWithCoref.add(e);
		}
		//printing CFs
		for (int i = 1; i <= numOfSentences; i++) {
			System.out.print("CF of ["+i+"]: ");
			for (CFEntity e : cFNotMatchWithCoref) {
				if(e.getSentenceIndex() == i) {
					System.out.print(e.getName()+" "+e.getDependencyType()+"-");
				}
			}
			System.out.println();
		}
		
		ArrayList<CoreferenceEntity> corefNotMatchWithCF = new ArrayList<>();
		for (CoreferenceEntity e : coreferenceEntities) {
			corefNotMatchWithCF.add(e);
		}
		
		ArrayList<CFEntity> cFMatchWithCoref = new ArrayList<>();
		ArrayList<CoreferenceEntity> corefMatchWithCF = new ArrayList<>();
		for (CoreferenceEntity e1: coreferenceEntities) {
			for (CFEntity e2: cfEntities) {
				if(e1.getName().equals(e2.getName()) && e1.getBeginPosition() == e2.getBeginPosition()) {
					corefMatchWithCF.add(e1);
					cFMatchWithCoref.add(e2);
				}
				
			}
		}
		
		for (CoreferenceEntity e : corefMatchWithCF) {
			for (CoreferenceEntity e1 : coreferenceEntities) {
				if(e.getName().equals(e1.getName()) && e.getBeginPosition() == e1.getBeginPosition()) {
					corefNotMatchWithCF.remove(e1);
				}
			}
		}
//		System.out.println("---CoreferenceEntitys do not match CFEntitys---: "+ corefNotMatchWithCF.size()+" out of "+coreferenceEntities.size()+".");
		for (CoreferenceEntity e : corefNotMatchWithCF) {
//			System.out.println(e.getName()+ " "+ e.getBeginPosition()+ "-> " + e.getFirstMention() );
		}
		for (CFEntity  e1 : cFMatchWithCoref) {
			for (CFEntity e2 : cfEntities) {
				if(e1.getName().equals(e2.getName()) && e1.getBeginPosition() == e2.getBeginPosition()) {
					cFNotMatchWithCoref.remove(e1);
				}
			}
		}
//		System.out.println("---CFEntitys do not match CoreferenceEntitys---: "+ cFNotMatchWithCoref.size()+" out of "+cfEntities.size()+".");
		for (CFEntity e : cFNotMatchWithCoref) {
//			System.out.println(e.getName()+ " "+ e.getBeginPosition()+ "-> " + e.getDependencyType() );
		}
		Collection<Transition> transitions = JCasUtil.select(aJCas, Transition.class); 
		for (Transition transition : transitions) {
		  		System.out.println(transition.getSentenceIndex()-1+ "->"+ transition.getSentenceIndex()+" "+ transition.getName()); 
		}
		System.out.println();
		
		System.out.println("Spezialfall mit NER: "+ numOfNER);
		System.out.println("Spezialfall mit And/Or: "+ numOfAndOr);
		System.out.println("Spezialfall mit Nebensatz: "+ numOfSubClause);
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
