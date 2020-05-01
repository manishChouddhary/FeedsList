package com.synerzip.feeds.dependencyinjection

import android.app.Application
import com.synerzip.feeds.AppApplication
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.database.FeedsDatabase
import com.synerzip.feeds.network.CommunicationService
import com.synerzip.feeds.network.NetworkClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FeedsModule {
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return NetworkClient().getRetrofitClient()
    }

    @Provides
    fun provideCommunicationService(retrofit: Retrofit) : CommunicationService {
        return retrofit.create(CommunicationService::class.java)
    }

    @Provides
    fun provideApplication():Application = AppApplication.application

    @Provides
    fun provideFeedsDao(application : Application) : FeedsDao{
        return FeedsDatabase.getDatabase(application).feedsDao()
    }

    @Provides
    fun provideDataRepository(communicationService: CommunicationService, feedsDao: FeedsDao): DataRepository {
        return DataRepository(communicationService, feedsDao)
    }
}