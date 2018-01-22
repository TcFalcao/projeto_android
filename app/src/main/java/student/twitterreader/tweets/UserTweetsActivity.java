package student.twitterreader.tweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import student.twitterreader.R;
import twitter4j.User;

/**
 * Created by tuliodesouza on 21/01/18.
 */

public class UserTweetsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_tweet_list);

    ButterKnife.bind(this);

    User user = (User) getIntent().getExtras().getSerializable(UserTweetsFragment.KEY_USER);

    UserTweetsFragment fragment = new UserTweetsFragment();
    Bundle args = new Bundle();
    args.putSerializable(UserTweetsFragment.KEY_USER, user);
    fragment.setArguments(args);

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.user_tweets_fragment_container, fragment)
        .commit();

  }
}
