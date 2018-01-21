package student.twitterreader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import student.twitterreader.tweet.SortUserDialog;
import student.twitterreader.tweet.Tweet;
import student.twitterreader.tweet.TweetAdapter;
import student.twitterreader.tweet.TweetDialog;
import student.twitterreader.tweet.TweetService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TweetService.OnTweetsRetrievedListener {

    @BindView(R.id.twittesList)
    RecyclerView mTweetsListView;

    @BindView(R.id.hashtagTextContainer) View mHashtagTextContainer;
    @BindView(R.id.hashtagChangeContainer) View mHashtagChangeContainer;

    @BindView(R.id.hashtagEdt)
    EditText mHashtagEdt;

    @BindView(R.id.hashtagTxt)
    TextView mHashtagTxt;

    TweetService mTweetService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTweetService = TweetService.getInstance();

        mTweetsListView.setLayoutManager(new LinearLayoutManager(this));

    }

    @OnClick(R.id.hashtagTextContainer)
    public void onHashtagClick () {
        mHashtagChangeContainer.setVisibility(View.VISIBLE);
        mHashtagTextContainer.setVisibility(View.INVISIBLE);

        mHashtagEdt.requestFocus();
        showKeyboard();
    }


    @OnClick(R.id.changeHashtagBtn)
    public void onChangeHashtagClick () {
        mHashtagChangeContainer.setVisibility(View.INVISIBLE);
        mHashtagTextContainer.setVisibility(View.VISIBLE);

        String hashtag = mHashtagEdt.getText().toString();

        if(!hashtag.isEmpty() && hashtag.charAt(0) != '#') {
            hashtag = "#" + hashtag;
        }

        mHashtagTxt.setText(hashtag);
        hideKeyboard();

        mTweetService.listTweets(hashtag, this);
    }

    @OnClick(R.id.manageBtn)
    public void onManageClick() {
        Intent it = new Intent(this, ManageActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tweet) {

            TweetDialog dialog = new TweetDialog(this, mHashtagTxt.getText().toString());
            dialog.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mHashtagEdt, 0);
        }
    }

    @Override
    public void onTweetsRetrieved(List<Tweet> tweets) {
        TweetAdapter adapter = new TweetAdapter(this, tweets);
        mTweetsListView.setAdapter(adapter);
    }
}
