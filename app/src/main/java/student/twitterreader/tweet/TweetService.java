package student.twitterreader.tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class TweetService {

    public TweetService() {

    }

    public List<Tweet> getFakeTweets() {

        List<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            tweets.add(new Tweet(
                    String.valueOf("Usuario " + i),
                    String.valueOf("@user" + (i + 1)),
                    String.valueOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore")
            ));
        }

        return tweets;
    }

}
