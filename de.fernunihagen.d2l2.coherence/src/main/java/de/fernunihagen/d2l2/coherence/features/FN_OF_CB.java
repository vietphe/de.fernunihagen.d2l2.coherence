package de.fernunihagen.d2l2.coherence.features;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.fernunihagen.d2l2.coherence.types.CB;
import de.fernunihagen.d2l2.coherence.types.Entity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

/**
 * Extracts the number of CBs that not undefined/number of sentences in the classification unit
 */
public class FN_OF_CB extends FeatureExtractorResource_ImplBase implements FeatureExtractor{
	
	public static final String FN_OF_CB = "FN_OF_CB";
	
	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget classificationUnit)
	    throws TextClassificationException
	{
	 
	 Set<Feature> features = new HashSet<Feature>();	 
	 int numOfCB = 0;
	 Collection<CB> cBs = JCasUtil.select(jcas, CB.class);
	 for (CB c : cBs) {
		 if (!c.getName().equals("undefined")) {
			 numOfCB++;
		 }
	 }
	 Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);
	 	
	 features.add(new Feature(FN_OF_CB, numOfCB/sentences.size(), FeatureType.NUMERIC));
	 return features;
	}
}
