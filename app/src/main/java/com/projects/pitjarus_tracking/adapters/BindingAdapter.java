package com.projects.pitjarus_tracking.adapters;

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.pitjarus_tracking.helpers.UIHelper;
import com.projects.pitjarus_tracking.components.GridSpacingItemDecorationUtil;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */
public class BindingAdapter {

    @androidx.databinding.BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, SimpleRecyclerAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @androidx.databinding.BindingAdapter({"sourceCompat"})
    public static void setImageSrcCompat(ImageView imageView, int imageResource) {
        imageView.setImageResource(imageResource);
    }

    @androidx.databinding.BindingAdapter("setupGridRecyclerView")
    public static void setupGridRecyclerView(RecyclerView recyclerView, float margin){
        Log.v("column", String.valueOf(UIHelper.newInstance(recyclerView.getContext()).calculateColumnNumber()));

        recyclerView.setLayoutManager(
                new GridLayoutManager(recyclerView.getContext(), 4)
        );
        recyclerView.addItemDecoration(
                new GridSpacingItemDecorationUtil(margin)
        );
    }

    @androidx.databinding.BindingAdapter("setupDinamisGridRecyclerView")
    public static void setupDinamisGridRecyclerView(RecyclerView recyclerView, float margin){
        Log.v("column", String.valueOf(UIHelper.newInstance(recyclerView.getContext()).calculateColumnNumber()));

        recyclerView.setLayoutManager(
                new GridLayoutManager(recyclerView.getContext(), UIHelper.newInstance(recyclerView.getContext()).calculateColumnNumber())
        );
        recyclerView.addItemDecoration(
                new GridSpacingItemDecorationUtil(margin)
        );
    }

    @androidx.databinding.BindingAdapter("setupHorizontalRecyclerView")
    public static void setupHorizontalRecyclerView(RecyclerView recyclerView, float margin){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(
                new GridSpacingItemDecorationUtil(margin, "horizontal")
        );
    }

    @androidx.databinding.BindingAdapter("setupVerticalRecyclerView")
    public static void setupVerticalRecyclerView(RecyclerView recyclerView, float margin){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(
                new GridSpacingItemDecorationUtil(margin, "vertical")
        );
    }
}
