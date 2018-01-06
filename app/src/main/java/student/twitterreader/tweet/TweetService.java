package student.twitterreader.tweet;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class TweetService {

    private static final String CONSUMER_KEY = "PxPpnxDyNjts0rxqLUGkJobit";
    private static final String CONSUMER_SECRET = "pTXfJVBBP9Jsq3Op1HOOFqmXLZsP3suzrgim8d2gXJLNLLt4WX";
    private static final String ACCESS_TOKEN = "126353595-MqShyUuRyHHAoyBQjmZvjTq36oN5hCLwhgkMssxa";
    private static final String ACCESS_TOKEN_SECRET = "sVQucS86eSQ0uLCYqFVBTOKUQ63HlEy25qjOJtbjyKbKk";

    private static TweetService instance;
    private OnTweetsRetrievedListener mListener;

    private List<Tweet> mCurrentTweets;
    private Map<User, List<Tweet>> mCurrentTweetMappedByUser;

    private String mCurrentSearch;

    public interface OnTweetsRetrievedListener {
        public void onTweetsRetrieved(List<Tweet> tweets);
    }

    private TweetService() {

    }

    public static TweetService getInstance() {
        if (instance == null) {
            instance = new TweetService();
        }
        return instance;
    }

    public void listTweets(String search, OnTweetsRetrievedListener listener) {
        mListener = listener;
        new TwitterTask().execute(search);
    }

    private class TwitterTask extends AsyncTask<String, Void, List<Status>> {

        private ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        private TwitterFactory twitterFactory;
        private Twitter twitter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            configBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey(CONSUMER_KEY)
                    .setOAuthConsumerSecret(CONSUMER_SECRET)
                    .setOAuthAccessToken(ACCESS_TOKEN)
                    .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

            twitterFactory = new TwitterFactory(configBuilder.build());
            twitter = twitterFactory.getInstance();
        }

        @Override
        protected List<twitter4j.Status> doInBackground(String... params) {

            mCurrentSearch = params[0];

            Query query = new Query(mCurrentSearch);
            query.setCount(100);
            QueryResult result = null;

            try {
                result = twitter.search(query);
            } catch (TwitterException te) {
                te.printStackTrace();
            }

            return result.getTweets();
        }

        @Override
        protected void onPostExecute(List<twitter4j.Status> statuses) {
            super.onPostExecute(statuses);

            List<Tweet> tweets = new ArrayList<>();
            mCurrentTweetMappedByUser = new HashMap<>();

            for (twitter4j.Status status : statuses) {
                Tweet tweet = new Tweet(status.getUser(), status.getText());
                tweets.add(tweet);

                if (mCurrentTweetMappedByUser.get(status.getUser()) == null) {
                    mCurrentTweetMappedByUser.put(status.getUser(), new ArrayList<Tweet>());
                }
                mCurrentTweetMappedByUser.get(status.getUser()).add(tweet);
            }

            mListener.onTweetsRetrieved(tweets);
            mCurrentTweets = tweets;
        }
    }

    public String getCurrentSearch() {
        return this.mCurrentSearch;
    }

    public List<User> getCurrentUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(mCurrentTweetMappedByUser.keySet());
        return users;
    }

}
