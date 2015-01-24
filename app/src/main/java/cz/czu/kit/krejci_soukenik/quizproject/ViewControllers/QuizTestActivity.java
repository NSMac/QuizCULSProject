package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOtazka;
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
        //questionsList = new ArrayList<QuizOtazka>();
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
        //Intent intent = getIntent();
        //questionsList = (ArrayList<QuizOtazka>) intent.getSerializableExtra("questions");

        ListAdapter adapter = new QuizTestItemAdapter(QuizTestActivity.this, questionsList);
//        ArrayAdapter<QuizOtazka> adapter = new ArrayAdapter<QuizOtazka>(this,
//                R.layout.test_item, R.id.otazka, questionsList);
        lv.setAdapter(adapter);
        //ListAdapter adapter = new SimpleAdapter(QuizTestActivity.this, testList, R.layout.test_item, new String[] {"a", "b"}, new int[]{1,2,3});
        //lv.setAdapter(adapter);

    }
}
