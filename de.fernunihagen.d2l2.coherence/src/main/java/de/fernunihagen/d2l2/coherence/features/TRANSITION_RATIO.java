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
import de.fernunihagen.d2l2.coherence.types.Transition;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

/**
 * Extracts the number of CBs that not undefined/number of sentences in the classification unit
 */
public class TRANSITION_RATIO extends FeatureExtractorResource_ImplBase implements FeatureExtractor{
	
	public static final String FN_OF_CONTINUE = "FN_OF_CONTINUE";
	public static final String FN_OF_SMOOTH_SHIFT = "FN_OF_SMOOTH_SHIFT";
	public static final String FN_OF_RETAIN = "FN_OF_RETAIN";
	public static final String FN_OF_ROUGH_SHIFT = "FN_OF_ROUGH_SHIFT";
	
	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget classificationUnit)
	    throws TextClassificationException
	{
	 
	 Set<Feature> features = new HashSet<Feature>();	 
	 int numOfContinue = 0;
	 Collection<Transition> transitions = JCasUtil.select(jcas, Transition.class);
	 for (Transition t : transitions) {
		 if (t.getName().equals("Continue")) {
			 numOfContinue++;
		 }
	 }
	 int numOfSmooth_Shift = 0;
	 for (Transition t : transitions) {
		 if (t.getName().equals("Smooth-Shift")) {
			 numOfSmooth_Shift++;
		 }
	 }
	 int numOfRetain = 0;
	 for (Transition t : transitions) {
		 if (t.getName().equals("Retain")) {
			 numOfRetain++;
		 }
	 }
	 int numOfRough_Shift = 0;
	 for (Transition t : transitions) {
		 if (t.getName().equals("Rough-Shift")) {
			 numOfRough_Shift++;
		 }
	 }
	 	 	
	 features.add(new Feature(FN_OF_CONTINUE, numOfContinue/transitions.size(), FeatureType.NUMERIC));
	 features.add(new Feature(FN_OF_SMOOTH_SHIFT, numOfSmooth_Shift/transitions.size(), FeatureType.NUMERIC));
	 features.add(new Feature(FN_OF_RETAIN, numOfRetain/transitions.size(), FeatureType.NUMERIC));
	 features.add(new Feature(FN_OF_ROUGH_SHIFT, numOfRough_Shift/transitions.size(), FeatureType.NUMERIC));
	 return features;
	}
}
