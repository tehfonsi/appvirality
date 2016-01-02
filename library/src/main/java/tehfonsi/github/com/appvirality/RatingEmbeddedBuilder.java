package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;

import tehfonsi.github.com.appvirality.preferences.RatingPreferences;
import tehfonsi.github.com.appvirality.utils.CommonUtils;
import tehfonsi.github.com.appvirality.views.RatingDialogFragment;
import tehfonsi.github.com.appvirality.views.RatingViewHelper;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingEmbeddedBuilder extends AbstractBuilder {

    public RatingEmbeddedBuilder(Context mContext) {
        super(mContext);
    }

    @Override
    public boolean shouldShow() {
        boolean didNotRateCondition = true;

        if (getPreferences().didUserRate()) {
            didNotRateCondition = false;
        }

        return didNotRateCondition && super.shouldShow();
    }

    public View getView(Context context) {
       if (shouldShow()) {
           getPreferences().increaseTimesShown();
           return RatingViewHelper.getView(context, getFeedbackMail(), new RatingPreferences(context), null);
       } else {
           return new View(context);
       }
    }
}
