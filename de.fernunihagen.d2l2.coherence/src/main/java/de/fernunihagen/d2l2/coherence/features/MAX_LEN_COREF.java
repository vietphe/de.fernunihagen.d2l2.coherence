package de.fernunihagen.d2l2.coherence.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.fernunihagen.d2l2.coherence.types.Entity;

/**
 * Extracts the number of CBs that not undefined/number of sentences in the classification unit
 */
public class MAX_LEN_COREF extends FeatureExtractorResource_ImplBase implements FeatureExtractor{
	
	public static final String MAX_LEN_COREF = "MAX_LEN_COREF";
	
	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget classificationUnit)
	    throws TextClassificationException
	{
	 
		Set<Feature> features = new HashSet<Feature>();	 
		Collection<Entity> entities = JCasUtil.select(jcas, Entity.class); 
			int max= 0;
			for (Entity entity : entities) {
				if (max <= Integer.parseInt(entity.getCorefId())) {
					max = Integer.parseInt(entity.getCorefId());
				}
			//			System.out.println(entity.getSentenceIndex()+ "->"+ entity.getName()+" "+ entity.getCorefId()); 
			}
			
			Map<Integer,ArrayList<Entity>> map = new HashMap<>();
			for (int i = 1; i <= max; i++) {
				
				ArrayList<Entity> aList = new ArrayList<>(); 
				for (Entity e : entities) {
					if(Integer.parseInt(e.getCorefId()) == i) {
						aList.add(e);
						
					}
					
				}
				if (!aList.isEmpty()) {
					map.put(i, aList);
				}
				
			}
			int max_len = 0;
			for (Map.Entry<Integer, ArrayList<Entity>> entry : map.entrySet()) {
				ArrayList<Entity> val = entry.getValue();
				if(val.size()>= max_len) {
					max_len = val.size();
				}
			
			}
		features.add(new Feature(MAX_LEN_COREF, max_len, FeatureType.NUMERIC));
		return features;
	}
}