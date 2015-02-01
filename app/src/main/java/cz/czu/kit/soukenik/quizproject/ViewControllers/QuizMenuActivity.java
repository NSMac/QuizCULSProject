package cz.czu.kit.soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import cz.czu.kit.soukenik.quizproject.Model.QuizNetwork;
import cz.czu.kit.soukenik.quizproject.Model.QuizTest;
import cz.czu.kit.krejci_soukenik.quizproject.R;


public class QuizMenuActivity extends Activity {

    ArrayAdapter<String> adapter;
    private ProgressDialog pDialog;
    // Hashmap for ListView
    ArrayList<QuizTest> testsList;

    GridView gridQuizes;
    Button[] buttons;

    public static String showResults = null;
    public static String numQustions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_menu);

        gridQuizes = (GridView) findViewById(R.id.gridQuizes);

        if (savedInstanceState == null) {
            new GetAllTests().execute();
        } else {
            testsList = (ArrayList<QuizTest>)savedInstanceState.getSerializable("myTests");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        showResults = sharedPrefs.getString("showResults", "Body");
        numQustions = sharedPrefs.getString("setNumQuestions", "2");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("myTests", testsList);

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

    private class GetAllTests extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(QuizMenuActivity.this);
            pDialog.setMessage("Prosím čekejte...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Creating service handler class instance
            QuizServiceHandler sh = new QuizServiceHandler();

            // Making a request to url and getting response
            String jsonTests = sh.makeServiceCall(QuizNetwork.getUrlTest(), QuizServiceHandler.GET);
            Log.d("Response: ", "> " + jsonTests);

            testsList = QuizNetwork.parseJsonTests(jsonTests);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (testsList != null) {
                String[] filenames = new String[testsList.size()];
                int i = 0;
                for (QuizTest test : testsList) {
                    filenames[i++] = test.getNazev();
                }
                gridQuizes.setAdapter(new QuizMenuButtonAdapter(QuizMenuActivity.this, filenames, testsList));
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the URL");
            }
        }
    }
}
