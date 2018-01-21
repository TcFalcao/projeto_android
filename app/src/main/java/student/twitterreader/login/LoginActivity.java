package student.twitterreader.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.MainActivity;
import student.twitterreader.R;
import student.twitterreader.tweet.TweetService;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.loginBtn)
    public void onLoginClick() {

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
