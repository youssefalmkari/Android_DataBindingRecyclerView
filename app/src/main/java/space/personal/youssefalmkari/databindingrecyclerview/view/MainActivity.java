package space.personal.youssefalmkari.databindingrecyclerview.view;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import space.personal.youssefalmkari.databindingrecyclerview.R;
import space.personal.youssefalmkari.databindingrecyclerview.databinding.ActivityMainBinding;
import space.personal.youssefalmkari.databindingrecyclerview.model.Post;
import space.personal.youssefalmkari.databindingrecyclerview.model.User;
import space.personal.youssefalmkari.databindingrecyclerview.utils.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity implements PostAdapter.PostsAdapterListener {

    private MyClickHandlers handlers;
    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbar_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handlers = new MyClickHandlers(this);

        renderProfile();

        initRecyclerView();
    }

    /**
     * Renders RecyclerView with Grid Images in 3 columns
     */
    private void initRecyclerView() {

        RecyclerView recyclerView = binding.content.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        PostAdapter mAdapter = new PostAdapter(getPosts(), this);
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * Renders user profile data
     */
    private void renderProfile() {

        user = new User();
        user.setName("David Attenborough");
        user.setEmail("david@natgeo.com");
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg");
        user.setAbout("Naturalist");

        // ObservableField doesn't have setter method, instead will
        // be called using set() method
        user.getNumberOfPosts().set(3400L);
        user.getNumberOfFollowers().set(3050890L);
        user.getNumberOfFollowing().set(150L);

        // display user
        binding.setUser(user);

        // assign click handlers
        binding.content.setHandlers(handlers);

    }

    private ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");

            posts.add(post);
        }

        return posts;
    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(getApplicationContext(), "Post clicked! " + post.getImageUrl(), Toast.LENGTH_SHORT).show();
    }

    public class MyClickHandlers {

        Context context;

        MyClickHandlers(Context context) {
            this.context = context;
        }

        /**
         * Demonstrating updating bind data
         * Profile name, number of posts and profile image
         * will be updated on Fab click
         */
        public void onProfileFabClicked(View view) {
            user.setName("Kakarot");
            user.setProfileImage(getResources().getString(R.string.goku_profile_address));

            // updating ObservableField
            user.getNumberOfPosts().set(5500L);
            user.getNumberOfFollowers().set(5050890L);
            user.getNumberOfFollowing().set(180L);
        }

        public boolean onProfileImageLongPressed(View view) {
            Toast.makeText(getApplicationContext(),
                    "Profile image long pressed!",
                    Toast.LENGTH_LONG).show();
            return false;
        }


        public void onFollowersClicked(View view) {
            Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFollowingClicked(View view) {
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onPostsClicked(View view) {
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
