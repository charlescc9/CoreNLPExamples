// Exploration of the Stanford CoreNLP API: stanfordnlp.github.io/CoreNLP/api

import edu.stanford.nlp.dcoref.CorefCoreAnnotations.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.varia.NullAppender;

public class CoreNLPExample {
    public static void main(String[] args) {
        // Configure logger
        BasicConfigurator.configure(new NullAppender());

        // Initialize and annotate document pipeline
        Annotation document = new Annotation("Barack Obama was born in Hawaii. He is the president. Obama was elected in 2008.");
        String annotators = "tokenize, ssplit, pos, lemma, ner, parse, entitymentions, natlog, openie, sentiment";
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", annotators));
        pipeline.annotate(document);

        // Explore annotations
        System.out.println("Coreferences: " + document.get(CorefChainAnnotation.class));

        for (CoreMap sentence: document.get(SentencesAnnotation.class)) {
            System.out.println("\nSentence: " + sentence.get(TextAnnotation.class));
            for (CoreLabel word: sentence.get(TokensAnnotation.class)) {
                System.out.println("Word: " + word.get(TextAnnotation.class) +
                        ", Lemma: " + word.get(LemmaAnnotation.class) +
                        ", POS tag: " + word.get(PartOfSpeechAnnotation.class) +
                        ", NER tag: " + word.get(NamedEntityTagAnnotation.class));
            }

            System.out.println("Mentions: " + sentence.get(MentionsAnnotation.class));
            System.out.println("OpenIE: " + sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class));
            System.out.println("Sentiment: " + sentence.get(SentimentCoreAnnotations.SentimentClass.class));
            System.out.println("Dependency parse: " + sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class));
            System.out.println("Constituency parse: ");
            TreePrint treePrint = new TreePrint("penn");
            treePrint.printTree(sentence.get(TreeCoreAnnotations.TreeAnnotation.class));
        }
    }
}