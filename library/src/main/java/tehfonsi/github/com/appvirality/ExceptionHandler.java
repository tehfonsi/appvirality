package tehfonsi.github.com.appvirality;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.Thread.UncaughtExceptionHandler;

import tehfonsi.github.com.appvirality.preferences.AppViralityPreferences;

public class ExceptionHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler mDefaultExceptionHandler;
    AppViralityPreferences mPreferences;

    public ExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler, AppViralityPreferences preferences) {
        mPreferences = preferences;
        mDefaultExceptionHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        mPreferences.resetStartCountWithoutCrash();

        // Call the original handler.
        mDefaultExceptionHandler.uncaughtException(thread, throwable);
    }
}