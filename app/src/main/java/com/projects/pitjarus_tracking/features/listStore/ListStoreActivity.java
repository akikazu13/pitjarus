package com.projects.pitjarus_tracking.features.listStore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.adapters.SimpleRecyclerAdapter;
import com.projects.pitjarus_tracking.databinding.ActivityListStoreBinding;
import com.projects.pitjarus_tracking.databinding.ItemListStoreBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.detailStore.DetailStoreActivity;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.ArrayList;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

@RuntimePermissions

public class ListStoreActivity extends BaseActivity<ActivityListStoreBinding> {

    private SimpleRecyclerAdapter<StoreModel> storeAdapter;
    private ListStoreViewModel storeViewModel;

    private GoogleMap map;
    Location currentLocation;

    private final static String KEY_LOCATION = "location";

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
                (holder, item) -> {
                    ItemListStoreBinding binding = (ItemListStoreBinding) holder.getLayoutBinding();
                    binding.setStoreModel(item);

                    binding.itemStoreLayout.setOnClickListener(onClick -> {
                        Intent intent = new Intent(ListStoreActivity.this, DetailStoreActivity.class);
                        intent.putExtra("id", item.getId());
                        startActivity(intent);
                    });
                }
        );

        storeViewModel = ViewModelProviders.of(this).get(ListStoreViewModel.class);
        storeViewModel.getStoreList();

    }

    @Override
    protected void initLayout() {
        super.initLayout();

        storeViewModel.getStoreLiveData().observe(this, response -> {
            storeAdapter.setMainData(response);
            binding.setStoreAdapter(storeAdapter);
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrame);
        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {
                loadMap(googleMap);
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            currentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (currentLocation != null) {
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            map.animateCamera(cameraUpdate);
        }

        ListStoreActivityPermissionsDispatcher.startLocationUpdateWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    protected void startLocationUpdate() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        long UPDATE_INTERVAL = 60000;
        locationRequest.setInterval(UPDATE_INTERVAL);
        long FASTEST_INTERVAL = 5000;
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    private void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            ListStoreActivityPermissionsDispatcher.getMyLocationWithPermissionCheck(this);
            ListStoreActivityPermissionsDispatcher.startLocationUpdateWithPermissionCheck(this);

            getMyLocation();
            startLocationUpdate();
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    @NeedsPermission({android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        FusedLocationProviderClient locationProviderClient  = getFusedLocationProviderClient(this);
        locationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null){
                        onLocationChanged(location);
                    }
                })
                .addOnFailureListener((e) ->{

                });
    }

    private void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }
        currentLocation = location;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(KEY_LOCATION, currentLocation);
        super.onSaveInstanceState(savedInstanceState);
    }
}
