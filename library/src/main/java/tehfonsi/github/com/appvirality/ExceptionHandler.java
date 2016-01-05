package tehfonsi.github.com.appvirality;

import java.lang.Thread.UncaughtExceptionHandler;

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