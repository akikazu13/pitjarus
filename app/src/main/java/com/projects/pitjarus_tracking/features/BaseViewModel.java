package com.projects.pitjarus_tracking.features;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.projects.pitjarus_tracking.AppConfiguration;
import com.projects.pitjarus_tracking.connections.databases.IPitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.networks.RetrofitInstance;
import com.projects.pitjarus_tracking.connections.networks.implementations.IVolleyAPI;
import com.projects.pitjarus_tracking.connections.networks.implementations.IRetrofitAPI;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class BaseViewModel extends AndroidViewModel {

    protected IVolleyAPI volleyAPI;
    protected IPitjarusTrackingDatabase database;
    protected IRetrofitAPI IRetrofitAPI;

    public BaseViewModel(@NonNull Application application) {
        super(application);

        volleyAPI = AppConfiguration.getOurInstance().getVolleyAPI(application);
        database = AppConfiguration.getOurInstance().getPitjarusTrackingDatabase(application);
        IRetrofitAPI = RetrofitInstance.getRetrofitInstance().create(IRetrofitAPI.class);
    }
}
