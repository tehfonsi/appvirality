package tehfonsi.github.com.appvirality.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephanschober on 31.01.16.
 */
public class AppViralityUtils {

    public static List<AppPackage> getInstalledApps(Context context) {
        List<AppPackage> appPackages = new ArrayList<>();
        for (AppPackage appPackage: AppPackage.values()) {
            if (isAppInstalled(context, appPackage.toString())) {
                appPackages.add(appPackage);
            }
        }
        return appPackages;
    }

    // facebook: "com.facebook.katana"
    public static boolean isAppInstalled(Context context, String packageName) {
        try{
            ApplicationInfo info = context.getPackageManager().
                    getApplicationInfo(packageName, 0);
            return true;
        } catch( PackageManager.NameNotFoundException e ){
            return false;
        }
    }
}
