package com.projects.pitjarus_tracking.helpers;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sohibun Nawawi on 15/03/2021.
 */

@Getter
@Setter

public class UIHelper {

    private Context context;

    private UIHelper(){}

    public static UIHelper newInstance(Context context){
        UIHelper uiHelper = new UIHelper();
        uiHelper.setContext(context);
        return uiHelper;
    }

    public static void fullscreenDarkUI(Window window){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setDarkIconStatusBar(window);
    }

    public static void setDarkIconStatusBar(Window window){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        window.setStatusBarColor(Color.TRANSPARENT);
    }
    public static void fullscreenLightUI(Window window){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setLightIconStatusBar(window);
    }

    public static void setLightIconStatusBar(Window window){
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public Integer calculateColumnNumber(){
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (screenWidthDp / 100);
    }
}
