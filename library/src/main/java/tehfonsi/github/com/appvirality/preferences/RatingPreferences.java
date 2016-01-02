package tehfonsi.github.com.appvirality.preferences;

import android.content.Context;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingPreferences extends AppViralityPreferences {

    public RatingPreferences(Context mContext) {
        super(mContext);
    }

    @Override
    String getPreferenceName() {
        return "com.github.tehfonsi.appvirality.rating";
    }

    public boolean didUserRate() {
        return getPreferences().getBoolean("pref_user_rated", false);
    }

    public void userRated() {
        getEditablePreferences().putBoolean("pref_user_rated", true).apply();
    }
}
