package cz.czu.kit.soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cz.czu.kit.soukenik.quizproject.R;
import cz.czu.kit.soukenik.quizproject.Model.QuizOdpoved;
import cz.czu.kit.soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.soukenik.quizproject.Model.QuizTest;

/**
 * Created by soukenik on 02/02/15.
 */
public class QuizFinishedTestActivity extends Activity implements View.OnClickListener {
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
        try{
            // Get the Bundle Object
            Bundle bundleObject = getIntent().getExtras();

            // Get ArrayList Bundle
            questionsList = (ArrayList<QuizOtazka>) bundleObject.
                    getSerializable("questions");

        } catch(Exception e){
            e.printStackTrace();
        }

        // Find View
        TextView title = (TextView) findViewById(R.id.quiz_title);
        questionsCount = (TextView)findViewById(R.id.quiz_questions_count);
        quizRepeating = (TextView)findViewById(R.id.quiz_repeating);
        quizSucess = (TextView)findViewById(R.id.quiz_success);
        questionsCountResult = (TextView) findViewById(R.id.quiz_q_count_result);
        quizRepeatingResult = (TextView) findViewById(R.id.quiz_repeating_result);
        quizSucessResult = (TextView) findViewById(R.id.quiz_success_result);
        testResult = (TextView)findViewById(R.id.resultTextView);

        // Set View
        Log.d("QuizLaunchTestActivity", "QuizTest" + quizTest.toString());
        title.setText(quizTest.getNazev());
        questionsCountResult.setText(""+quizTest.getPocetOtazek());
        quizRepeatingResult.setText(""+quizTest.getPocetOpakovani());

        getFinalTestResult();

        launchButton = (Button) findViewById(R.id.quiz_launch_button);
        launchButton.setText(getString(R.string.showCorrectAns));
        launchButton.setOnClickListener(this);

    }

    @Override
    public void onClick (View view) {
        Log.d("QuizLaunchTestActivity", "Clicked.");
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", questionsList);

        Intent intent = new Intent(view.getContext(), QuizTestActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("quizTest", quizTest);
        intent.putExtra("kShowCorrectAnswers", true);
        view.getContext().startActivity(intent);

    }

    public void getFinalTestResult() {
        int maxResult = questionsList.size();
        int earnedPoints = 0;
        int uspesnost = quizTest.getUspesnost();
        for (QuizOtazka ot : questionsList) {
            int point = 0;
            boolean wrongAnswer = false;
            if (!ot.isPrazdnaOdpoved()){
                for (QuizOdpoved od : ot.getOdpvedi()){
                    if (od.isSelectedAnswer() && od.getOk() == 1){
                        point = 1;
                    } else if (od.isSelectedAnswer() && od.getOk() == 0) {
                        wrongAnswer = true;
                    } else if (!od.isSelectedAnswer() && od.getOk() == 1) {
                        wrongAnswer = true;
                    }
                }
            }
            if (!wrongAnswer) earnedPoints += point;
        }
        int rate = (earnedPoints / maxResult) * 100;
        if (QuizMenuActivity.showResults.equals("Body")){
            quizSucessResult.setText(""+earnedPoints+" / "+maxResult);
        } else {
            quizSucessResult.setText(""+rate+"%");
        }
        if (rate >= uspesnost) {
            testResult.setText("You passed :)");
            testResult.setTextColor(Color.GREEN);
        } else {
            testResult.setText("You failed :(");
            testResult.setTextColor(Color.RED);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuizFinishedTestActivity.this, QuizMenuActivity.class);
        startActivity(intent);
    }

}
