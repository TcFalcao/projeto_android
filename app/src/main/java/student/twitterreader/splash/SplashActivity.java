package student.twitterreader.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import student.twitterreader.tweets.TweetSearchActivity;
import student.twitterreader.R;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, TweetSearchActivity.class);
                startActivity(it);
            }
        }, 2000);
    }

}
