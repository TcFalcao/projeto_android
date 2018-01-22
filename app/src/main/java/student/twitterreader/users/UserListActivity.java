package student.twitterreader.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.R;
import student.twitterreader.tweets.TweetService;
import student.twitterreader.tweets.UserTweetsActivity;
import student.twitterreader.tweets.UserTweetsFragment;
import student.twitterreader.users.UserAdapter.OnUserClickListener;
import twitter4j.User;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class UserListActivity extends AppCompatActivity implements OnUserClickListener {

    @BindView(R.id.usersList)
    RecyclerView mUsersListView;

    @BindView(R.id.manageHashtagTxt)
    TextView mHashtagTxt;

    private TweetService mTweetService;
    private List<User> mUsers;

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users_list);

        ButterKnife.bind(this);

        mTweetService = TweetService.getInstance();

        mHashtagTxt.setText(mTweetService.getCurrentSearch());
        mUsersListView.setLayoutManager(new LinearLayoutManager(this));

        mUsers = mTweetService.getCurrentUsers();

        View fragmentContainer = findViewById(R.id.user_tweets_fragment_container);
        isTwoPane = fragmentContainer != null;

        UserAdapter adapter = new UserAdapter(this, mUsers, this);
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

    @Override
    public void onUserClick(User user) {

        if (isTwoPane) {
            UserTweetsFragment fragment = new UserTweetsFragment();
            Bundle args = new Bundle();
            args.putSerializable(UserTweetsFragment.KEY_USER, user);
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                .replace(R.id.user_tweets_fragment_container, fragment)
                .commit();
        } else {
            Intent it = new Intent(this, UserTweetsActivity.class);
            it.putExtra(UserTweetsFragment.KEY_USER, user);
            startActivity(it);
        }
    }
}
