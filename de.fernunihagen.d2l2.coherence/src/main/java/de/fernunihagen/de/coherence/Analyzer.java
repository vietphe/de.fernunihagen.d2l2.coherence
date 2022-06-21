package de.fernunihagen.de.coherence;

import java.util.ArrayList;
import java.util.Collection;

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
		System.out.println(essayText);
		Collection<CFEntity> cfEntities = JCasUtil.select(aJCas, CFEntity.class);		
		for (CFEntity entity : cfEntities) {			  
			System.out.println("CF: "+entity.getSentenceIndex()+" "+ entity.getName() + " "+entity.getBeginPosition()+":"+entity.getEndPosition()+" "+entity.getDependencyType()); 
		} 
		System.out.println();
		Collection<CoreferenceEntity> coreferenceEntities = JCasUtil.select(aJCas, CoreferenceEntity.class);
		for (CoreferenceEntity c: coreferenceEntities) {
			System.out.println("Coref: "+ c.getFirstMention()+" <-- "+c.getName()+" " +c.getBeginPosition()+":"+c.getEndPosition());
		}
		System.out.println();
		
		Collection<Transition> transitions = JCasUtil.select(aJCas, Transition.class); 
		for (Transition transition : transitions) {
		  		System.out.println(transition.getSentenceIndex()-1+ "->"+ transition.getSentenceIndex()+" "+ transition.getName()); 
		}
		
		
		ArrayList<CoreferenceEntity> notMatchWithCF = new ArrayList<>();
		for (CoreferenceEntity e : coreferenceEntities) {
			notMatchWithCF.add(e);
		}
		ArrayList<CoreferenceEntity> corefEntity = new ArrayList<>();
		for (CoreferenceEntity e : coreferenceEntities) {
			corefEntity.add(e);
		}
		ArrayList<CoreferenceEntity> matchWithCF = new ArrayList<>();
		for (CoreferenceEntity e1: coreferenceEntities) {
			for (CFEntity e2: cfEntities) {
				if(e1.getName().equals(e2.getName()) && e1.getBeginPosition() == e2.getBeginPosition()) {
					matchWithCF.add(e1);
				}
				
			}
		}
		for (CoreferenceEntity e : matchWithCF) {
//			System.out.println(e.getName()+ " "+ e.getBeginPosition()+ "->" + e.getFirstMention() );
		}
		
		for (CoreferenceEntity e : matchWithCF) {
			for (CoreferenceEntity e1 : corefEntity) {
				if(e.getName().equals(e1.getName()) && e.getBeginPosition() == e1.getBeginPosition()) {
					notMatchWithCF.remove(e1);
				}
			}
		}
		for (CoreferenceEntity e : notMatchWithCF) {
			System.out.println("xxx "+e.getName()+ " "+ e.getBeginPosition()+ "-> " + e.getFirstMention() );
		}
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
