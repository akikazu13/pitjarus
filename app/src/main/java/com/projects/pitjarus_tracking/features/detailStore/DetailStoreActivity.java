package com.projects.pitjarus_tracking.features.detailStore;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.databinding.ActivityDetailStoreBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.storeMenu.StoreMenuActivity;
import com.projects.pitjarus_tracking.models.StoreModel;

import org.parceler.Parcels;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

public class DetailStoreActivity extends BaseActivity<ActivityDetailStoreBinding> {

    private DetailStoreViewModel detailStoreViewModel;
    private StoreModel storeModel;

    @Override
    protected int attachLayout() {
        return R.layout.activity_detail_store;
    }

    @Override
    protected void initData() {
        super.initData();

        Integer id = getIntent().getIntExtra("id", 0);

        detailStoreViewModel = ViewModelProviders.of(this).get(DetailStoreViewModel.class);
        detailStoreViewModel.getDetailStore(id);
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        detailStoreViewModel.getDetailStoreliveData().observe(this, response->{
            storeModel = response;
            binding.setDetailStoreModel(response);
        });
    }

    @Override
    protected void initAction() {
        super.initAction();

        binding.btnNoVisit.setOnClickListener(onCLick-> onBackPressed());
        binding.btnVisit.setOnClickListener(onClick->{
            Intent intent = new Intent(DetailStoreActivity.this, StoreMenuActivity.class);
            intent.putExtra("storeModel", Parcels.wrap(storeModel));
            startActivity(intent);
        });
        binding.btnBack.setOnClickListener((View onClick) -> onBackPressed());
    }
}
