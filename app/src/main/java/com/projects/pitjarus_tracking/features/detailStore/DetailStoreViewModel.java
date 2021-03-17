package com.projects.pitjarus_tracking.features.detailStore;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.projects.pitjarus_tracking.connections.requests.DatabaseErrorListener;
import com.projects.pitjarus_tracking.connections.requests.DatabaseSuccessListener;
import com.projects.pitjarus_tracking.features.BaseViewModel;
import com.projects.pitjarus_tracking.models.StoreModel;

import lombok.Getter;

/**
 * Created by Sohibun Nawawi on 16/03/2021.
 */

@Getter

public class DetailStoreViewModel extends BaseViewModel {

    private MutableLiveData<StoreModel> detailStoreliveData = new MutableLiveData<>();

    public DetailStoreViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDetailStore(Integer request){
        database.getDetailStore(request,
                new DatabaseSuccessListener<StoreModel>() {
                    @Override
                    public void onSuccessResponse(StoreModel results) {
                        detailStoreliveData.postValue(results);
                    }
                },
                new DatabaseErrorListener() {
                    @Override
                    public void onErrorResponseListener() {
                    }
                });
    }
}
