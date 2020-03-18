package com.c_od_e.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c_od_e.cache.db.ConfigConstants
import com.c_od_e.cache.model.Config
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class ConfigDao {

    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Maybe<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)

}