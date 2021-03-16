package com.projects.pitjarus_tracking;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * Created by Sohibun Nawawi on 15/03/2021.
 */

public class PitjarusTrackingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        AppConfiguration.getOurInstance().initDb(getApplicationContext());
    }
}
