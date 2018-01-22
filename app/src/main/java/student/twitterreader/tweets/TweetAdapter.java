package student.twitterreader.tweets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import student.twitterreader.R;
import student.twitterreader.model.Tweet;
import twitter4j.User;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {

    private Context mContext;
    private List<Tweet> mTweets;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.mContext = context;
        this.mTweets = tweets;
    }

    @Override
    public TweetAdapter.TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tweet_item, parent, false);
        TweetViewHolder holder = new TweetViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(TweetAdapter.TweetViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);

        User user = tweet.getUser();

        Picasso.with(mContext).load(user.getProfileImageURL()).into(holder.profileImage);

        holder.fullName.setText(user.getName());
        holder.userName.setText("@" + user.getScreenName());
        holder.tweetTxt.setText(tweet.getTweet());
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public static class TweetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profileImage;
        @BindView(R.id.fullNameTxt)
        TextView fullName;
        @BindView(R.id.userNameTxt)
        TextView userName;
        @BindView(R.id.tweetTxt)
        TextView tweetTxt;


        public TweetViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
