package cz.czu.kit.soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cz.czu.kit.soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.krejci_soukenik.quizproject.R;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizTestActivity extends Activity {

    ArrayList<QuizOtazka> questionsList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_test);
        ListView lv = (ListView) findViewById(R.id.list);
        try{
            // Get the Bundle Object
            Bundle bundleObject = getIntent().getExtras();

            // Get ArrayList Bundle
            questionsList = (ArrayList<QuizOtazka>) bundleObject.
                    getSerializable("questions");

        } catch(Exception e){
            e.printStackTrace();
        }

        List<QuizOtazka> subList = getSubListOfQuestions(getNumberOfQuestions(), questionsList);
        ListAdapter adapter = new QuizTestItemAdapter(QuizTestActivity.this, subList);
        lv.setAdapter(adapter);

    }

    public int getNumberOfQuestions() {
        String num = QuizMenuActivity.numQustions;
        int n = Integer.parseInt(num);
        Log.d("QuizTestActivity","Number of question is: "+num);
        return n;
    }

    public List<QuizOtazka> getSubListOfQuestions(int numberOfQustions,
                                                    ArrayList<QuizOtazka> fullList) {
        List<QuizOtazka> rangeList = null;
        int index = 0;
        if (numberOfQustions == 4) {
            return fullList;
        } else {
            int endNum = index + numberOfQustions;
            if (endNum > fullList.size()) endNum = fullList.size();
            if (!fullList.get(index).isFilledAnswers() || !fullList.get(index).isPrazdnaOdpoved()) {
                rangeList = fullList.subList(index, endNum);
            } else {
                for (QuizOtazka ot : fullList) {
                    if (ot.isFilledAnswers() || ot.isPrazdnaOdpoved()) {
                        rangeList = fullList.subList(index, endNum);
                    } else if (fullList.get(index).isFilledAnswers()){
                        rangeList = fullList.subList(index, endNum);
                    }
                    index++;
                }
            }
            Log.d("QuizTest", "SubList: "+rangeList.toString());
            return rangeList;
        }

    }

}
