package com.projects.pitjarus_tracking;

import android.content.Context;

import androidx.room.Room;

import com.projects.pitjarus_tracking.helpers.JSONHelper;
import com.projects.pitjarus_tracking.connections.databases.IPitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.databases.PitjarusTrackingDatabase;
import com.projects.pitjarus_tracking.connections.databases.PitjarusTrackingDatabaseDefinition;
import com.projects.pitjarus_tracking.connections.databases.PitjarusTrackingMigrationDatabase;

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

    public void initDb(Context context) {
        databaseDefinition = Room.databaseBuilder(context, PitjarusTrackingDatabaseDefinition.class, context.getString(R.string.app_name))
                .addMigrations(PitjarusTrackingMigrationDatabase.MIGRATION)
                .build();
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
}
