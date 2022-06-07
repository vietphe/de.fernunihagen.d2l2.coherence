package de.fernunihagen.de.coherence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

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
		ArrayList<CoreferenceChain> corefs = new ArrayList();
		for (CoreferenceChain coreferenceChain : corefs1) {
			corefs.add(coreferenceChain);
		}
		
		for (int i = 0; i < corefs.size(); i++) {
			//reads only chains that have more than 2 elements
			if(corefs.get(i).getFirst().getNext()!=null) {
				String corefChains = readChains(corefs.get(i).getFirst());
				stringBuilder.delete(0, stringBuilder.length()-1);
//				System.out.println(corefChains);
				int coreferenceLinkId = i;
				String [] strArr = corefChains.split(";");
				
				//get only last word of each element in coref chains ex.: the children --> children
				for (int k = 0; k < strArr.length; k++) {
					if(numOfWord(strArr[k])>3) {
						strArr[k] = newCorefEntity(strArr[k]);
					}
				}
				String [] temp1 = strArr[0].split(" ");
				String firstMention = temp1[0];
				if(firstMention.equals("")) {
					String [] temp2 = strArr[1].split(" ");
					firstMention = temp2[0];
				}				
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
					}
				}
			}
		}
	}
		
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();

		
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
  	//get only the last word of CorefEntity
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
}


