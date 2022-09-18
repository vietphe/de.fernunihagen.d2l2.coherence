package de.fernunihagen.de.coherence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.classes.CorefEntity;
import de.fernunihagen.d2l2.coherence.types.CFEntity;
import de.fernunihagen.d2l2.coherence.types.CoreferenceEntity;
import de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceChain;
import de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink;

public class CoreferenceAnnotator extends JCasAnnotator_ImplBase {
	
	static StringBuilder stringBuilder ;
	
	@Override
	public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);
	    stringBuilder = new StringBuilder();
	    
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
				
		Collection<CoreferenceChain> corefs1 = JCasUtil.select(aJCas, CoreferenceChain.class);
		//convert corefs1 to ArrayList
		ArrayList<CoreferenceChain> corefs = new ArrayList<>();
		for (CoreferenceChain coreferenceChain : corefs1) {
			corefs.add(coreferenceChain);
		}
		int id=1;
		outerloop:
		for (int i = 0; i < corefs.size(); i++) {
			
			String corefChain = readChains(corefs.get(i).getFirst());
			stringBuilder.delete(0, stringBuilder.length()-1);
			//remove ";" at begin of corefchains if it exists 
			char firstChar = corefChain.charAt(0);
			String firstCharStr = Character.toString(firstChar);
			if (firstCharStr.equals(";")) {
				corefChain = corefChain.substring(1, corefChain.length());
			}
//				System.out.println(corefChain);			
			String coreferenceLinkId = ""+id;
			id++;
			String [] strArr = corefChain.split(";");
			
			//TODO: check if pronoun "it" has a reference to an entire sentence. If so, annotate it as entireSentence
			String arr [] = strArr[strArr.length-1].split(" ");
			if(arr[0].toLowerCase().equals("it")&& numOfWord(strArr[0]) >= 6 ){
				strArr[0] = "AsSentence 0 0";
			}		
			//solve nominal phrase
			for (int k = 0; k < strArr.length; k++) {
				if(numOfWord(strArr[k])>4) {
					strArr[k] = nominalPhraseToWord(strArr[k],aJCas);
				}
				//if it is a phrase with 2 words, take the 2nd word
				if(numOfWord(strArr[k])==4) {
					strArr[k] = newCorefEntity(strArr[k]);
				}
			}
			String [] temp1 = strArr[0].split(" ");
			String firstMention = temp1[0];			
//				System.out.println(firstMention);
			for (int j = 0; j < strArr.length; j++) {
//					System.out.println("--"+strArr[j]);
				String [] temp = strArr[j].split(" ");
				if(!temp[0].equals("")) {
					
					CoreferenceEntity ce = new CoreferenceEntity(aJCas);
					ce.setId(coreferenceLinkId);
					ce.setName(temp[0]);
					ce.setBeginPosition(Integer.valueOf(temp[1]));
					ce.setEndPosition(Integer.valueOf(temp[2]));
					ce.setFirstMention(firstMention);
					ce.addToIndexes();
//					System.out.print(temp[0]+" "+"-");
				}
			}
//			System.out.println();
		}
	}
   	//to read CoreferenceLink data type
  	public static String readChains(CoreferenceLink l) {
  		stringBuilder.append(l.getCoveredText()+" "+l.getBegin()+" "+l.getEnd()+""+";");
  		if(l.getNext()==null) {
  			return stringBuilder.toString();
  		}else {
  			readChains(l.getNext());
  		}		
  		return stringBuilder.toString();
  	}
  	public static int numOfWord(String str) {
  		StringTokenizer st = new StringTokenizer(str);  		
  		return st.countTokens();  	
  	}
  	//get only the last word of CorefEntity if corefchain include 2 words
  	public static String newCorefEntity(String str) {  		
  		String result = "";
  		String[] words = str.trim().split("\\s+");
  		int lengthOfElement  = words[words.length-3].length();
  		int begin = Integer.valueOf(words[words.length-1])-lengthOfElement;
  		int end = Integer.valueOf(words[words.length-1]);
  		result +=words[words.length-3]+" "+begin+" "+end;
  		return result;
  	}
  	public static String getLastWord(String str) {
  		String[] words = str.trim().split("\\s+");
  		return  words[words.length-3];
  	}
  	public static int getBegin(String str) {
  		String[] words = str.trim().split("\\s+");
  		int lengthOfElement  = words[words.length-3].length();
  		return Integer.valueOf(words[words.length-1])-lengthOfElement;
  	}
  	
  	public static String nominalPhraseToWord(String str, JCas aJCas) {
  		Collection<CFEntity> cfEntities = JCasUtil.select(aJCas, CFEntity.class);
  		ArrayList<CorefEntity> corefEntities = new ArrayList<>();
  		String result = "";
  		String [] words = str.split(" ");
  		int begin = Integer.valueOf(words[words.length-2]);
  		int end = Integer.valueOf(words[words.length-1]);
  		
  		for (int i = 0; i < words.length-2; i++) {
  			String name = words[i];
  			end = begin + words[i].length();
  			//check if a comma at the end of word
  			try {
				char c = name.charAt(name.length()-1);
				
				String s = Character.toString(c);  			
				if(s.equals(",")){
					name = name.substring(0, name.length()-1);
					end = end-1;				
				}
  			} catch (Exception e) {
  				System.out.println("RS:"+str);
  			}
  			corefEntities.add(new CorefEntity(begin, end, name,"",""));
//  			System.out.println(name +" "+ begin +" "+ end);
  			begin = begin+ words[i].length()+1;
  			
		}
  		outerloop:
  		for (CorefEntity e : corefEntities) {
  			for (CFEntity ce : cfEntities) {
  				if(e.getName().equals(ce.getName()) && e.getBegin() == ce.getBeginPosition() && e.getEnd() == ce.getEndPosition()&& !ce.getDependencyType().equals("poss")) {
  					result = e.getName()+ " " + e.getBegin()+ " "+ e.getEnd(); 					 
  					break outerloop;
  				} 				
  			}
  		}
//  		System.out.println("!----"+result);
  		return result;
  	}
}
