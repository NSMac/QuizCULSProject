package cz.czu.kit.soukenik.quizproject.Model;

import java.io.Serializable;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizOdpoved implements Serializable {

    public static final String TAG_ANSWERS = "odpovedi";
    public static final String TAG_ID_QUESTION = "id_otazka";
    public static final String TAG_ANSWER_TEXT = "text";
    public static final String TAG_OK = "ok";
    public static final String TAG_DISPLAY = "zobrazit";
    public static final String TAG_QUSTION = "otazka_id_otazka";
    public static final String TAG_QUESTION_TYPE = "typ_otazka_id_typ_otazka";

    private int idOtazka;
    private int otazkaIdOtazka;
    private int idTest;
    private String text;
    private int ok;
    private int zobrazit;
    private boolean selectedAnswer;

    @Override
    public String toString() {
        return "QuizOdpoved{" +
                "idOtazka=" + idOtazka +
                ", otazkaIdOtazka=" + otazkaIdOtazka +
                ", idTest=" + idTest +
                ", text='" + text + '\'' +
                ", ok=" + ok +
                ", zobrazit=" + zobrazit +
                ", selectedAnswer=" + selectedAnswer +
                '}';
    }

    public boolean isSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(boolean selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public QuizOdpoved(int idOtazka, int otazkaIdOtazka, int idTest, String text, int ok, int zobrazit) {
        this.idOtazka = idOtazka;
        this.otazkaIdOtazka = otazkaIdOtazka;
        this.idTest = idTest;
        this.text = text;
        this.ok = ok;
        this.zobrazit = zobrazit;
        this.selectedAnswer = false;
    }

    public int getIdOtazka() {
        return idOtazka;
    }

    public int getOtazkaIdOtazka() {
        return otazkaIdOtazka;
    }

    public int getIdTest() {
        return idTest;
    }

    public String getText() {
        return text;
    }

    public int getOk() {
        return ok;
    }

    public int getZobrazit() {
        return zobrazit;
    }
}
