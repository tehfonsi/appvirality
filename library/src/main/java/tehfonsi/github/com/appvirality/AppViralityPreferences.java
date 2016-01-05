package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by stephanschober on 30.12.15.
 */
public abstract class AppViralityPreferences {

    private static final String TAG = AppViralityPreferences.class.getSimpleName();

    private Context mContext;

    public AppViralityPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public abstract String getPreferenceName();

    protected SharedPreferences getPreferences() {
        return mContext.getSharedPreferences(getPreferenceName(), Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor getEditablePreferences() {
        return getPreferences().edit();
    }

    public int getStartCount() {
        return getPreferences().getInt("pref_start_count",0);
    }

    public void increaseStartCount() {
        getEditablePreferences().putInt("pref_start_count", getStartCount()+1).apply();
    }

    public int getStartCountWithoutCrash() {
        return getPreferences().getInt("pref_start_count_without_crash", 0);
    }

    public void increaseStartCountWithoutCrash() {
        getEditablePreferences().putInt("pref_start_count_without_crash", getStartCountWithoutCrash()+1).apply();
    }

    public void resetStartCountWithoutCrash() {
        getEditablePreferences().putInt("pref_start_count_without_crash", 0).apply();
    }

    public int getTimesShown() {
        return getPreferences().getInt("pref_times_shown", 0);
    }

    public void increaseTimesShown() {
        getEditablePreferences().putInt("pref_times_shown", getTimesShown()+1).apply();
    }

    public boolean didUserFinishPositive() {
        return getPreferences().getBoolean("pref_user_finished_positive", false);
    }

    public void userFinishedPositive() {
        getEditablePreferences().putBoolean("pref_user_finished_positive", true).apply();
    }

    public void resetAll() {
        getEditablePreferences().putInt("pref_times_shown",0).apply();
        getEditablePreferences().putInt("pref_start_count",0).apply();
        getEditablePreferences().putInt("pref_start_count_without_crash",0).apply();
    }
}
