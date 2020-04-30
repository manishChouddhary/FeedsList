package com.synerzip.feeds.dependencyinjection

import com.synerzip.feeds.comunication.DataRepository
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
    fun provideDataRepository(communicationService: CommunicationService): DataRepository {
        return DataRepository(communicationService)
    }
}