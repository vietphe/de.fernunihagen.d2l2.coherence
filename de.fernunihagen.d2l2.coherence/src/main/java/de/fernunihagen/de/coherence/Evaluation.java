package de.fernunihagen.de.coherence;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class Evaluation extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		int numOfPronoun = 0;
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token token : tokens) {
			if(token.getPos().getCoarseValue().equals("PRON")) {
				numOfPronoun++;
			}
		}
		System.out.println(numOfPronoun);
	}

}
