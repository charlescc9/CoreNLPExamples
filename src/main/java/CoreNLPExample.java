// Exploration of the Stanford CoreNLP API: stanfordnlp.github.io/CoreNLP/api

import edu.stanford.nlp.dcoref.CorefCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
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

        // Annotate document pipeline
        String text = "Obama was born in Hawaii. He is our president. " +
                "President Xi Jinping of China, on his first visit to the United States, " +
                "showed off his familiarity with American history and pop culture on Tuesday night.";
        String annotators = "tokenize, ssplit, pos, lemma, ner, parse, natlog, openie, mention, dcoref";
        Annotation document = new Annotation(text);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", annotators));
        pipeline.annotate(document);

        // Explore annotations
        System.out.println("Coreferences: " + document.get(CorefCoreAnnotations.CorefChainAnnotation.class));

        for (CoreMap sentence: document.get(CoreAnnotations.SentencesAnnotation.class)) {
            System.out.println("\nSentence: " + sentence.get(CoreAnnotations.TextAnnotation.class));

            for (CoreLabel word: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                System.out.println("Word: " + word.get(CoreAnnotations.TextAnnotation.class) +
                        ", Lemma: " + word.get(CoreAnnotations.LemmaAnnotation.class) +
                        ", POS tag: " + word.get(CoreAnnotations.PartOfSpeechAnnotation.class) +
                        ", NER tag: " + word.get(CoreAnnotations.NamedEntityTagAnnotation.class));
            }

            System.out.println("Mentions: " + sentence.get(CoreAnnotations.MentionsAnnotation.class));
            System.out.println("OpenIE: " + sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class));
            System.out.println("Dependency parse: " + sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class));
            System.out.println("Constituency parse: ");
            TreePrint treePrint = new TreePrint("penn");
            treePrint.printTree(sentence.get(TreeCoreAnnotations.TreeAnnotation.class));
        }
    }
}