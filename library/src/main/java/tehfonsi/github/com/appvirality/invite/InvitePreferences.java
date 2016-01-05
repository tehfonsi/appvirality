package tehfonsi.github.com.appvirality.invite;

import android.content.Context;

import tehfonsi.github.com.appvirality.AppViralityPreferences;

/**
 * Created by stephanschober on 05.01.16.
 */
public class InvitePreferences extends AppViralityPreferences {

    public InvitePreferences(Context mContext) {
        super(mContext);
    }

    @Override
    public String getPreferenceName() {
        return "com.github.tehfonsi.appvirality.invite";
    }
}
