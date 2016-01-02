package tehfonsi.github.com.appvirality.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;

import tehfonsi.github.com.appvirality.R;
import tehfonsi.github.com.appvirality.preferences.RatingPreferences;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingDialogFragment extends DialogFragment implements RatingViewHelper.RatingCallbackListener {

    private int mTheme = 0;
    private String mFeedbackMail;

    private AppCompatDialog mDialog;

    public static RatingDialogFragment newInstance(int theme, String feedbackMail) {
        RatingDialogFragment ratingDialogFragment = new RatingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("theme", theme);
        bundle.putString("mail", feedbackMail);
        ratingDialogFragment.setArguments(bundle);
        return ratingDialogFragment;
    }

    public RatingDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTheme = arguments.getInt("theme");
            mFeedbackMail = arguments.getString("mail");
        }

        if (mTheme == 0) {
            mDialog = new AppCompatDialog(getContext(), R.style.AppViralityDialogStyle);
        } else {
            mDialog = new AppCompatDialog(getContext(), mTheme);
        }

        mDialog.setContentView(RatingViewHelper.getView(getContext(), mFeedbackMail,new RatingPreferences(getContext()), this));

        return mDialog;
    }

    @Override
    public void onFinished() {
        mDialog.dismiss();
    }

    @Override
    public void onSendFeedbackClicked() {

    }

    @Override
    public void onCanceled() {

    }

    @Override
    public void onReviewClicked() {

    }

    @Override
    public void onFeedbackClicked() {

    }

    @Override
    public void onWriteReviewClicked() {

    }
}
