package cz.czu.kit.krejci_soukenik.quizproject.Model;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizNetwork {
    public String createHash();
    public boolean connectToTheServer();
    public String getLinkToTest(String selectedTest);
    public Object getParsedFile(Object jsonFile);
}
