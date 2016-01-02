package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import tehfonsi.github.com.appvirality.views.RatingDialogFragment;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingDialogBuilder extends AbstractBuilder {

    private int mDelay = 0;

    public RatingDialogBuilder(Context mContext) {
        super(mContext);
    }

    public RatingDialogBuilder withDelay(int seconds) {
        this.mDelay = seconds;
        return this;
    }

    public void showDialogFragment(FragmentManager fragmentManager) {
        showDialogFragment(fragmentManager, 0);
    }

    public void showDialogFragment(final FragmentManager fragmentManager, final int theme) {
        increaseStartCount();
        if (shouldShow()) {
            if (mDelay > 0) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        show(fragmentManager, theme);
                    }
                }, (mDelay * 1000));
            } else {
                show(fragmentManager, theme);
            }
        }
    }

    @Override
    public boolean shouldShow() {
        boolean didNotRateCondition = true;

        if (getPreferences().didUserRate()) {
            didNotRateCondition = false;
        }

        return didNotRateCondition && super.shouldShow();
    }

    private void show(FragmentManager fragmentManager, int theme) {
        if (fragmentManager.isDestroyed()) return;

        RatingDialogFragment.newInstance(theme, getFeedbackMail()).show(fragmentManager, "rating_dialog");
        getPreferences().increaseTimesShown();
    }
}
