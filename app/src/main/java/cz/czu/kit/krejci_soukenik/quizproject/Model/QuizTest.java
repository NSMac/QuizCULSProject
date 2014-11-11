package cz.czu.kit.krejci_soukenik.quizproject.Model;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizTest {
    public String getCurrentQuestion();
    public String[] getCurrentAnswers();
    public int getCurrentPosition();
    public boolean isQuizFinished();
    public void selectedAnswer(int answer);
    public boolean checkAnswer();
}
