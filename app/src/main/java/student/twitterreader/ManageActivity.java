package student.twitterreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.tweet.SortUserDialog;
import student.twitterreader.tweet.TweetAdapter;
import student.twitterreader.tweet.TweetService;
import student.twitterreader.tweet.UserAdapter;
import twitter4j.User;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class ManageActivity extends AppCompatActivity {

    @BindView(R.id.usersList)
    RecyclerView mUsersListView;

    @BindView(R.id.manageHashtagTxt)
    TextView mHashtagTxt;

    TweetService mTweetService;
    List<User> mUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage);

        ButterKnife.bind(this);

        mTweetService = TweetService.getInstance();

        mHashtagTxt.setText(mTweetService.getCurrentSearch());
        mUsersListView.setLayoutManager(new LinearLayoutManager(this));

        mUsers = mTweetService.getCurrentUsers();

        UserAdapter adapter = new UserAdapter(this, mUsers);
        mUsersListView.setAdapter(adapter);
    }

    @OnClick(R.id.manageBtn)
    public void onSuffleClick() {

        Random random = new Random();
        int luckIndex = random.nextInt(mUsers.size());
        User luckUser = mUsers.get(luckIndex);

        SortUserDialog dialog = new SortUserDialog(this, luckUser);
        dialog.show();

    }
}
