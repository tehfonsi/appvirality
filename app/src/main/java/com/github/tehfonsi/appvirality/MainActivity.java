package com.github.tehfonsi.appvirality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import tehfonsi.github.com.appvirality.RatingDialogBuilder;
import tehfonsi.github.com.appvirality.RatingEmbeddedBuilder;
import tehfonsi.github.com.appvirality.recyclerview.RatingRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    static final String FEEDBACK_MAIL = "teh.fonsi+appvirality@gmail.com";
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        SimpleRecyclerViewAdapter simpleRecylcerViewAdapter = new SimpleRecyclerViewAdapter(generateListItems(100));

        //ADAPTER
        final RatingEmbeddedBuilder adapterRatingEmbeddedBuilder = (RatingEmbeddedBuilder) new RatingEmbeddedBuilder(this)
                .withFeedbackMail(FEEDBACK_MAIL);
        adapterRatingEmbeddedBuilder.withAdapterPosition(5);

        RatingRecyclerViewAdapter ratingRecyclerViewAdapter = new RatingRecyclerViewAdapter(simpleRecylcerViewAdapter, adapterRatingEmbeddedBuilder);

        mRecyclerView.setAdapter(ratingRecyclerViewAdapter);

        //DIALOG
        final RatingDialogBuilder ratingDialogBuilder = (RatingDialogBuilder) new RatingDialogBuilder(this)
                .withFeedbackMail(FEEDBACK_MAIL)
                .withTimesShown(2);
        ratingDialogBuilder.withDelay(10);

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
                .withFeedbackMail(FEEDBACK_MAIL);

        LinearLayout embeddedContainer = (LinearLayout) findViewById(R.id.container_embedded);
        if (ratingEmbeddedBuilder.shouldShow()) {
            embeddedContainer.addView(ratingEmbeddedBuilder.getView(embeddedContainer));
        }
    }

    private List<String> generateListItems(int count) {
        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add("Recyclerview Item "+i);
        }

        return items;
    }
}
