package student.twitterreader.model;

import twitter4j.User;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class Tweet {

    User user;
    private String tweet;

    public Tweet(User user, String tweet) {
        this.user = user;
        this.tweet = tweet;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
