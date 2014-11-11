package cz.czu.kit.krejci_soukenik.quizproject.Model;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizResults {
    public double getScore();
    public String[] getLaunchedTests();
    public double getAvgScore();
    public boolean isSelectedTestedSuccessful(int id);
}
