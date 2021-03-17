package com.projects.pitjarus_tracking.connections.databases;

import com.projects.pitjarus_tracking.connections.requests.DatabaseErrorListener;
import com.projects.pitjarus_tracking.connections.requests.DatabaseSuccessListener;
import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.List;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public interface IPitjarusTrackingDatabase {

    void insertStore(StoreModel storeModel,
                     DatabaseSuccessListener<Long> successListener,
                     DatabaseErrorListener errorListener);

    void getAllStore(DatabaseSuccessListener<List<StoreModel>> successListener,
                     DatabaseErrorListener errorListener);

    void getDetailStore(Integer request,
                        DatabaseSuccessListener<StoreModel> successListener,
                        DatabaseErrorListener errorListener);


}
