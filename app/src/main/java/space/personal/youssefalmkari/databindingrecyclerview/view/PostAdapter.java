package space.personal.youssefalmkari.databindingrecyclerview.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import space.personal.youssefalmkari.databindingrecyclerview.R;
import space.personal.youssefalmkari.databindingrecyclerview.databinding.PostRowItemBinding;
import space.personal.youssefalmkari.databindingrecyclerview.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private final PostRowItemBinding binding;

        ViewHolder(final PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    PostAdapter(List<Post> postList, LayoutInflater layoutInflater) {
        this.postList = postList;
        this.layoutInflater = layoutInflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // Assert layoutInflater has context
        if (layoutInflater == null) {
            LayoutInflater.from(viewGroup.getContext());
        }

        // Inflate row item
        PostRowItemBinding binding =
                DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.post_row_item,
                        viewGroup,
                        false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.binding.setPost(postList.get(i));
        viewHolder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(postList.get(viewHolder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }
}
