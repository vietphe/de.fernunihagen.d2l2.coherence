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
import de.fernunihagen.d2l2.coherence.io.EssayReader;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpCoreferenceResolver;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpDependencyParser;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpParser;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;


public class BaseExperiment {

	public static void main(String[] args) throws ResourceInitializationException, UIMAException, IOException {
//		binCasWriter();
		annotate();
//		preprocess();

	}
	public static void binCasWriter() throws ResourceInitializationException, UIMAException, IOException {
		// TODO: adjust paths and param_Language 
				String documentPathCSV ="resources/Example.csv";
				String documentPathTxt ="newresources/";
				String outputPath = "D:\\HIWI\\CF\\output.txt";
				String param_Language = "en";
//				String param_Language = "de";
				
				CollectionReaderDescription csvReader = CollectionReaderFactory.createReaderDescription(
						CorefReader.class, CorefReader.PARAM_INPUT_FILE, documentPathCSV,CorefReader.PARAM_LANGUAGE, param_Language);
				CollectionReaderDescription txtReader = CollectionReaderFactory.createReaderDescription(
						EssayReader.class, EssayReader.PARAM_INPUT_FILE, documentPathTxt,EssayReader.PARAM_LANGUAGE, param_Language);
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
				AnalysisEngineDescription binCasWriter = createEngineDescription(
	                    BinaryCasWriter.class, 
	                    BinaryCasWriter.PARAM_FORMAT, "6+",
	                    BinaryCasWriter.PARAM_OVERWRITE, true,
	                    BinaryCasWriter.PARAM_TARGET_LOCATION, "target/bincas"
	                    );
				SimplePipeline.runPipeline(txtReader, 
						seg, 
						posTagger,
						lemma,
						parser,
						ner,
						depparser,
						binCasWriter
						);


		
	}
	private static void preprocess() throws ResourceInitializationException, UIMAException, IOException {

		// TODO: adjust paths and param_Language 
		String documentPathCSV ="resources/Example.csv";
		String documentPathTxt ="newresources/";
		String outputPath = "D:\\HIWI\\CF\\output.txt";
		String param_Language = "en";
//		String param_Language = "de";
		
		CollectionReaderDescription csvReader = CollectionReaderFactory.createReaderDescription(
				CorefReader.class, CorefReader.PARAM_INPUT_FILE, documentPathCSV,CorefReader.PARAM_LANGUAGE, param_Language);
		CollectionReaderDescription txtReader = CollectionReaderFactory.createReaderDescription(
				EssayReader.class, EssayReader.PARAM_INPUT_FILE, documentPathTxt,EssayReader.PARAM_LANGUAGE, param_Language);
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
		AnalysisEngineDescription transitionAnnotator = createEngineDescription(TransitionAnnotator.class);
		AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class);
		AnalysisEngineDescription eval = createEngineDescription(Evaluation.class);
		
		SimplePipeline.runPipeline(txtReader, 
				seg, 
				posTagger,
				lemma,
				parser,
				ner,
				depparser,
				coreNlpCoreferenceResolver,
				cFAnnotator,
				corefAnnotator,				
				transitionAnnotator,
				analyzer
				);
	}
	private static void annotate() throws ResourceInitializationException, UIMAException, IOException {
		
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(BinaryCasReader.class, 
				BinaryCasReader.PARAM_SOURCE_LOCATION,"target/bincas",BinaryCasReader.PARAM_LANGUAGE, "en", BinaryCasReader.PARAM_PATTERNS, "*.bin");
		AnalysisEngineDescription coreNlpCoreferenceResolver = createEngineDescription(CoreNlpCoreferenceResolver.class);
		AnalysisEngineDescription corefAnnotator = createEngineDescription(CoreferenceAnnotator.class);
		AnalysisEngineDescription cFAnnotator = createEngineDescription(CFAnnotator.class);
		AnalysisEngineDescription transitionAnnotator = createEngineDescription(TransitionAnnotator.class);
		AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class);
		AnalysisEngineDescription eval = createEngineDescription(Evaluation.class);		
		SimplePipeline.runPipeline( reader,				
				coreNlpCoreferenceResolver,
				cFAnnotator,
				corefAnnotator,				
				transitionAnnotator,
				analyzer
				);
	}

}
