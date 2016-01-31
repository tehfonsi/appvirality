package tehfonsi.github.com.appvirality.utils;

/**
 * Created by stephanschober on 31.01.16.
 */
public enum AppPackage {

    FACEBOOK("com.facebook.katana"),
    //    FACEBOOK_MESSENGER("com.facebook.orca"),
    TWITTER("com.twitter.android"),
//    GOOGLE_PLUS("com.google.android.apps.plus"),
//    YOUTUBE("com.google.android.youtube"),
    INSTAGRAM("com.instagram.android"),
    SNAPCHAT("com.snapchat.android"),
    TUMBLR("com.tumblr"),
    PINTEREST("com.pinterest"),
    LOVOO("net.lovoo.android"),
    BADOO("com.badoo.mobile"),
    WHATSAPP("com.whatsapp");

    private final String text;

    AppPackage(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
