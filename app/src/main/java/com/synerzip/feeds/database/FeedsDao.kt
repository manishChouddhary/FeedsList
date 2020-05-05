package com.synerzip.feeds.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.synerzip.feeds.model.ImEntity
import io.reactivex.Single

@Dao
interface FeedsDao {
    @Query("Select * from entity_table")
    fun getFeedEntities(): Single<List<ImEntity>>

    @Transaction
    fun updateData(entities : List<ImEntity>) {
        deleteAll()
        insertAll(entities)
    }

    @Insert(onConflict =  REPLACE)
    fun insertAll(entity : List<ImEntity>)

    @Query("DELETE FROM entity_table")
    fun deleteAll()
}