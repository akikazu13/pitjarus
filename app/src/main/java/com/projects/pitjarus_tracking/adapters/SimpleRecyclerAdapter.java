package com.projects.pitjarus_tracking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Getter
@Setter

public class SimpleRecyclerAdapter<A> extends RecyclerView.Adapter<SimpleRecyclerAdapter.SimpleViewHolder> {

    protected List<A> mainData;
    private @LayoutRes
    int layoutRes;
    private SimpleRecyclerAdapter.OnViewHolder<A> listener;

    public SimpleRecyclerAdapter(List<A> mainData, int layoutRes, OnViewHolder<A> listener) {
        this.mainData = mainData;
        this.layoutRes = layoutRes;
        this.listener = listener;
    }

    public interface OnViewHolder<A>{
        void onBindView(SimpleViewHolder holder, A item);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private SimpleRecyclerAdapter.OnViewHolder listener;
        private ViewDataBinding layoutBinding;

        public SimpleViewHolder(@NonNull View itemView, SimpleRecyclerAdapter.OnViewHolder listener) {
            super(itemView);

            try {
                this.listener = listener;
                layoutBinding = DataBindingUtil.bind(itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public ViewDataBinding getLayoutBinding(){
            return layoutBinding;
        }
    }

    @NonNull
    @Override
    public SimpleRecyclerAdapter.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false);
        return new SimpleViewHolder(view, getListener());
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleRecyclerAdapter.SimpleViewHolder holder, int position) {
        A a = mainData.get(position);
        getListener().onBindView(holder, a);
    }

    @Override
    public int getItemCount() {
        return getMainData() == null ? 0 : getMainData().size();
    }

    public void setMainData(List<A> mainData) {
        this.mainData = mainData;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        mainData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mainData.size());
    }
}
