package tehfonsi.github.com.appvirality.rating;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import tehfonsi.github.com.appvirality.R;
import tehfonsi.github.com.appvirality.utils.CommonUtils;

/**
 * Created by stephanschober on 02.01.16.
 */
public class RatingViewHelper {

    public static View getView(final ViewGroup parent, int resourceId, final String feedbackMail, final RatingPreferences preferences, final RatingCallbackListener callbackListener) {
        return getView(parent.getContext(), parent, resourceId, feedbackMail, preferences, callbackListener);
    }

    public static View getView(final Context context, final ViewGroup parent, int resourceId, final String feedbackMail, final RatingPreferences preferences, final RatingCallbackListener callbackListener) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View appFeedbackView;
        if (parent == null) {
            appFeedbackView = layoutInflater.inflate(resourceId, null);
        } else {
            appFeedbackView = layoutInflater.inflate(resourceId, parent, false);
        }

        TextView whatDoYouThinkText;
        final LinearLayout containerQuestion;
        final LinearLayout containerReview;
        final LinearLayout containerFeedback;

        containerQuestion = (LinearLayout) appFeedbackView.findViewById(R.id.container_question);
        containerReview = (LinearLayout) appFeedbackView.findViewById(R.id.container_review);
        containerFeedback = (LinearLayout) appFeedbackView.findViewById(R.id.container_feedback);

        whatDoYouThinkText = (TextView) appFeedbackView.findViewById(R.id.text_what_do_you_think);
        whatDoYouThinkText.setText(String.format(context.getString(R.string.what_do_you_think), CommonUtils.getApplicationName(context)));

        View.OnClickListener noThanksClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerQuestion.setVisibility(View.GONE);
                containerFeedback.setVisibility(View.GONE);
                containerReview.setVisibility(View.GONE);

                preferences.resetAll();

                if (callbackListener != null) {
                    callbackListener.onFinished();
                    callbackListener.onCanceled();
                }
            }
        };

        Button feedbackPositiveButton = (Button) appFeedbackView.findViewById(R.id.button_feedback_positive);
        feedbackPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerQuestion.setVisibility(View.GONE);
                containerFeedback.setVisibility(View.GONE);
                containerReview.setVisibility(View.VISIBLE);

                if (callbackListener != null) {
                    callbackListener.onReviewClicked();
                }
            }
        });

        Button feedbackNegativeButton = (Button) appFeedbackView.findViewById(R.id.button_feedback_negative);
        feedbackNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListener != null) {
                    callbackListener.onFeedbackClicked();
                }
                if (feedbackMail == null) {
                    containerQuestion.setVisibility(View.GONE);
                    containerFeedback.setVisibility(View.GONE);
                    containerReview.setVisibility(View.GONE);

                    callbackListener.onFinished();
                } else {
                    containerQuestion.setVisibility(View.GONE);
                    containerFeedback.setVisibility(View.VISIBLE);
                    containerReview.setVisibility(View.GONE);
                }
            }
        });

        Button noThanksFeedbackButton = (Button) appFeedbackView.findViewById(R.id.button_feedback_no_thanks);
        Button noThanksReviewButton = (Button) appFeedbackView.findViewById(R.id.button_review_no_thanks);
        noThanksFeedbackButton.setOnClickListener(noThanksClickListener);
        noThanksReviewButton.setOnClickListener(noThanksClickListener);

        Button writeReviewButton = (Button) appFeedbackView.findViewById(R.id.button_leave_review);
        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No Play Store installed on device", Toast.LENGTH_SHORT).show();
                }

                preferences.userFinishedPositive();

                containerQuestion.setVisibility(View.GONE);
                containerFeedback.setVisibility(View.GONE);
                containerReview.setVisibility(View.GONE);

                if (callbackListener != null) {
                    callbackListener.onFinished();
                    callbackListener.onWriteReviewClicked();
                }
            }
        });

        Button sendFeedbackButton = (Button) appFeedbackView.findViewById(R.id.button_send_feedback);
        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", feedbackMail, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.feedback_subject));
                context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.feedback_chooser)));

                preferences.resetAll();

                containerQuestion.setVisibility(View.GONE);
                containerFeedback.setVisibility(View.GONE);
                containerReview.setVisibility(View.GONE);

                if (callbackListener != null) {
                    callbackListener.onSendFeedbackClicked();
                    callbackListener.onFinished();
                }
            }
        });

        return appFeedbackView;
    }

    public interface RatingCallbackListener {
        void onFinished();

        void onSendFeedbackClicked();

        void onCanceled();

        void onReviewClicked();

        void onFeedbackClicked();

        void onWriteReviewClicked();
    }
}
