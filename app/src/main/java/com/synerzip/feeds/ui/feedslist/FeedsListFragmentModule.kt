package com.synerzip.feeds.ui.feedslist

import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.dependencyinjection.FragmentScope
import com.synerzip.feeds.ui.FeedsViewModel
import dagger.Module
import dagger.Provides

@Module
class FeedsListFragmentModule {
    @Provides
    @FragmentScope
    fun provideViewModel(dataRepository: DataRepository): FeedsViewModel = FeedsViewModel(dataRepository)
}