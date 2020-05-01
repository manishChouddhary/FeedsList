package com.synerzip.feeds.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.synerzip.feeds.model.ImEntity

@Dao
interface FeedsDao {
    @Query("Select * from entity_table")
    fun getFeedEntities(): LiveData<List<ImEntity>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(entity: ImEntity)

    @Query("DELETE FROM entity_table")
    suspend fun deleteAll() {
        deleteAttributes()
        deleteCommons()
    }

    @Query("DELETE FROM attributes_table")
    suspend fun deleteAttributes()

    @Query("DELETE FROM commons_table")
    suspend fun deleteCommons()

}