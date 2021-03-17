package com.projects.pitjarus_tracking.features.listStore;

import android.content.Intent;
import android.location.Location;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.adapters.SimpleRecyclerAdapter;
import com.projects.pitjarus_tracking.databinding.ActivityListStoreBinding;
import com.projects.pitjarus_tracking.databinding.ItemListStoreBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.detailStore.DetailStoreActivity;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.ArrayList;

import okhttp3.Dispatcher;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

public class ListStoreActivity extends BaseActivity<ActivityListStoreBinding>{

    private SimpleRecyclerAdapter<StoreModel> storeAdapter;
    private ListStoreViewModel storeViewModel;

    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private LocationRequest locationRequest;
    Location currentLocation;
    private long UPDATE_INTERVAL = 60000;
    private long FASTEST_INTERVAL = 5000;

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

                    binding.itemStoreLayout.setOnClickListener(onClick->{
                        Intent intent = new Intent(ListStoreActivity.this, DetailStoreActivity.class);
                        intent.putExtra("id", item.getId());
                        startActivity(intent );
                    });
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

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrame);
        if (mapFragment != null){
            mapFragment.getMapAsync(googleMap -> {

            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMap(GoogleMap googleMap){
        map = googleMap;
        if (map != null){

        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }
    private void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragment.toString());
        transaction.commit();
    }
}
