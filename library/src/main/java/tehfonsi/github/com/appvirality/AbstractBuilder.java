package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.util.Log;

import tehfonsi.github.com.appvirality.preferences.AppViralityPreferences;
import tehfonsi.github.com.appvirality.preferences.RatingPreferences;

/**
 * Created by stephanschober on 31.12.15.
 */
public class AbstractBuilder {

    private final String TAG  = AbstractBuilder.class.getSimpleName();
    private RatingPreferences mPreferences;

    private int mTimesShown = -1;
    private int mMinStartCount = 3;
    private int mMinStartCountWithoutCrash = 3;
    private String mFeedbackMail;

    public AbstractBuilder(Context mContext) {
        this.mPreferences = new RatingPreferences(mContext);

        initExceptionHandler();
    }

    public AbstractBuilder withMinStartCount(int minStartCount) {
        this.mMinStartCount = minStartCount;
        return this;
    }

    public AbstractBuilder withMinStartCountWithoutCrash(int minStartCountWithoutCrash) {
        this.mMinStartCountWithoutCrash = minStartCountWithoutCrash;
        return this;
    }

    public AbstractBuilder withTimesShown(int timesShown) {
        this.mTimesShown = timesShown;
        return this;
    }

    public AbstractBuilder withFeedbackMail(String feedbackMail) {
        this.mFeedbackMail = feedbackMail;
        return this;
    }

    private void initExceptionHandler() {
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();

        // Don't register again if already registered.
        if (!(currentHandler instanceof ExceptionHandler)) {

            // Register default exceptions handler.
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(currentHandler, mPreferences));
        }
    }

    public boolean shouldShow() {
        boolean startCountCondition = false;
        boolean startCountWithoutCrashCondition = false;
        boolean timesShownCondition = false;

        if (mPreferences.getStartCount() >= mMinStartCount) {
            Log.v(TAG, "startCountCondition");
            startCountCondition = true;
        }

        if (mPreferences.getStartCountWithoutCrash() >= mMinStartCountWithoutCrash) {
            Log.v(TAG, "startCountWithoutCrashCondition");
            startCountWithoutCrashCondition = true;
        }

        if (mPreferences.getTimesShown() < mTimesShown || mTimesShown < 0) {
            Log.v(TAG, "timesShownCondition");
            timesShownCondition = true;
        }

        return startCountCondition && startCountWithoutCrashCondition && timesShownCondition;
    }

    protected void increaseStartCount() {
        mPreferences.increaseStartCount();
        mPreferences.increaseStartCountWithoutCrash();
    }

    protected RatingPreferences getPreferences() {
        return mPreferences;
    }

    protected String getFeedbackMail() {
        return mFeedbackMail;
    }

    public void reset() {
        mPreferences.resetAll();
    }

}
