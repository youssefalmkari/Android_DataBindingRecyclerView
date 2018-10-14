package space.personal.youssefalmkari.databindingrecyclerview.model;

import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Post {

    private String imageUrl;

    public static void loadImage(ImageView view, String imageUrl) {

        // Load imageUrl
        Glide.with(view.getContext()).load(imageUrl).into(view);

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
