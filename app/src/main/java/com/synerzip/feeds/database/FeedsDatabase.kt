package com.synerzip.feeds.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.synerzip.feeds.model.Attributes
import com.synerzip.feeds.model.Common
import com.synerzip.feeds.model.ImEntity

@Database(entities = [ImEntity::class, Attributes::class, Common::class], version = 1, exportSchema = false)
abstract class FeedsDatabase: RoomDatabase() {
    abstract fun feedsDao(): FeedsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FeedsDatabase? = null

        fun getDatabase(context: Context): FeedsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeedsDatabase::class.java,
                    "feed_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}