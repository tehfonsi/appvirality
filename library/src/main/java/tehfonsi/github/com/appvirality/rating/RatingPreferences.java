package tehfonsi.github.com.appvirality.rating;

import android.content.Context;

import tehfonsi.github.com.appvirality.AppViralityPreferences;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingPreferences extends AppViralityPreferences {

    public RatingPreferences(Context mContext) {
        super(mContext);
    }

    @Override
    public String getPreferenceName() {
        return "com.github.tehfonsi.appvirality.rating";
    }

}
