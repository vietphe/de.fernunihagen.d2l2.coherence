package de.fernunihagen.de.coherence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.fernunihagen.d2l2.coherence.types.Entity;
import de.fernunihagen.d2l2.coherence.types.Transition;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

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
//		String essayText = aJCas.getDocumentText();
//		System.out.println(essayText);
		Collection<CFEntity> cfEntities = JCasUtil.select(aJCas, CFEntity.class);
		Collection<Entity> entities = JCasUtil.select(aJCas, Entity.class); 
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
		
		for (Map.Entry<Integer, ArrayList<Entity>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<Entity> val = entry.getValue();
			System.out.print(key+" "+":");
			for (Entity entity : val) {
				System.out.print(entity.getName()+"-");
			}
			System.out.println();
		}
		System.out.println("Num of Coref kette: "+ map.size());
		int max_len = 0;
		for (Map.Entry<Integer, ArrayList<Entity>> entry : map.entrySet()) {
			ArrayList<Entity> val = entry.getValue();
			if(val.size()>= max_len) {
				max_len = val.size();
			}
		
		}
		System.out.println("Max leng of Coref kette: "+ max_len);
		
		
//		System.out.println("Transitions: ");
//		Collection<Transition> transitions = JCasUtil.select(aJCas, Transition.class); 
//		for (Transition transition : transitions) {
//		  		System.out.println(transition.getSentenceIndex()-1+ "->"+ transition.getSentenceIndex()+" "+ transition.getName()); 
//		}
//		System.out.println();
		
		Collection<CoreferenceEntity> coreferenceEntities = JCasUtil.select(aJCas, CoreferenceEntity.class);
		int highestCorefId = 0;
		for (CoreferenceEntity c: coreferenceEntities) {
			if (Integer.valueOf(c.getId()) >= highestCorefId ) {
				highestCorefId = Integer.valueOf(c.getId());
			}
//			System.out.println("Coref: "+c.getId()+" "+c.getName()+" " +c.getBeginPosition()+":"+c.getEndPosition() +" --> "+c.getFirstMention());
		}
//		//printing Coref
//		System.out.println("Coref: ");
//		for (int i = 1; i <= highestCorefId; i++) {
//			System.out.print("Id ["+i+"]: ");
//			for (CoreferenceEntity e: coreferenceEntities) {
//				if(Integer.valueOf(e.getId()) == i) {
//					System.out.print(e.getName()+" ("+e.getBeginPosition()+":"+e.getEndPosition()+")"+" - ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		ArrayList<CFEntity> cFNotMatchWithCoref = new ArrayList<>();
		for (CFEntity e : cfEntities) {
			cFNotMatchWithCoref.add(e);
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
//		for (CFEntity e : cFNotMatchWithCoref) {
//			System.out.print(e.getName()+"-" );
//		}
//		System.out.println();
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
//					System.out.println("CF: "+entity.getSentenceIndex()+" "+ entity.getName() + " "+entity.getBeginPosition()+":"+entity.getEndPosition()+" "+entity.getDependencyType()); 
		} 
				
//		System.out.println("Spezialfall mit NER: "+ numOfNER);
//		System.out.println("Spezialfall mit And/Or: "+ numOfAndOr);	
//		System.out.println();
		
		int numOfPronoun = 0;
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		for (Token token : tokens) {
			if(token.getPos().getCoarseValue().equals("PRON")) {
				numOfPronoun++;
			}
		}
//		System.out.println("Num of pronoun: "+numOfPronoun);
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
