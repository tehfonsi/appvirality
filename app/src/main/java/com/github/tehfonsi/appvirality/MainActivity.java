package com.github.tehfonsi.appvirality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import tehfonsi.github.com.appvirality.RatingDialogBuilder;
import tehfonsi.github.com.appvirality.RatingEmbeddedBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DIALOG
        final RatingDialogBuilder ratingDialogBuilder = (RatingDialogBuilder) new RatingDialogBuilder(this)
                .withFeedbackMail("teh.fonsi+appvirality@gmail.com");

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

        ratingDialogBuilder.showDialogFragment(getSupportFragmentManager());


        //EMBEDDED
        final RatingEmbeddedBuilder ratingEmbeddedBuilder = (RatingEmbeddedBuilder) new RatingEmbeddedBuilder(this)
                .withFeedbackMail("teh.fonsi+appvirality@gmail.com");

        LinearLayout embeddedContainer = (LinearLayout) findViewById(R.id.container_embedded);
        if (ratingEmbeddedBuilder.shouldShow()) {
            embeddedContainer.addView(ratingEmbeddedBuilder.getView(this));
        }
    }
}
