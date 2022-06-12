package de.fernunihagen.de.coherence;

import java.util.Collection;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.types.CFEntity;
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
//		System.out.println(essayText);
		Collection<CFEntity> entities = JCasUtil.select(aJCas, CFEntity.class);
		
//		for (CFEntity entity : entities) {			  
//			System.out.println(entity.getSentenceIndex()+ " "+ entity.getSentenceIndex()+" "+ entity.getName()); 
//		} 
		Collection<Transition> transitions = JCasUtil.select(aJCas, Transition.class); 
		for (Transition transition : transitions) {
		  		System.out.println(transition.getSentenceIndex()-1+ "->"+ transition.getSentenceIndex()+" "+ transition.getName()); 
		}
		 
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
