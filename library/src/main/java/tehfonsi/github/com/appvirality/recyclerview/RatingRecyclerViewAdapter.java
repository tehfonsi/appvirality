package tehfonsi.github.com.appvirality.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import tehfonsi.github.com.appvirality.RatingDialogBuilder;
import tehfonsi.github.com.appvirality.RatingEmbeddedBuilder;

/**
 * Created by stephanschober on 03.01.16.
 */
public class RatingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int RATING_ITEM_TYPE = -1337;

    @NonNull
    private final RatingRecyclerViewPlacement mRatingPlacement;
    @NonNull
    private final RecyclerView.Adapter mOriginalAdapter;

    public RatingRecyclerViewAdapter(@NonNull RecyclerView.Adapter originalAdapter,
                                     @NonNull RatingEmbeddedBuilder ratingEmbeddedBuilder) {
        this.mRatingPlacement = new RatingRecyclerViewPlacement(ratingEmbeddedBuilder, originalAdapter);
        this.mOriginalAdapter = originalAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RATING_ITEM_TYPE) {
            return new RatingEmbeddedViewHolder(mRatingPlacement.getView(parent));
        }

        return mOriginalAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mRatingPlacement.isRatingPosition(position)) {
            return;
        }

        mOriginalAdapter.onBindViewHolder(holder, mRatingPlacement.getOriginalPosition(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (mRatingPlacement.isRatingPosition(position)) {
            return RATING_ITEM_TYPE;
        }
        return super.getItemViewType(mRatingPlacement.getOriginalPosition(position));
    }

    @Override
    public int getItemCount() {
        return mRatingPlacement.getItemCount();
    }
}
