package student.twitterreader.users;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.R;
import student.twitterreader.tweets.TweetService;
import twitter4j.User;

/**
 * Created by tuliodesouza on 22/12/2017.
 */

public class SortUserDialog extends Dialog {

    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.fullNameTxt)
    TextView fullName;
    @BindView(R.id.userNameTxt)
    TextView userName;
    @BindView(R.id.edt_direct)
    EditText mDirectTweet;

    private Context mContext;
    private User mUser;

    public SortUserDialog(@NonNull Context context, User user) {
        super(context);
        mContext = context;
        mUser = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_dialog);
        ButterKnife.bind(this);

        Picasso.with(mContext).load(mUser.getProfileImageURL()).into(profileImage);

        fullName.setText(mUser.getName());
        userName.setText("@" + mUser.getScreenName());

        mDirectTweet.setText("@" + mUser.getScreenName() + " " + mContext.getString(R.string.congrat_direct));
    }

    @OnClick(R.id.send_direct_btn)
    public void onDirectSendClick() {
        TweetService service = TweetService.getInstance();
        service.tweetText(mContext, mDirectTweet.getText().toString());
        dismiss();
    }

    @OnClick(R.id.cancel_direct_btn)
    public void onDirectCancelClick() {
        dismiss();
    }
}
