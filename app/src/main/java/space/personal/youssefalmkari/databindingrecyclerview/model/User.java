package space.personal.youssefalmkari.databindingrecyclerview.model;

import android.databinding.*;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import space.personal.youssefalmkari.databindingrecyclerview.BR;

public class User extends BaseObservable {

    private String name;
    private String email;
    private String profileImage;
    private String about;

    private ObservableField<Long> numberOfFollowers;
    private ObservableField<Long> numberOfFollowing;
    private ObservableField<Long> numberOfPosts;

    public User() { }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        notifyPropertyChanged(BR.profileImage);
    }

    @Bindable
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
        notifyPropertyChanged(BR.about);
    }

    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view, String imageUrl) {

        // Load profile image
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);

    }

    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(ObservableField<Long> numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public void setNumberOfFollowing(ObservableField<Long> numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(ObservableField<Long> numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
