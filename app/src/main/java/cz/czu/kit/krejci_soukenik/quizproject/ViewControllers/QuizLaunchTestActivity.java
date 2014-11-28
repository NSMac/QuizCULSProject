package cz.czu.kit.krejci_soukenik.quizproject.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cz.czu.kit.krejci_soukenik.quizproject.R;

/**
 * Created by soukenik on 28/11/14.
 */
public class QuizLaunchTestActivity extends Activity implements View.OnClickListener {

    private Button launchButton;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_launch_test);

        launchButton = (Button) findViewById(R.id.quiz_launch_button);
        launchButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        view.getContext().startActivity(new Intent(view.getContext(), QuizTestActivity.class));
    }


}
