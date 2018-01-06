package student.twitterreader.tweet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.R;
import twitter4j.User;

/**
 * Created by rjbf2 on 22/04/2017.
 */

public class SortUserDialog extends Dialog {

    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.fullNameTxt)
    TextView fullName;
    @BindView(R.id.userNameTxt)
    TextView userName;

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
    }
}
