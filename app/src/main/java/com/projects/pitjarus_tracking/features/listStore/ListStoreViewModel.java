package com.projects.pitjarus_tracking.features.listStore;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.projects.pitjarus_tracking.connections.requests.DatabaseErrorListener;
import com.projects.pitjarus_tracking.connections.requests.DatabaseSuccessListener;
import com.projects.pitjarus_tracking.features.BaseViewModel;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.List;

import lombok.Getter;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

@Getter

public class ListStoreViewModel extends BaseViewModel {

    private MutableLiveData<List<StoreModel>> storeLiveData = new MutableLiveData<>();

    public ListStoreViewModel(@NonNull Application application) {
        super(application);
    }

    public void getStoreList(){
        database.getAllStore(
                new DatabaseSuccessListener<List<StoreModel>>() {
                    @Override
                    public void onSuccessResponse(List<StoreModel> results) {
                        storeLiveData.postValue(results);
                    }
                },
                new DatabaseErrorListener() {
                    @Override
                    public void onErrorResponseListener() {

                    }
                }
        );
    }
}
