package com.projects.pitjarus_tracking.connections.databases;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.projects.pitjarus_tracking.AppConfiguration;
import com.projects.pitjarus_tracking.connections.requests.DatabaseErrorListener;
import com.projects.pitjarus_tracking.connections.requests.DatabaseSuccessListener;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.List;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class PitjarusTrackingDatabase implements IPitjarusTrackingDatabase{

    private @Nullable
    Activity currentActivity;
    private final PitjarusTrackingDatabaseDefinition databaseDefinition;

    public PitjarusTrackingDatabase (@Nullable Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                this.currentActivity = (Activity) context;
            }
        }

        this.databaseDefinition = AppConfiguration.getOurInstance().getDatabaseDefinition();
    }

    @Override
    public void insertStore(StoreModel storeModel, DatabaseSuccessListener<Long> successListener, DatabaseErrorListener errorListener) {
        Log.v("responseDatabase", storeModel.toString());
        new Thread(){
            @Override
            public void run() {
                Long id = databaseDefinition.storeDao().insert(storeModel);
                successListener.setResult(id);

                if (currentActivity != null){
                    currentActivity.runOnUiThread(successListener);
                    return;
                }
                successListener.run();
            }
        }.start();
    }

    @Override
    public void getAllStore(DatabaseSuccessListener<List<StoreModel>> successListener, DatabaseErrorListener errorListener) {
        new Thread(){
            @Override
            public void run() {
                List<StoreModel> storeModelList = databaseDefinition.storeDao().getAllStore();

                successListener.setResult(storeModelList);

                if (currentActivity != null) {
                    currentActivity.runOnUiThread(successListener);
                    return;
                }
                successListener.run();
            }
        }.start();
    }

    @Override
    public void getDetailStore(Integer request, DatabaseSuccessListener<StoreModel> successListener, DatabaseErrorListener errorListener) {
        new Thread(){
            @Override
            public void run() {
                StoreModel storeModel = databaseDefinition.storeDao().getDetailStore(request);

                successListener.setResult(storeModel);

                if (currentActivity != null){
                    currentActivity.runOnUiThread(successListener);
                }
                successListener.run();
            }
        }.start();
    }
}
