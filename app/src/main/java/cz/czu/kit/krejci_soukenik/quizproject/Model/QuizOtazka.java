package cz.czu.kit.krejci_soukenik.quizproject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizOtazka implements Serializable {

    public static final String TAG_QUESTION = "otazka";
    public static final String TAG_ID_QUESTION = "id_otazka";
    public static final String TAG_QUESTION_TEXT = "text";
    public static final String TAG_OK = "ok";
    public static final String TAG_QUESTION_TYPE = "typ_otazka_id_typ_otazka";

    private int idOtazka;
    private String otazka;
    private String ok;
    private int typOtazky;
    private ArrayList<QuizOdpoved> odpvedi;


    @Override
    public String toString() {
        return "QuizOtazka{" +
                "idOtazka=" + idOtazka +
                ", otazka='" + otazka + '\'' +
                ", ok='" + ok + '\'' +
                ", typOtazky=" + typOtazky +
                ", odpvedi=" + odpvedi +
                '}';
    }


    public QuizOtazka(ArrayList<QuizOdpoved> odpvedi, int idOtazka, String otazka, String  ok, int typOtazky) {
        this.odpvedi = odpvedi;
        this.idOtazka = idOtazka;
        this.otazka = otazka;
        this.ok = ok;
        this.typOtazky = typOtazky;
    }

    public int getIdOtazka() {
        return idOtazka;
    }

    public String getOtazka() {
        return otazka;
    }

    public String getOk() {
        return ok;
    }

    public int getTypOtazky() {
        return typOtazky;
    }

    public ArrayList<QuizOdpoved> getOdpvedi() {
        return odpvedi;
    }

}
