package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.util.Log;

/**
 * Created by stephanschober on 31.12.15.
 */
public class AbstractBuilder {

    private final String TAG  = AbstractBuilder.class.getSimpleName();
    private AppViralityPreferences mPreferences;

    private int mTimesShown = -1;
    private int mMinStartCount = 3;
    private int mMinStartCountWithoutCrash = 3;
    private String mFeedbackMail;
    private CustomCondition mAdditionalCustomCondition;
    private CustomCondition mCustomOnlyCondition;

    public AbstractBuilder(Context mContext, AppViralityPreferences preferences) {
        this.mPreferences = preferences;

        initExceptionHandler();
    }

    /**
     * Add an additional condition which will be checked along the default conditions
     * @param customCondition
     * @return
     */
    public AbstractBuilder withAdditionalCustomCondition(CustomCondition customCondition) {
        this.mAdditionalCustomCondition = customCondition;
        return this;
    }

    /**
     * This is the only condition which will be checked
     * @param customCondition
     * @return
     */
    public AbstractBuilder withCustomConditionOnly(CustomCondition customCondition) {
        this.mCustomOnlyCondition = customCondition;
        return this;
    }

    /**
     * Define the minimum number of starts before anything is shown
     * @param minStartCount
     * @return
     */
    public AbstractBuilder withMinStartCount(int minStartCount) {
        this.mMinStartCount = minStartCount;
        return this;
    }

    /**
     * Define the minimum number of starts, without a preceding crash, before anything is shown
     * @param minStartCountWithoutCrash
     * @return
     */
    public AbstractBuilder withMinStartCountWithoutCrash(int minStartCountWithoutCrash) {
        this.mMinStartCountWithoutCrash = minStartCountWithoutCrash;
        return this;
    }

    /**
     * Define how often anything should be shown, default is indefinite
     * @param timesShown
     * @return
     */
    public AbstractBuilder withTimesShown(int timesShown) {
        this.mTimesShown = timesShown;
        return this;
    }

    /**
     * Define a feedback mail, only used for rating/feedback logic
     * @param feedbackMail
     * @return
     */
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

    /**
     * Check if all conditions are met
     * @return
     */
    public boolean shouldShow() {
        if (mCustomOnlyCondition != null) {
            return mCustomOnlyCondition.shouldShow();
        }

        boolean startCountCondition = false;
        boolean startCountWithoutCrashCondition = false;
        boolean timesShownCondition = false;
        boolean customCondition = false;

        if (mPreferences.getStartCount() >= mMinStartCount) {
            Log.v(TAG, mPreferences.getPreferenceName()+": startCountCondition");
            startCountCondition = true;
        }

        if (mPreferences.getStartCountWithoutCrash() >= mMinStartCountWithoutCrash) {
            Log.v(TAG, mPreferences.getPreferenceName()+": startCountWithoutCrashCondition");
            startCountWithoutCrashCondition = true;
        }

        if (mPreferences.getTimesShown() < mTimesShown || mTimesShown < 0) {
            Log.v(TAG, mPreferences.getPreferenceName()+": timesShownCondition");
            timesShownCondition = true;
        }

        if (mAdditionalCustomCondition == null) {
            customCondition = true;
        } else {
            customCondition = mAdditionalCustomCondition.shouldShow();
            Log.v(TAG, mPreferences.getPreferenceName()+": additionalCustomCondition: "+customCondition);
        }

        return startCountCondition && startCountWithoutCrashCondition && timesShownCondition && customCondition;
    }

    /**
     * Increase the number of starts
     *
     * see {@link #withMinStartCount(int) withMinStartCount} and {@link #withMinStartCountWithoutCrash(int) withMinStartCountWithoutCrash}
     */
    public void increaseStartCount() {
        mPreferences.increaseStartCount();
        mPreferences.increaseStartCountWithoutCrash();
    }

    protected AppViralityPreferences getPreferences() {
        return mPreferences;
    }

    protected String getFeedbackMail() {
        return mFeedbackMail;
    }

    public void reset() {
        mPreferences.resetAll();
    }

    public interface CustomCondition {
        boolean shouldShow();
    }

}
