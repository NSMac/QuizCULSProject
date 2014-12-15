package cz.czu.kit.krejci_soukenik.quizproject.Interfaces;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizNetwork {
    public  String getUrl() ;
    public boolean connectToTheServer();
    public String getLinkToTest(String selectedTest);
    public Object getParsedFile(Object jsonFile);
}
