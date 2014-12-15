package cz.czu.kit.krejci_soukenik.quizproject.Model;

import android.util.Log;
import java.io.Serializable;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizTest implements Serializable {

    //JSON Node names for test
    public static final String TAG_ID = "id_test";
    public static final String TAG_NUM_OF_QUESTIONS = "pocet_otazek";
    public static final String TAG_TITLE = "nazev";
    public static final String TAG_NUM_OF_REPEATS = "pocet_opakovani";
    public static final String TAG_LIMIT = "limit";
    public static final String TAG_FINAL = "zaverecny";
    public static final String TAG_SUCCESS_RATE = "uspesnost";


    private int id;
    private int pocetOtazek;
    private String nazev;
    private int pocetOpakovani;
    private int limit;
    private int zaverecny;
    private int uspesnost;

    public QuizTest(int id, int pocetOtazek, String nazev, int pocetOpakovani, int limit, int zaverecny, int uspesnost) {
        this.id = id;
        this.pocetOtazek = pocetOtazek;
        this.nazev = nazev;
        this.pocetOpakovani = pocetOpakovani;
        this.limit = limit;
        this.zaverecny = zaverecny;
        this.uspesnost = uspesnost;

        Log.i("QuizTest", this.toString());
    }

    public int getId() {
        return id;
    }

    public int getPocetOtazek() {
        return pocetOtazek;
    }

    public String getNazev() {
        return nazev;
    }

    public int getPocetOpakovani() {
        return pocetOpakovani;
    }

    public int getLimit() {
        return limit;
    }

    public int isZaverecny() {
        return zaverecny;
    }

    public int getUspesnost() {
        return uspesnost;
    }

    @Override
    public String toString() {
        return "QuizTest{" +
                "id=" + id +
                ", pocetOtazek=" + pocetOtazek +
                ", nazev='" + nazev + '\'' +
                ", pocetOpakovani=" + pocetOpakovani +
                ", limit=" + limit +
                ", zaverecny=" + zaverecny +
                ", uspesnost=" + uspesnost +
                '}';
    }
}
