package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import cz.czu.kit.krejci_soukenik.quizproject.R;


public class QuizMenuActivity extends Activity {

    ArrayAdapter<String> adapter;

    GridView gridQuizes;
    Button[] buttons;

    public static String showResults = null;
    public static String numQustions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_menu);

        gridQuizes = (GridView) findViewById(R.id.gridQuizes);
        gridQuizes.setAdapter(new QuizMenuButtonAdapter(this));
        /*buttons = new Button[20];
        for (Button button : buttons) {
            button.setText("Button");
            button.setPadding(10, 10, 10, 10);
        }
        //gridQuizes.set
        /*adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 80; i++) {
            adapter.add("dummy data " + i);

        }
        gridQuizes.setAdapter(adapter);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        showResults = sharedPrefs.getString("showResults", "Body");
        numQustions = sharedPrefs.getString("setNumQuestions", "2");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsActivity = new Intent(getBaseContext(), QuizSettingsActivity.class);
                startActivity(settingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
