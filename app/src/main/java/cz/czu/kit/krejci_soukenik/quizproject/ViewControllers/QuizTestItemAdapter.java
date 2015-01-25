package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOdpoved;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizTest;
import cz.czu.kit.krejci_soukenik.quizproject.R;


/**
 * Created by soukenik on 22/01/15.
 */

public class QuizTestItemAdapter extends BaseAdapter {
    private static int MAX_ANSWERS = 6;

    private Context context;
    private String question;
    private String[] answers;
    private ArrayList<QuizOtazka> questionsList;

    static class ViewHolder {
        TextView question;
        CheckBox[] answers;

        public ViewHolder(TextView question, CheckBox[] answers) {
            this.question = question;
            this.answers = answers;
        }
    }

    public QuizTestItemAdapter(Context context,  ArrayList<QuizOtazka> questionsList) {
        //super(context, R.layout.test_item, questionsList);
        this.context = context;
        //this.question = question;
        //this.answers = answers;
        this.questionsList = questionsList;

    }

    // Total number of things contained within the adapter
    @Override
    public int getCount() {
        return questionsList.size();
    }

    // Require for structure, not really used in my code.
    @Override
    public QuizOtazka getItem(int position) {
        return questionsList.get(position);
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {

        View testItemView = convertView;
        TextView question;
        CheckBox[] answers;
        final QuizOtazka ot = getItem(position);

        if (testItemView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            testItemView = inflater.inflate(R.layout.test_item, parent, false);
            question = (TextView)testItemView.findViewById(R.id.otazka);
            answers = new CheckBox[MAX_ANSWERS];
            for (int i = 1; i <= MAX_ANSWERS; i++) {
                String odpovedID = "odpoved"+i;
                int chID = testItemView.getResources().getIdentifier(odpovedID, "id", "cz.czu.kit.krejci_soukenik.quizproject");
                answers[(i-1)] = (CheckBox)testItemView.findViewById(chID);

            }
            testItemView.setTag(new ViewHolder(question, answers));
        } else {
            ViewHolder holder = (ViewHolder)testItemView.getTag();
            question = holder.question;
            answers = holder.answers;
            for (int i = 0; i < MAX_ANSWERS; i++) {
                answers[i].setOnCheckedChangeListener(null);
            }
        }
        //ViewHolder holder = (ViewHolder)testItemView.getTag();
        question.setText(ot.getOtazka());
        for (int i = 0; i < MAX_ANSWERS; i++) {
            CheckBox checkBox = answers[i];
            if (i < ot.getOdpvedi().size()) {
                String answer = ot.getOdpvedi().get(i).getText();
                checkBox.setId(ot.getOdpvedi().get(i).getIdOtazka());
                checkBox.setText(answer);
                checkBox.setChecked(ot.getOdpvedi().get(i).isSelectedAnswer());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        QuizOdpoved selectedAnswer = null;
                        for (QuizOdpoved od : ot.getOdpvedi()) {
                            if (buttonView.getId() == od.getIdOtazka()) selectedAnswer = od;
                        }
                        try {
                            if (isChecked) {
                                selectedAnswer.setSelectedAnswer(true);
                                Log.d("QuizOdpoved", "Selected answer is: " + selectedAnswer.toString());
                            } else {
                                Log.d("QuizOdpoved", "Unselected answer is: " + selectedAnswer.toString());
                                selectedAnswer.setSelectedAnswer(false);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                //checkBox.setOnClickListener(new MyOnClickListener(checkBox, ot));
            } else {
                checkBox.setVisibility(testItemView.GONE);

            }
        }

        return testItemView;
    }


}

