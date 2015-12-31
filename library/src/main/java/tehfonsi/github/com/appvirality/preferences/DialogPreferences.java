package tehfonsi.github.com.appvirality.preferences;

import android.content.Context;

/**
 * Created by stephanschober on 30.12.15.
 */
public class DialogPreferences extends AppViralityPreferences {

    public DialogPreferences(Context mContext) {
        super(mContext);
    }

    @Override
    String getPreferenceName() {
        return "com.github.tehfonsi.appvirality.dialog";
    }
}
