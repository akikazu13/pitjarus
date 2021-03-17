package com.projects.pitjarus_tracking.features.detailStore;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.databinding.ActivityDetailStoreBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.storeMenu.StoreMenuActivity;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

public class DetailStoreActivity extends BaseActivity<ActivityDetailStoreBinding> {

    private Integer id =0;
    private DetailStoreViewModel detailStoreViewModel;

    @Override
    protected int attachLayout() {
        return R.layout.activity_detail_store;
    }

    @Override
    protected void initData() {
        super.initData();

        id = getIntent().getIntExtra("id", 0);
        Log.v("getIntentCek", id.toString());

        detailStoreViewModel = ViewModelProviders.of(this).get(DetailStoreViewModel.class);
        detailStoreViewModel.getDetailStore(id);
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        detailStoreViewModel.getDetailStoreliveData().observe(this, response->{
            Log.v("cek", response.toString());
            binding.setDetailStoreModel(response);
        });
    }

    @Override
    protected void initAction() {
        super.initAction();

        binding.btnNoVisit.setOnClickListener(onCLick->{
            onBackPressed();
        });
        binding.btnVisit.setOnClickListener(onClick->{
            Intent intent = new Intent(DetailStoreActivity.this, StoreMenuActivity.class);
            startActivity(intent);
        });
    }
}
