package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
    }

    public QuizTestItemAdapter(Context context,  ArrayList<QuizOtazka> questionsList) {
        //super(context, R.layout.test_item, questionsList);
        this.context = context;
        //this.question = question;
        //this.answers = answers;
        this.questionsList = questionsList;

    }

    // Total number of things contained within the adapter
    public int getCount() {
        return questionsList.size();
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {
        View testItemView = convertView;
        CheckBox checkBox;
        QuizOtazka ot = questionsList.get(position);
        if (testItemView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            testItemView = inflater.inflate(R.layout.test_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.question = (TextView)testItemView.findViewById(R.id.otazka);
            viewHolder.answers = new CheckBox[MAX_ANSWERS];
            for (int i = 1; i <= MAX_ANSWERS; i++) {
                String odpovedID = "odpoved"+i;
                int chID = testItemView.getResources().getIdentifier(odpovedID, "id", "cz.czu.kit.krejci_soukenik.quizproject");
                viewHolder.answers[(i-1)] = (CheckBox)testItemView.findViewById(chID);
            }

//            viewHolder.aButton = (RadioButton)testItemView.findViewById(R.id.odpovedA);
//            viewHolder.bButton = (RadioButton)testItemView.findViewById(R.id.odpovedB);
//            viewHolder.cButton = (RadioButton)testItemView.findViewById(R.id.odpovedC);
            testItemView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)testItemView.getTag();
        holder.question.setText(ot.getOtazka());
        for (int i = 0; i < MAX_ANSWERS; i++) {
            checkBox = holder.answers[i];
            if (i < ot.getOdpvedi().size()) {
                String answer = ot.getOdpvedi().get(i).getText();
                checkBox.setId(ot.getOdpvedi().get(i).getIdOtazka());
                checkBox.setText(answer);
            } else {
                checkBox.setVisibility(testItemView.GONE);

            }
        }


//        holder.aButton.setText(a.getText());
//        holder.bButton.setText(b.getText());
//        holder.cButton.setText(c.getText());

        return testItemView;
    }


}

