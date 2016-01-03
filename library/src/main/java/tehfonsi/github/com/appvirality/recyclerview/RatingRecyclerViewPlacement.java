package tehfonsi.github.com.appvirality.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import tehfonsi.github.com.appvirality.RatingDialogBuilder;
import tehfonsi.github.com.appvirality.RatingEmbeddedBuilder;

/**
 * Created by stephanschober on 03.01.16.
 */
public class RatingRecyclerViewPlacement {

    @NonNull
    private final RatingEmbeddedBuilder mRatingEmbeddedBuilder;
    @NonNull
    private final RecyclerView.Adapter mOriginalAdapter;


    public RatingRecyclerViewPlacement(@NonNull RatingEmbeddedBuilder ratingEmbeddedBuilder,@NonNull RecyclerView.Adapter originalAdapter) {
        this.mRatingEmbeddedBuilder = ratingEmbeddedBuilder;
        this.mOriginalAdapter = originalAdapter;
    }

    public int getItemCount() {
        int count = mOriginalAdapter.getItemCount();
        if (mRatingEmbeddedBuilder.shouldShow()) {
            count+=1;
        }
        return count;
    }

    public boolean isRatingPosition(int position) {
        return position == mRatingEmbeddedBuilder.getAdapterPosition() && mRatingEmbeddedBuilder.shouldShow();
    }

    public int getOriginalPosition(int position) {
        if (position > mRatingEmbeddedBuilder.getAdapterPosition() && mRatingEmbeddedBuilder.shouldShow()) {
            position-=1;
        }
        return position;
    }

    public View getView(ViewGroup parent) {
        return mRatingEmbeddedBuilder.getDefaultAdapterView(parent);
    }
}
