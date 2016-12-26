// Exploration of the Stanford Simple CoreNLP API: stanfordnlp.github.io/CoreNLP/simple

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.varia.NullAppender;
import edu.stanford.nlp.simple.*;

public class SimpleCoreNLPExample {
    public static void main(String[] args) {
        // Configure logger
        BasicConfigurator.configure(new NullAppender());

        // Initialize document
        Document doc = new Document("Barack Obama was born in Hawaii. He is the president. Obama was elected in 2008.");

        // Explore annotations (lazy loaded)
        for (Sentence sentence : doc.sentences()) {
            System.out.println("\nSentence: " + sentence);
            System.out.println("Words: " + sentence.words());
            System.out.println("Lemmas: " + sentence.lemmas());
            System.out.println("POS tags: " + sentence.posTags());
            System.out.println("NER tags: " + sentence.nerTags());
            System.out.println("Dependency parse: " + sentence.incomingDependencyLabels());
            System.out.println("Constituency parse: " + sentence.parse());
            System.out.println("Natural logic polarity: " + sentence.natlogPolarities());
            System.out.println("Open Information Extraction: " + sentence.openie());
        }
    }
}