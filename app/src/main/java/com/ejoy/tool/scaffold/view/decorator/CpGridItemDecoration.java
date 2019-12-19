package com.ejoy.tool.scaffold.view.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CpGridItemDecoration extends RecyclerView.ItemDecoration{
    private int mSpanCount;
    private int mSpace;

    public CpGridItemDecoration(int spanCount, int space) {
        this.mSpanCount = spanCount;
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % mSpanCount;

        outRect.left = column * mSpace / mSpanCount;
        outRect.right = mSpace - (column + 1) * mSpace / mSpanCount;
        if (position >= mSpanCount) {
            outRect.top = mSpace;
        }
    }
}
