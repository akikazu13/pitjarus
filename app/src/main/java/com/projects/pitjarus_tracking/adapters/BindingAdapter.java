package com.projects.pitjarus_tracking.adapters;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */
public class BindingAdapter {

    @androidx.databinding.BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, SimpleRecyclerAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }
}
