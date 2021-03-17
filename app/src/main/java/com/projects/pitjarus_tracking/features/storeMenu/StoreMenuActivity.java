package com.projects.pitjarus_tracking.features.storeMenu;

import android.content.Intent;

import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.adapters.SimpleRecyclerAdapter;
import com.projects.pitjarus_tracking.data.GlobalVariable;
import com.projects.pitjarus_tracking.databinding.ActivityStoreMenuBinding;
import com.projects.pitjarus_tracking.databinding.ItemDashboardBinding;
import com.projects.pitjarus_tracking.databinding.ItemStoreMenuBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.listStore.ListStoreActivity;
import com.projects.pitjarus_tracking.models.ProgressModel;
import com.projects.pitjarus_tracking.models.StoreMenuModel;
import com.projects.pitjarus_tracking.models.StoreModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohibun Nawawi on 17/03/2021.
 */

public class StoreMenuActivity extends BaseActivity<ActivityStoreMenuBinding> {

    private StoreModel storeModel;
    private final List<ProgressModel> progressModelList = new ArrayList<>();
    private final List<StoreMenuModel> storeMenuModelList = new ArrayList<>();
    private SimpleRecyclerAdapter<ProgressModel> progressAdapter;
    private SimpleRecyclerAdapter<StoreMenuModel> storeMenuAdapter;

    @Override
    protected int attachLayout() {
        return R.layout.activity_store_menu;
    }

    @Override
    protected void initData() {
        super.initData();

        storeModel = Parcels.unwrap(getIntent().getParcelableExtra("storeModel"));

        setDashboardList();
        setMenuStore();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        binding.setStoreModel(storeModel);

        progressAdapter.setMainData(progressModelList);
        binding.setItemDasboardAdapter(progressAdapter);

        storeMenuAdapter.setMainData(storeMenuModelList);
        binding.setStoreMenuAdapter(storeMenuAdapter);
    }

    @Override
    protected void initAction() {
        super.initAction();

        binding.btnFinish.setOnClickListener(onClick->{
            Intent intent = new Intent(StoreMenuActivity.this, ListStoreActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnBack.setOnClickListener(onClick-> onBackPressed());
    }

    private void setDashboardList(){
        progressModelList.add(new ProgressModel("ABC", 50, 25));
        progressModelList.add(new ProgressModel("KSO",30, 10));
        progressModelList.add(new ProgressModel("NDP", 10, 9));
        progressModelList.add(new ProgressModel("WDD", 35, 10));

        progressAdapter = new SimpleRecyclerAdapter<>(
                new ArrayList<>(),
                R.layout.item_dashboard,
                (holder, item) -> {
                    ItemDashboardBinding binding = (ItemDashboardBinding) holder.getLayoutBinding();
                    binding.setProgressModel(item);

                    binding.progressBar.setMax(item.getTarget());
                    binding.progressBar.setProgress(item.getProgress());
                }
        );
    }

    private void setMenuStore(){
        storeMenuModelList.add(new StoreMenuModel(GlobalVariable.MENU_NAME_INFORMATION, R.drawable.ic_information));
        storeMenuModelList.add(new StoreMenuModel(GlobalVariable.MENU_NAME_PRODUCT_CHECK, R.drawable.ic_produk_check));
        storeMenuModelList.add(new StoreMenuModel(GlobalVariable.MENU_NAME_PROMO, R.drawable.ic_promo));
        storeMenuModelList.add(new StoreMenuModel(GlobalVariable.MENU_NAME_SUMMARY, R.drawable.ic_summary));
        storeMenuModelList.add(new StoreMenuModel(GlobalVariable.MENU_NAME_INVESTMENT, R.drawable.ic_cart));

        storeMenuAdapter = new SimpleRecyclerAdapter<>(
                new ArrayList<>(),
                R.layout.item_store_menu,
                (holder, item) -> {
                    ItemStoreMenuBinding binding = (ItemStoreMenuBinding) holder.getLayoutBinding();
                    binding.setStoreMenuModel(item);
                }
        );
    }
}
