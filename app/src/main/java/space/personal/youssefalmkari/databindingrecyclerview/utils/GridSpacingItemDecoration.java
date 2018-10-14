package space.personal.youssefalmkari.databindingrecyclerview.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

        // Get position and column
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        // Decorate item
        if (includeEdge) {

            // Left & Right
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;

            // Top
            if (position < spanCount) {
                outRect.top = spacing;
            }

            // Bottom
            outRect.bottom = spacing;

        } else {

            // Left && Right
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;

            // Top
            if (position >= spanCount) {
                outRect.top = spacing;
            }
        }

    }
}
