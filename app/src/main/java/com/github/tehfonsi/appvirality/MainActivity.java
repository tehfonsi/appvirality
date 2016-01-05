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

import tehfonsi.github.com.appvirality.AbstractBuilder;
import tehfonsi.github.com.appvirality.invite.InviteSnackbarBuilder;
import tehfonsi.github.com.appvirality.rating.RatingDialogBuilder;
import tehfonsi.github.com.appvirality.rating.RatingEmbeddedBuilder;
import tehfonsi.github.com.appvirality.rating.RatingRecyclerViewAdapter;

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

        //INVITE SNACKBAR

        InviteSnackbarBuilder inviteSnackbarBuilder = new InviteSnackbarBuilder(this);
        inviteSnackbarBuilder.withDelay(5);
        inviteSnackbarBuilder.withCustomConditionOnly(new AbstractBuilder.CustomCondition() {
            @Override
            public boolean shouldShow() {
                return true;
            }
        });
        inviteSnackbarBuilder.show(findViewById(android.R.id.content));

        //RATING ADAPTER
        final RatingEmbeddedBuilder adapterRatingEmbeddedBuilder = (RatingEmbeddedBuilder) new RatingEmbeddedBuilder(this)
                .withFeedbackMail(FEEDBACK_MAIL);
        adapterRatingEmbeddedBuilder.withAdapterPosition(5);

        RatingRecyclerViewAdapter ratingRecyclerViewAdapter = new RatingRecyclerViewAdapter(simpleRecylcerViewAdapter, adapterRatingEmbeddedBuilder);

        mRecyclerView.setAdapter(ratingRecyclerViewAdapter);

        //RATING DIALOG
        final RatingDialogBuilder ratingDialogBuilder = (RatingDialogBuilder) new RatingDialogBuilder(this)
                .withFeedbackMail(FEEDBACK_MAIL)
                .withTimesShown(2);
        ratingDialogBuilder.withDelay(10);

        ratingDialogBuilder.showDialogFragment(getSupportFragmentManager());


        //RATING EMBEDDED
        final RatingEmbeddedBuilder ratingEmbeddedBuilder = (RatingEmbeddedBuilder) new RatingEmbeddedBuilder(this)
                .withFeedbackMail(FEEDBACK_MAIL);

        LinearLayout embeddedContainer = (LinearLayout) findViewById(R.id.container_embedded);
        if (ratingEmbeddedBuilder.shouldShow()) {
            embeddedContainer.addView(ratingEmbeddedBuilder.getView(embeddedContainer));
        }


        //init buttons
        Button crashButton = (Button) findViewById(R.id.button_crash);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("Crash!");
            }
        });

        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingEmbeddedBuilder.reset();
                ratingDialogBuilder.reset();
                adapterRatingEmbeddedBuilder.reset();
            }
        });
    }

    private List<String> generateListItems(int count) {
        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add("Recyclerview Item "+i);
        }

        return items;
    }
}
