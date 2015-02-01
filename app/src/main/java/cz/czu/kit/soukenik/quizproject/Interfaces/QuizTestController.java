package cz.czu.kit.soukenik.quizproject.Interfaces;

/**
 * Created by soukenik on 11/11/14.
 */
public interface QuizTestController {
    public String getCurrentQuestion();
    public String[] getCurrentAnswers();
    public int getCurrentPosition();
    public boolean isQuizFinished();
    public void selectedAnswer(int answer);
    public boolean checkAnswer();
}
