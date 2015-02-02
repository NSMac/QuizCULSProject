package cz.czu.kit.soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cz.czu.kit.soukenik.quizproject.Model.QuizNetwork;
import cz.czu.kit.soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.soukenik.quizproject.Model.QuizTest;
import cz.czu.kit.soukenik.quizproject.R;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizLaunchTestActivity extends Activity implements View.OnClickListener {

    private ProgressDialog pDialog;

    private Button launchButton;
    private ArrayList<QuizOtazka> questionsList;
    private QuizTest quizTest;
    private TextView questionsCount;
    private TextView quizRepeating;
    private TextView quizSucess;
    private TextView questionsCountResult;
    private TextView quizRepeatingResult;
    private TextView quizSucessResult;
    private TextView testResult;


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_launch_test);

        Intent intent = getIntent();

        quizTest = (QuizTest) intent.getSerializableExtra("quizTest");
        // Get Tests
        new GetQuestions().execute();

        // Find View
        TextView title = (TextView) findViewById(R.id.quiz_title);
        questionsCount = (TextView)findViewById(R.id.quiz_questions_count);
        quizRepeating = (TextView)findViewById(R.id.quiz_repeating);
        quizSucess = (TextView)findViewById(R.id.quiz_success);
        questionsCountResult = (TextView) findViewById(R.id.quiz_q_count_result);
        quizRepeatingResult = (TextView) findViewById(R.id.quiz_repeating_result);
        quizSucessResult = (TextView) findViewById(R.id.quiz_success_result);
        testResult = (TextView)findViewById(R.id.resultTextView);
        testResult.setVisibility(View.GONE);

        // Set View
        Log.d("QuizLaunchTestActivity", "QuizTest"+quizTest.toString());
        title.setText(quizTest.getNazev());
        questionsCountResult.setText(""+quizTest.getPocetOtazek());
        quizRepeatingResult.setText(""+quizTest.getPocetOpakovani());
        quizSucessResult.setText(""+quizTest.getUspesnost()+"%");

        launchButton = (Button) findViewById(R.id.quiz_launch_button);
        launchButton.setOnClickListener(this);

        //new GetQuestions().execute();
    }

    @Override
    public void onClick (View view) {
        Log.d("QuizLaunchTestActivity", "Clicked.");
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", questionsList);

        Intent intent = new Intent(view.getContext(), QuizTestActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("quizTest", quizTest);
        intent.putExtra("kShowCorrectAnswers", false);
        view.getContext().startActivity(intent);

    }

    private class GetQuestions extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(QuizLaunchTestActivity.this);
            pDialog.setMessage(getString(R.string.wait));
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
                noConnectionAlertHandler();
            }
        }

        protected void noConnectionAlertHandler(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizLaunchTestActivity.this);
            alertDialogBuilder.setMessage(R.string.noConnection);
            alertDialogBuilder.setPositiveButton(R.string.again,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            new GetQuestions().execute();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
