package com.projects.pitjarus_tracking.connections.databases.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.projects.pitjarus_tracking.models.StoreModel;

import java.util.List;

/**
 * Created by Sohibun Nawawi on 13/03/2021.
 */

@Dao

public interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert (StoreModel storeModel);

    @Query("SELECT * FROM StoreModel ORDER BY 1 DESC LIMIT :pageNo, :pageSize")
    List<StoreModel> getAllStore (Integer pageNo, Integer pageSize);
}
