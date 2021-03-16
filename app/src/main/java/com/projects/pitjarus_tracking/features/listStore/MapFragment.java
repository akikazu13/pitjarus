package com.projects.pitjarus_tracking.features.listStore;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.databinding.FragmentMapBinding;
import com.projects.pitjarus_tracking.features.BaseFragment;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

public class MapFragment extends BaseFragment<FragmentMapBinding> implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected int attachLayout() {
        return R.layout.fragment_map;
    }

    @Override
    protected void initLayout() {
        super.initLayout();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("mark in sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
