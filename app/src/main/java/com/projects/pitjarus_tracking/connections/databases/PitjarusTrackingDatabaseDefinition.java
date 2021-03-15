package com.projects.pitjarus_tracking.connections.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.projects.pitjarus_tracking.BuildConfig;
import com.projects.pitjarus_tracking.connections.databases.daos.StoreDao;
import com.projects.pitjarus_tracking.models.StoreModel;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Database(
        entities = {
                StoreModel.class
        }, version = BuildConfig.DATABASE_VERSION_CODE, exportSchema = false
)

public abstract class PitjarusTrackingDatabaseDefinition extends RoomDatabase {
    public abstract StoreDao storeDao();
}
