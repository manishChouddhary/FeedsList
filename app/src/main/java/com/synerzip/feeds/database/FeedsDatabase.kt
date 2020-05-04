package com.synerzip.feeds.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.synerzip.feeds.model.*

@Database(entities = [ImEntity::class], version = 1, exportSchema = false)
@TypeConverters(CommonTypeConverter::class)
abstract class FeedsDatabase: RoomDatabase() {
    abstract fun feedsDao(): FeedsDao

    private class FeedsDatabaseCallback() : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }
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
                )
                    .addCallback(FeedsDatabaseCallback())
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}