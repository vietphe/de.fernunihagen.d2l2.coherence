package de.fernunihagen.de.coherence;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.coherence.io.CorefReader;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpCoreferenceResolver;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpDependencyParser;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpParser;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpSegmenter;


public class BaseExperiment {

	public static void main(String[] args) throws ResourceInitializationException, UIMAException, IOException {
		preprocess();

	}
	private static void preprocess() throws ResourceInitializationException, UIMAException, IOException {

		// TODO: adjust paths and param_Language 
		String documentPath ="resources/Example.csv";
		String outputPath = "D:\\HIWI\\CF\\output.txt";
		String param_Language = "en";
//		String param_Language = "de";
		
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
				CorefReader.class, CorefReader.PARAM_INPUT_FILE, documentPath,CorefReader.PARAM_LANGUAGE, param_Language);
		AnalysisEngineDescription posTagger = createEngineDescription(CoreNlpPosTagger.class,
				CoreNlpPosTagger.PARAM_LANGUAGE, param_Language);  								
		AnalysisEngineDescription seg = createEngineDescription(CoreNlpSegmenter.class,
				CoreNlpSegmenter.PARAM_LANGUAGE, param_Language);
		AnalysisEngineDescription lemma = createEngineDescription(CoreNlpLemmatizer.class);
		AnalysisEngineDescription parser = createEngineDescription(CoreNlpParser.class,
				CoreNlpParser.PARAM_LANGUAGE, param_Language,CoreNlpParser.PARAM_VARIANT,"factored");
		AnalysisEngineDescription ner = createEngineDescription(CoreNlpNamedEntityRecognizer.class,
				CoreNlpNamedEntityRecognizer.PARAM_LANGUAGE, param_Language);
		AnalysisEngineDescription depparser = createEngineDescription(CoreNlpDependencyParser.class,CoreNlpDependencyParser.PARAM_LANGUAGE,param_Language,CoreNlpDependencyParser.PARAM_VARIANT,"ud");
		AnalysisEngineDescription coreNlpCoreferenceResolver = createEngineDescription(CoreNlpCoreferenceResolver.class);
		AnalysisEngineDescription corefAnnotator = createEngineDescription(CoreferenceAnnotator.class);
		AnalysisEngineDescription cFAnnotator = createEngineDescription(CFAnnotator.class);
		AnalysisEngineDescription transitionAnnotator = createEngineDescription(TestClass.class);
		AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class);
		
		SimplePipeline.runPipeline(reader, 
				seg, 
				posTagger,
				lemma,
				parser,
				ner,
				depparser,
				coreNlpCoreferenceResolver,
				corefAnnotator,
				cFAnnotator,
				transitionAnnotator,
				analyzer
				);
	}

}
