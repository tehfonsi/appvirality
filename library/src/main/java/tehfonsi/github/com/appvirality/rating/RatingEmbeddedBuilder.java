package tehfonsi.github.com.appvirality.rating;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import tehfonsi.github.com.appvirality.AbstractBuilder;
import tehfonsi.github.com.appvirality.R;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingEmbeddedBuilder extends AbstractBuilder {

    private int mAdapterPosition = 8;

    public RatingEmbeddedBuilder(Context mContext) {
        super(mContext, new RatingPreferences(mContext));
    }

    @Override
    public boolean shouldShow() {
        boolean didNotRateCondition = true;

        if (getPreferences().didUserFinishPositive()) {
            didNotRateCondition = false;
        }

        return didNotRateCondition && super.shouldShow();
    }

    public RatingEmbeddedBuilder withAdapterPosition(int position) {
        this.mAdapterPosition = position;
        return this;
    }

    public int getAdapterPosition() {
        return mAdapterPosition;
    }

    public View getDefaultAdapterView(ViewGroup parent) {
        if (shouldShow()) {
            getPreferences().increaseTimesShown();
            return RatingViewHelper.getView(parent, R.layout.layout_rating_recyclerview, getFeedbackMail(), new RatingPreferences(parent.getContext()), null);
        } else {
            return new View(parent.getContext());
        }
    }

    public View getView(ViewGroup parent) {
       if (shouldShow()) {
           getPreferences().increaseTimesShown();
           return RatingViewHelper.getView(parent, R.layout.layout_rating, getFeedbackMail(), new RatingPreferences(parent.getContext()), null);
       } else {
           return new View(parent.getContext());
       }
    }
}
