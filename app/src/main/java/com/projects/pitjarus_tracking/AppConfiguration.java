package com.projects.pitjarus_tracking;

import android.content.Context;

import com.projects.pitjarus_tracking.Helpers.JSONHelper;
import com.projects.pitjarus_tracking.connections.databases.IPitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.databases.PitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.databases.PitjarusTrackingDatabaseDefinition;
import com.projects.pitjarus_tracking.connections.networks.implementations.BaseVolleyAPI;
import com.projects.pitjarus_tracking.connections.networks.implementations.VolleyAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class AppConfiguration {

    private static final AppConfiguration ourInstance = new AppConfiguration();

    private PitjarusTrackingDatabaseDefinition databaseDefinition;

    public static AppConfiguration getOurInstance() {
        return ourInstance;
    }

    private AppConfiguration(){
    }

    public IPitjarusTrackingDatabase getPitjarusTrackingDatabase(Context context){
        return new PitjarusTrackingDatabase(context);
    }

    public JSONObject getApiUrl(Context context) {
        Integer apiUrl = BuildConfig.API_ROUTE;

        try {
            return JSONHelper.getOurInstance().readResource(context, apiUrl);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PitjarusTrackingDatabaseDefinition getDatabaseDefinition() {
        return databaseDefinition;
    }

    public BaseVolleyAPI getVolleyAPI(Context context){
        return new VolleyAPI(context);
    }
}
