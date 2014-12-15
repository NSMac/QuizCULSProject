package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import cz.czu.kit.krejci_soukenik.quizproject.Model.QuizTest;

/**
 * Created by soukenik on 09/11/14.
 */
public class QuizMenuButtonAdapter extends BaseAdapter  {
    private Context mContext;
    private String[] filenames;
    private ArrayList<QuizTest> testsList;

    // Gets the context so it can be used later
    public QuizMenuButtonAdapter(Context c, String[] filenames, ArrayList<QuizTest> testsList) {
        mContext = c;
        this.filenames = filenames;
        this.testsList = testsList;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return filenames.length;
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



    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setHeight(300);
            btn.setWidth(300);
            btn.setPadding(20, 20, 20, 20);
            btn.setOnClickListener(new MyOnClickListener(position, testsList));
        }
        else {
            btn = (Button) convertView;
        }
        //exus
        btn.setText(filenames[position]);
        // filenames is an array of strings
        btn.setTextColor(Color.WHITE);
        //btn.setBackgroundResource(R.drawable.button);
        btn.setId(position);
        //btn.setOnClickListener(;

        return btn;
    }
//
//    @Override
//    public void onClick (View view) {
//        Log.d("QuizMenuButtonAdapter", "Position: %d", this);
//        view.getContext().startActivity(new Intent(view.getContext(), QuizLaunchTestActivity.class));
//
//    }

    class MyOnClickListener implements View.OnClickListener
    {
        private final int position;
        private ArrayList<QuizTest> testsList ;

        public MyOnClickListener(int position, ArrayList<QuizTest> testsList)
        {
            this.position = position;
            this.testsList = testsList;
        }

        public void onClick(View view)
        {
            Log.d("QuizMenuButtonAdapter", "Position: " + this.position);
            Intent intent = new Intent(view.getContext(), QuizLaunchTestActivity.class);
            QuizTest quizTest = testsList.get(this.position);
            intent.putExtra("quizTest", quizTest);
            view.getContext().startActivity(intent);

        }
    }
}

