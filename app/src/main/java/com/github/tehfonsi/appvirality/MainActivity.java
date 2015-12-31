package com.github.tehfonsi.appvirality;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tehfonsi.github.com.appvirality.RatingDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RatingDialogBuilder ratingDialogBuilder = new RatingDialogBuilder(this);

        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialogBuilder.reset();
            }
        });

        Button crashButton = (Button) findViewById(R.id.button_crash);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("Crash!");
            }
        });

        ratingDialogBuilder.showDialogFragment(getSupportFragmentManager(), R.style.AppTheme);
    }
}
