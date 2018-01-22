package student.twitterreader.tweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import student.twitterreader.R;
import student.twitterreader.model.Tweet;
import twitter4j.User;

/**
 * Created by tuliodesouza on 21/01/18.
 */

public class UserTweetsFragment extends Fragment {

  public static final String KEY_USER = "key_user";

  @BindView(R.id.tweetsList)
  public RecyclerView mTweetsList;

  private User mUser;
  private List<Tweet> mUserTweets;

  public UserTweetsFragment() {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_tweet_list, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Bundle args = getArguments();

    TweetService service = TweetService.getInstance();

    mUser = (User) args.getSerializable(KEY_USER);
    mUserTweets = service.getUserTweets(mUser);

    mTweetsList.setLayoutManager(new LinearLayoutManager(getContext()));
    TweetAdapter adapter = new TweetAdapter(getContext(), mUserTweets);
    mTweetsList.setAdapter(adapter);

  }

}
