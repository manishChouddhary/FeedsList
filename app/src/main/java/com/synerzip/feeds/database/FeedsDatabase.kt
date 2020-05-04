package com.synerzip.feeds.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.synerzip.feeds.model.CommonTypeConverter
import com.synerzip.feeds.model.ImEntity

@Database(entities = [ImEntity::class], version = 1, exportSchema = false)
@TypeConverters(CommonTypeConverter::class)
abstract class FeedsDatabase: RoomDatabase() {
    abstract fun feedsDao(): FeedsDao
}