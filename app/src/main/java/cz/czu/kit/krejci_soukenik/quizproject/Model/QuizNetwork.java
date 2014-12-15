package cz.czu.kit.krejci_soukenik.quizproject.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by soukenik on 13/12/14.
 */
public class QuizNetwork {

    private static String urlTests = "http://vzdelavani.csita.cz/auth-service/tests/";
    private static String urlQuestions = "http://vzdelavani.csita.cz/auth-service/questions/";

    public static String getUrlTest() {
        String hashedDate, urlTest;
        // Hash current date
        hashedDate = getMd5(getCurrentDate());
        // Create URL
        urlTest = String.format("%s%s",urlTests, hashedDate);
        Log.i("QuizNetwork", String.format("Returning url address for tests: %s", urlTest));

        return urlTest;

    }

    public static String getUrlQuestion(int id_test) {
        String hashedDate, urlQuestion;
        // Hash current date
        hashedDate = getMd5(getCurrentDate());
        // Create URL
        urlQuestion = String.format("%s%s?id_test=%d", urlQuestions, hashedDate, id_test);
        Log.i("QuizNetwork", String.format("Returning url address for questions: %s", urlQuestion));

        return urlQuestion;
    }

    public static ArrayList<QuizTest> parseJsonTests(String jsonTests) {
        ArrayList<QuizTest> testsList = new ArrayList<QuizTest>();
        if (jsonTests != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonTests);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject t = jsonArray.getJSONObject(i);

                    int id = t.getInt(QuizTest.TAG_ID);
                    int pocet_otazek = t.getInt(QuizTest.TAG_NUM_OF_QUESTIONS);
                    String nazev = null;
                    try {
                        nazev = new String(t.getString(QuizTest.TAG_TITLE).getBytes(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    int pocet_opakovani = t.getInt(QuizTest.TAG_NUM_OF_REPEATS);
                    int limit = t.getInt(QuizTest.TAG_LIMIT);
                    int zaverecny = t.getInt(QuizTest.TAG_FINAL);
                    int uspesnost = t.getInt(QuizTest.TAG_SUCCESS_RATE);

                    QuizTest test = new QuizTest(id, pocet_otazek, nazev, pocet_opakovani, limit, zaverecny, uspesnost);

                    testsList.add(test);
                }


                return testsList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("QuizServiceHandler", "Couldn't get any data from the URL");
            return null;
        }
    }

    public static ArrayList<QuizOtazka>parseJsonQuestions(String jsonQuestions) {
        ArrayList<QuizOtazka> questionsList = new ArrayList<QuizOtazka>();
        if (jsonQuestions != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonQuestions);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject otazka = jsonArray.getJSONObject(i);
                    JSONObject otazka_object = otazka.getJSONObject(QuizOtazka.TAG_QUESTION);
                    int id_otazka = otazka_object.getInt(QuizOtazka.TAG_ID_QUESTION);
                    String otazka_text = otazka_object.getString(QuizOtazka.TAG_QUESTION_TEXT);
                    String ok_otazka = otazka_object.getString(QuizOtazka.TAG_OK);
                    int typ_otazka = otazka_object.getInt(QuizOtazka.TAG_QUESTION_TYPE);

                    JSONArray odpovedi = otazka.getJSONArray(QuizOdpoved.TAG_ANSWERS);
                    ArrayList<QuizOdpoved> odpovedList = new ArrayList<QuizOdpoved>();
                    for (int j = 0; j < odpovedi.length(); j++) {
                        JSONObject odpoved = odpovedi.getJSONObject(j);

                        int id_ot = odpoved.getInt(QuizOdpoved.TAG_ID_QUESTION);
                        String odpoved_text = odpoved.getString(QuizOdpoved.TAG_ANSWER_TEXT);
                        int ok_odpoved = odpoved.getInt(QuizOdpoved.TAG_OK);
                        int zobrazit = odpoved.getInt(QuizOdpoved.TAG_DISPLAY);
                        int otazka_id_otazka = odpoved.getInt(QuizOdpoved.TAG_QUSTION);
                        int typ_otazka_id = odpoved.getInt(QuizOdpoved.TAG_QUESTION_TYPE);

                        QuizOdpoved quizOdpoved = new QuizOdpoved(id_ot, otazka_id_otazka, typ_otazka_id, odpoved_text, ok_odpoved, zobrazit );
                        odpovedList.add(quizOdpoved);
                    }

                    QuizOtazka quizOtazka = new QuizOtazka(odpovedList, id_otazka, otazka_text, ok_otazka, typ_otazka);
                    questionsList.add(quizOtazka);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return questionsList;
    }


    private static String getCurrentDate() {
        String currentDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        currentDate = dateFormat.format(new Date());
        Log.i("QuizNetwork", String.format("Current date: %s",currentDate));

        return currentDate;
    }

    private static String getMd5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm MD5 not supported.", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }
}
