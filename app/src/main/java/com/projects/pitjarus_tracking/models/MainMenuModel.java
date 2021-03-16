package com.projects.pitjarus_tracking.models;

import com.projects.pitjarus_tracking.data.MainMenuEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sohibun Nawawi on 15/03/2021.
 */

@Getter
@Setter

public class MainMenuModel {

    private String name;
    private int icon;

    public MainMenuModel(MainMenuEnum menuEnum){
        setName(menuEnum.getName());
        setIcon(menuEnum.getIcon());
    }
}
