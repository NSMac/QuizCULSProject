package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizNetwork;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizTest;
import cz.czu.kit.krejci_soukenik.quizproject.R;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizLaunchTestActivity extends Activity implements View.OnClickListener {

    private ProgressDialog pDialog;
    private Button launchButton;
    private ArrayList<QuizOtazka> questionsList;
    private QuizTest quizTest;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_launch_test);

        Intent intent = getIntent();

        quizTest = (QuizTest) intent.getSerializableExtra("quizTest");

        TextView title = (TextView) findViewById(R.id.quiz_title);
        TextView questionsCount = (TextView) findViewById(R.id.quiz_q_count_result);
        TextView repeating = (TextView) findViewById(R.id.quiz_repeating_result);
        TextView success = (TextView) findViewById(R.id.quiz_success_result);

        title.setText(quizTest.getNazev());
        //questionsCount.setText(quizTest.getPocetOtazek());
        //repeating.setText(quizTest.getPocetOpakovani());
        //success.setText(quizTest.getUspesnost());

        launchButton = (Button) findViewById(R.id.quiz_launch_button);
        launchButton.setOnClickListener(this);

        new GetQuestions().execute();
    }

    @Override
    public void onClick (View view) {
        new GetQuestions().execute();
        Log.d("QuizLaunchTestActivity", "Clicked.");
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", questionsList);

        Intent intent = new Intent(view.getContext(), QuizTestActivity.class);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);

    }

    private class GetQuestions extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(QuizLaunchTestActivity.this);
            pDialog.setMessage("Prosím čekejte...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Creating service handler class instance
            QuizServiceHandler sh = new QuizServiceHandler();

            // Making a request to url and getting response
            String jsonQuestions = sh.makeServiceCall(QuizNetwork.getUrlQuestion(quizTest.getId()), QuizServiceHandler.GET);
            Log.d("Response: ", "> " + jsonQuestions);

            questionsList = QuizNetwork.parseJsonQuestions(jsonQuestions);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (questionsList != null) {
                Log.d("QuizLaunchTestActivity", questionsList.toString());
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the URL");
            }
        }
    }
}
