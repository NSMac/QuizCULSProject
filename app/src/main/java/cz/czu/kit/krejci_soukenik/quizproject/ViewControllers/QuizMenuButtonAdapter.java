package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by soukenik on 09/11/14.
 */
public class QuizMenuButtonAdapter extends BaseAdapter {
    private Context mContext;
    public String[] filenames;

    // Gets the context so it can be used later
    public QuizMenuButtonAdapter(Context c) {
        mContext = c;
        filenames = new String[]{
                "Test 1",
                "Test 2",
                "Test 3",
                "Test 4",
                "Test 5"
        };
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
            btn.setLayoutParams(new GridView.LayoutParams(200, 200));
            btn.setPadding(40, 20, 40, 20);
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

        return btn;
    }
}

