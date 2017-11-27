package student.twitterreader.tweet;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class Tweet {

    private String fullUserName;
    private String userName;

    private String tweet;

    public Tweet(String fullUserName, String userName, String tweet) {
        this.fullUserName = fullUserName;
        this.userName = userName;
        this.tweet = tweet;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
