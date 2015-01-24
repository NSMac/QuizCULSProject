package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOdpoved;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizOtazka;
import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizTest;
import cz.czu.kit.krejci_soukenik.quizproject.R;


/**
 * Created by soukenik on 22/01/15.
 */

public class QuizTestItemAdapter extends BaseAdapter {
    private Context context;
    private String question;
    private String[] answers;
    private ArrayList<QuizOtazka> questionsList;

    static class ViewHolder {
        TextView question;
        RadioButton aButton;
        RadioButton bButton;
        RadioButton cButton;
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
        if (testItemView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            testItemView = inflater.inflate(R.layout.test_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.question = (TextView)testItemView.findViewById(R.id.otazka);
            viewHolder.aButton = (RadioButton)testItemView.findViewById(R.id.odpovedA);
            viewHolder.bButton = (RadioButton)testItemView.findViewById(R.id.odpovedB);
            viewHolder.cButton = (RadioButton)testItemView.findViewById(R.id.odpovedC);
            testItemView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder)testItemView.getTag();
        QuizOtazka ot = questionsList.get(position);
        QuizOdpoved a = ot.getOdpvedi().get(0);
        QuizOdpoved b = ot.getOdpvedi().get(1);
        QuizOdpoved c = ot.getOdpvedi().get(2);

        holder.question.setText(ot.getOtazka());
        holder.aButton.setText(a.getText());
        holder.bButton.setText(b.getText());
        holder.cButton.setText(c.getText());

        return testItemView;
    }


}

