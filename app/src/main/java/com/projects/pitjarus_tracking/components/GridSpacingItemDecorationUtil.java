package com.projects.pitjarus_tracking.components;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sohibun Nawawi on 15/03/2021.
 */

public class GridSpacingItemDecorationUtil extends RecyclerView.ItemDecoration {
    private int mItemOffset;
    private String type;

    public GridSpacingItemDecorationUtil(float itemOffsetFloat, String type){
        this.mItemOffset = (int) itemOffsetFloat;
        this.type = type;
    }

    public GridSpacingItemDecorationUtil(float mItemOffset) {
        this.mItemOffset = (int) mItemOffset;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        if (type == null)
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        else{
            if (type.equalsIgnoreCase("vertical"))
                outRect.set(0, 0, 0, mItemOffset);
            else if (type.equalsIgnoreCase("horizontal"))
                outRect.set(0,0,mItemOffset,0);
        }

    }
}
