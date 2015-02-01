package cz.czu.kit.soukenik.quizproject.Interfaces;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizResults {
    public double getScore();
    public String[] getLaunchedTests();
    public double getAvgScore();
    public boolean isSelectedTestedSuccessful(int id);
    public void setScore(double score);
}
