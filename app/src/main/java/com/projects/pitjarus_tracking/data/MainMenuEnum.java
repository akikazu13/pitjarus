package com.projects.pitjarus_tracking.data;

import com.projects.pitjarus_tracking.R;

/**
 * Created by Sohibun Nawawi on 15/03/2021.
 */
public enum MainMenuEnum {

    MENU_VISIT(GlobalVariable.MENU_NAME_VISIT, R.drawable.ic_visited),
    MENU_TARGET_INSTALL(GlobalVariable.MENU_NAME_TARGET_INSTALL, R.drawable.ic_target),
    MENU_DASHBOARD(GlobalVariable.MENU_NAME_DASHBOARD,  R.drawable.ic_dashboard),
    MENU_TRANSMISSION(GlobalVariable.MENU_NAME_TRANSMISSION, R.drawable.ic_transmission),
    MENU_LOGOUT(GlobalVariable.MENU_NAME_LOGOUT, R.drawable.ic_target);

    private final String name;
    private final int icon;

    MainMenuEnum(String menuName, int menuIcon){
        this.name = menuName;
        this.icon = menuIcon;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
