package tehfonsi.github.com.appvirality.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

/**
 * Created by stephanschober on 30.12.15.
 */
public class RatingDialogFragment extends DialogFragment {

    private int mTheme = 0;

    public static RatingDialogFragment newInstance(int theme) {
        RatingDialogFragment ratingDialogFragment = new RatingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("theme", theme);
        ratingDialogFragment.setArguments(bundle);
        return ratingDialogFragment;
    }

    public RatingDialogFragment() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTheme = arguments.getInt("theme");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mTheme == 0) {
            mTheme = android.R.style.Theme_Dialog;
        }
        return new AlertDialog.Builder(getActivity(), mTheme)
//                .setTitle("")
                .setMessage("test")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                })
                .create();
    }
}
