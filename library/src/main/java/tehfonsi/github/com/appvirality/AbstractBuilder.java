package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.util.Log;

import tehfonsi.github.com.appvirality.preferences.AppViralityPreferences;
import tehfonsi.github.com.appvirality.preferences.DialogPreferences;

/**
 * Created by stephanschober on 31.12.15.
 */
public class AbstractBuilder {

    private final String TAG  = RatingDialogBuilder.class.getSimpleName();
    private AppViralityPreferences mPreferences;

    private int mTimesShown = 1;
    private int mMinStartCount = 3;
    private int mMinStartCountWithoutCrash = 3;

    public AbstractBuilder(Context mContext) {
        this.mPreferences = new DialogPreferences(mContext);

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

    private void initExceptionHandler() {
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();

        // Don't register again if already registered.
        if (!(currentHandler instanceof ExceptionHandler)) {

            // Register default exceptions handler.
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(currentHandler, mPreferences));
        }
    }

    public boolean shouldShowDialog() {
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

        if (mPreferences.getTimesShown() < mTimesShown) {
            Log.v(TAG, "timesShownCondition");
            timesShownCondition = true;
        }

        return startCountCondition && startCountWithoutCrashCondition && timesShownCondition;
    }

    protected void increaseStartCount() {
        mPreferences.increaseStartCount();
        mPreferences.increaseStartCountWithoutCrash();
    }

    protected AppViralityPreferences getPreferences() {
        return mPreferences;
    }

    public void reset() {
        mPreferences.resetAll();
    }

}
