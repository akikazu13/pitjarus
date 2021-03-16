package com.projects.pitjarus_tracking.features.listStore;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.SupportMapFragment;
import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.adapters.SimpleRecyclerAdapter;
import com.projects.pitjarus_tracking.databinding.ActivityListStoreBinding;
import com.projects.pitjarus_tracking.databinding.ItemListStoreBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.ArrayList;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

public class ListStoreActivity extends BaseActivity<ActivityListStoreBinding>{

    private SimpleRecyclerAdapter<StoreModel> storeAdapter;
    private ListStoreViewModel storeViewModel;
    private MapFragment mapFragment = new MapFragment();

    @Override
    protected int attachLayout() {
        return R.layout.activity_list_store;
    }

    @Override
    protected void initData() {
        super.initData();

        storeAdapter = new SimpleRecyclerAdapter<>(
                new ArrayList<>(),
                R.layout.item_list_store,
                (holder, item) ->{
                    ItemListStoreBinding binding = (ItemListStoreBinding) holder.getLayoutBinding();
                    binding.setStoreModel(item);
                }
        );

        storeViewModel = ViewModelProviders.of(this).get(ListStoreViewModel.class);
        storeViewModel.getStoreList();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        storeViewModel.getStoreLiveData().observe(this, response->{
            storeAdapter.setMainData(response);
            binding.setStoreAdapter(storeAdapter);
        });

        addFragmentToActivity(
                getSupportFragmentManager(), mapFragment, binding.mapFrame.getId()
        );
    }

    private void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragment.toString());
        transaction.commit();
    }
}
