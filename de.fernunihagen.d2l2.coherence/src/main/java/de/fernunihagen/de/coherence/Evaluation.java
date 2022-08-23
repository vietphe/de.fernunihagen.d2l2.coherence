package de.fernunihagen.de.coherence;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import webanno.custom.Cf;

public class Evaluation extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		for (Sentence sentence : sentences) {
			sentence.getBegin();
		}
		int numOfSentences = sentences.size();
		System.out.println(numOfSentences);
		Collection<Cf> cfs = JCasUtil.select(aJCas, Cf.class);
		for (Cf cf : cfs) {
			cf.getOrder();
		}
		System.out.println(cfs.size());
	}

}
