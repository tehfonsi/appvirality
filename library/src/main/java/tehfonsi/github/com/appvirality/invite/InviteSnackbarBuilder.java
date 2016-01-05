package tehfonsi.github.com.appvirality.invite;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import tehfonsi.github.com.appvirality.AbstractBuilder;
import tehfonsi.github.com.appvirality.R;
import tehfonsi.github.com.appvirality.rating.RatingPreferences;
import tehfonsi.github.com.appvirality.utils.CommonUtils;

/**
 * Created by stephanschober on 05.01.16.
 */
public class InviteSnackbarBuilder extends AbstractBuilder {

    private int mDelay = 0;
    private int mDuration = Snackbar.LENGTH_INDEFINITE;

    public InviteSnackbarBuilder(Context mContext) {
        super(mContext, new InvitePreferences(mContext));
    }

    /**
     * If all conditions are met, after how many seconds should the snackbar be shown?
     *
     * @param seconds
     * @return
     */
    public InviteSnackbarBuilder withDelay(int seconds) {
        this.mDelay = seconds;
        return this;
    }

    /**
     * How long should the snackbar be shown?
     *
     * You can use default lengths like Snackbar.LENGTH_SHORT
     *
     * @param seconds
     * @return
     */
    public InviteSnackbarBuilder withDuration(int seconds) {
        this.mDuration = seconds;
        return this;
    }

    /**
     * Shows the snackbar if all conditions are met, uses default click handler
     *
     * @param view preferable a CoordinatorLayout
     */
    public void show(final View view) {
        final Context context = view.getContext();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIntent(context);
            }
        };
        show(view, clickListener);
    }

    /**
     * Shows the snackbar if all conditions are met
     * You can show your own share logic with the click listener
     *
     * @param view preferable a CoordinatorLayout
     * @param clickListener
     */
    public void show(final View view, final View.OnClickListener clickListener) {
        increaseStartCount();
        if (shouldShow()) {
            if (mDelay > 0) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showSnackbar(view, clickListener);
                    }
                }, (mDelay * 1000));
            } else {
                showSnackbar(view, clickListener);
            }
        }
    }

    private void showSnackbar(View view, View.OnClickListener clickListener) {
        Resources resources = view.getContext().getResources();
        Snackbar snackbar = Snackbar.make(view, resources.getString(R.string.invite_text),
                (mDuration > 0) ? (mDuration*1000) : mDuration);
        if (clickListener != null) {
            snackbar.setAction(resources.getString(R.string.invite_action_positive), clickListener);
        }
        snackbar.show();
        getPreferences().userFinishedPositive();
    }

    /**
     * Opens a share intent
     *
     * Can be used standalone, for example in a menu
     *
     * @param context
     */
    public static void shareIntent(Context context) {
        String text = String.format(
                context.getResources().getString(R.string.invite_default_text),
                CommonUtils.getApplicationName(context),
                CommonUtils.getPlayStoreUrl(context));
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getResources().getString(R.string.invite_via)));
    }
}
