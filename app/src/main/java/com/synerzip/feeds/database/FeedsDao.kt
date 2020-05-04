package com.synerzip.feeds.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.synerzip.feeds.model.ImEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FeedsDao {
    @Query("Select * from entity_table")
    fun getFeedEntities(): Single<List<ImEntity>>

    @Insert(onConflict =  IGNORE)
    fun insert(entity : ImEntity)

    @Query("DELETE FROM entity_table")
    fun deleteAll()
}