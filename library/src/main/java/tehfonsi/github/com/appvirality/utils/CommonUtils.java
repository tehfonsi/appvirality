package tehfonsi.github.com.appvirality.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by stephanschober on 02.01.16.
 */
public class CommonUtils {

    /**
     * @param context A context of the current application.
     * @return The application name of the current application.
     */
    public static final String getApplicationName(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "(unknown)");
    }

    public static final String getPlayStoreUrl(Context context) {
        return "https://play.google.com/store/apps/details?id="+context.getPackageName();
    }
}
