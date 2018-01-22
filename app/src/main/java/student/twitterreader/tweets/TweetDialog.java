package student.twitterreader.tweets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import student.twitterreader.R;

/**
 * Created by tuliodesouza on 22/12/2017.
 */

public class TweetDialog extends Dialog {

    @BindView(R.id.edt_direct)
    EditText mDirectTweet;

    private Context mContext;
    private String mHashtag;

    public TweetDialog(@NonNull Context context, String hashtag) {
        super(context);
        mContext = context;
        mHashtag = hashtag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_dialog);
        ButterKnife.bind(this);

        mDirectTweet.setText(mHashtag);
    }

    @OnClick(R.id.send_direct_btn)
    public void onDirectSendClick() {
        TweetService service = TweetService.getInstance();
        String tweet = "";
        try {
            tweet = URLEncoder.encode(mDirectTweet.getText().toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        service.tweetText(mContext, tweet);
        dismiss();
    }

    @OnClick(R.id.cancel_direct_btn)
    public void onDirectCancelClick() {
        dismiss();
    }
}
