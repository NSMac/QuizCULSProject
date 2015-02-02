package cz.czu.kit.soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cz.czu.kit.soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.soukenik.quizproject.R;
import cz.czu.kit.soukenik.quizproject.Model.QuizTest;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizTestActivity extends Activity implements View.OnClickListener {

    ArrayList<QuizOtazka> questionsList;
    private Button continueButton;
    private Button quitButton;
    private List<QuizOtazka> subList;
    private int numberOfQuestions;
    private QuizTest quizTest;
    private boolean showCorrectAnswers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_test);
        ListView lv = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();
        quizTest = (QuizTest) intent.getSerializableExtra("quizTest");
        showCorrectAnswers = (boolean) intent.getSerializableExtra("kShowCorrectAnswers");

        try{
            // Get the Bundle Object
            Bundle bundleObject = getIntent().getExtras();

            // Get ArrayList Bundle
            questionsList = (ArrayList<QuizOtazka>) bundleObject.
                    getSerializable("questions");

        } catch(Exception e){
            e.printStackTrace();
        }

        continueButton = (Button)findViewById(R.id.test_check_button);
        continueButton.setOnClickListener(this);
        if (!isListInTheEnd(getNumberOfQuestions(), questionsList)){
            continueButton.setText("Continue");
        }

        quitButton = (Button)findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (showCorrectAnswers){
                    Intent intent = new Intent(QuizTestActivity.this, QuizMenuActivity.class);
                    startActivity(intent);
                } else alertHandler();
            }
        });

        numberOfQuestions = getNumberOfQuestions();
        if (showCorrectAnswers){
            continueButton.setVisibility(View.GONE);
        }

        subList = getSubListOfQuestions(numberOfQuestions, questionsList);
        ListAdapter adapter = new QuizTestItemAdapter(QuizTestActivity.this, subList, showCorrectAnswers);
        lv.setAdapter(adapter);

    }

    public void alertHandler() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizTestActivity.this);
        alertDialogBuilder.setMessage(R.string.decision);
        alertDialogBuilder.setPositiveButton(R.string.positive_button,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(QuizTestActivity.this, QuizMenuActivity.class);
                        startActivity(intent);

                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.negative_button,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        if (numberOfQustions == 4 || showCorrectAnswers) {
            return fullList;
        } else {
            int endNum = index + numberOfQustions;
            if (endNum > fullList.size()) endNum = fullList.size();
            if (fullList.get(index).isFilledAnswers() || fullList.get(index).isPrazdnaOdpoved()) {
                for (QuizOtazka ot : fullList) {
                    index++;
                    endNum = index + numberOfQustions;
                    if (ot.isFilledAnswers() || ot.isPrazdnaOdpoved()) {
                        rangeList = fullList.subList(index, endNum);
                    }

                }
            } else {
                rangeList = fullList.subList(index, endNum);
            }
            try {
                Log.d("QuizTest", "SubList: "+rangeList.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rangeList;
        }

    }

    @Override
    public void onBackPressed() {
        if (showCorrectAnswers) {
            super.onBackPressed();
        } else alertHandler();
        return;
    }

    public boolean isListInTheEnd(int numberOfQuestions, ArrayList<QuizOtazka> fullList) {
        int index = 0;
        for (QuizOtazka ot : fullList) {
            if (ot.isFilledAnswers() || ot.isPrazdnaOdpoved()) {
                index++;
            }
        }
        if ((index+numberOfQuestions) >= fullList.size()) return true;
        else return false;

    }

    private void setPrazdneOdpovedi(){
        for (QuizOtazka ot : subList) {
            if (!ot.isFilledAnswers()){
                ot.setPrazdnaOdpoved(true);
            }
        }
    }

    @Override
    public void onClick (View view) {
        setPrazdneOdpovedi();
        Log.d("QuizTestActivity", "Clicked.");
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", questionsList);

        if (isListInTheEnd(numberOfQuestions, questionsList)) {
            Intent intent = new Intent(view.getContext(), QuizFinishedTestActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("quizTest", quizTest);
            intent.putExtra("kShowCorrectAnswers", showCorrectAnswers);
            view.getContext().startActivity(intent);
        } else {
            Intent intent = new Intent(view.getContext(), QuizTestActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("quizTest", quizTest);
            intent.putExtra("kShowCorrectAnswers", showCorrectAnswers);
            view.getContext().startActivity(intent);
        }


    }

}
