package student.twitterreader.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import student.twitterreader.R;
import twitter4j.User;

/**
 * Created by tuliodesouza on 26/11/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.TweetViewHolder> {

    public interface OnUserClickListener {
        public void onUserClick(User user);
    }

    private Context mContext;
    private List<User> mUsers;
    private OnUserClickListener mListener;

    public UserAdapter(Context context, List<User> users, OnUserClickListener listener) {
        this.mContext = context;
        this.mUsers = users;
        this.mListener = listener;
    }

    @Override
    public UserAdapter.TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        TweetViewHolder holder = new TweetViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.TweetViewHolder holder, int position) {

        final User user = mUsers.get(position);

        Picasso.with(mContext).load(user.getProfileImageURL()).into(holder.profileImage);

        holder.fullName.setText(user.getName());
        holder.userName.setText("@" + user.getScreenName());

        holder.container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onUserClick(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class TweetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profileImage;
        @BindView(R.id.fullNameTxt)
        TextView fullName;
        @BindView(R.id.userNameTxt)
        TextView userName;

        View container;

        public TweetViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            container = view;
        }
    }
}
