package com.projects.pitjarus_tracking.features.mainMenu;

import android.content.Intent;
import android.widget.Toast;

import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.adapters.SimpleRecyclerAdapter;
import com.projects.pitjarus_tracking.data.GlobalVariable;
import com.projects.pitjarus_tracking.data.MainMenuEnum;
import com.projects.pitjarus_tracking.databinding.ActivityMainMenuBinding;
import com.projects.pitjarus_tracking.databinding.ItemMainMenuBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.listStore.ListStoreActivity;
import com.projects.pitjarus_tracking.models.MainMenuModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohibun Nawawi on 13/03/2021.
 */
public class MainMenuActivity extends BaseActivity<ActivityMainMenuBinding> {

    private final List<MainMenuModel> mainMenuModelList = new ArrayList<>();
    private SimpleRecyclerAdapter<MainMenuModel> mainMenuAdapter;

    @Override
    protected int attachLayout() {
        return R.layout.activity_main_menu;
    }

    @Override
    protected void initData() {
        super.initData();

        mainMenuModelList.add(new MainMenuModel(MainMenuEnum.MENU_VISIT));
        mainMenuModelList.add(new MainMenuModel(MainMenuEnum.MENU_TARGET_INSTALL));
        mainMenuModelList.add(new MainMenuModel(MainMenuEnum.MENU_DASHBOARD));
        mainMenuModelList.add(new MainMenuModel(MainMenuEnum.MENU_TRANSMISSION));
        mainMenuModelList.add(new MainMenuModel(MainMenuEnum.MENU_LOGOUT));

        mainMenuAdapter = new SimpleRecyclerAdapter<>(
                mainMenuModelList,
                R.layout.item_main_menu,
                (holder, item) ->{
                    ItemMainMenuBinding binding = (ItemMainMenuBinding) holder.getLayoutBinding();
                    binding.setModel(item);

                    binding.mainMenuLayout.setOnClickListener(onClick->{
                        switch (item.getName()){
                            case (GlobalVariable.MENU_NAME_VISIT):{
                                Intent intent = new Intent(MainMenuActivity.this, ListStoreActivity.class);
                                startActivity(intent);
                                break;
                            }
                            default: {
                                Toast.makeText(this, "Fitur Belum Tersedia", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    });
                }
        );
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        binding.setMainMenuAdapter(mainMenuAdapter);
    }
}
