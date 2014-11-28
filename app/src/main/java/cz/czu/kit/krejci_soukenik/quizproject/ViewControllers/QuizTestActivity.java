package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cz.czu.kit.krejci_soukenik.quizproject.R;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizTestActivity extends Activity {

    ArrayList<HashMap<String, String>> testList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_test);
        testList = new ArrayList<HashMap<String, String>>();
        ListView lv = (ListView) findViewById(R.id.list);

        ListAdapter adapter = new SimpleAdapter(QuizTestActivity.this, testList, R.layout.test_item, new String[] {"a", "b"}, new int[]{1,2,3});
        lv.setAdapter(adapter);

    }
}
