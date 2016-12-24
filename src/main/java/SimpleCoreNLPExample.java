// Exploration of the Stanford Simple CoreNLP API: stanfordnlp.github.io/CoreNLP/simple

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.varia.NullAppender;

public class SimpleCoreNLPExample {
    public static void main(String[] args) {
        // Configure logger
        BasicConfigurator.configure(new NullAppender());

        // Initialize document
        String text = "Obama was born in Hawaii. He is our president. " +
                "President Xi Jinping of China, on his first visit to the United States, " +
                "showed off his familiarity with American history and pop culture on Tuesday night.";
        Document doc = new Document(text);

        // Explore annotations (lazy loaded)
        for (Sentence sentence : doc.sentences()) {
            System.out.println("\nSentence: " + sentence);
            System.out.println("Words: " + sentence.words());
            System.out.println("Lemmas: " + sentence.lemmas());
            System.out.println("POS tags: " + sentence.posTags());
            System.out.println("NER tags: " + sentence.nerTags());
            System.out.println("Parse: " + sentence.parse());
            System.out.println("Dependencies: " + sentence.incomingDependencyLabels());
            System.out.println("Open Information Extraction: " + sentence.openie());
        }
    }
}
