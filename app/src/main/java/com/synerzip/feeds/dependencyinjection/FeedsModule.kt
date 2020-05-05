package com.synerzip.feeds.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.database.FeedsDatabase
import com.synerzip.feeds.network.CommunicationService
import com.synerzip.feeds.network.NetworkClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [FragmentBuilder::class])
class FeedsModule(private val context : Context) {
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return NetworkClient().getRetrofitClient()
    }

    @Provides
    fun provideCommunicationService(retrofit: Retrofit) : CommunicationService {
        return retrofit.create(CommunicationService::class.java)
    }

    @Provides
    fun getFeedsDatabase(): FeedsDatabase {
        return Room.databaseBuilder(
            context,
            FeedsDatabase::class.java,
            "feed_database")
            .build()
    }
    @Provides
    fun provideFeedsDao(feedsDatabase: FeedsDatabase) : FeedsDao{
        return feedsDatabase.feedsDao()
    }

    @Provides @Singleton
    fun provideDataRepository(communicationService: CommunicationService, feedsDao: FeedsDao): DataRepository {
        return DataRepository(communicationService, feedsDao)
    }
}