package com.synerzip.feeds.dependencyinjection

import com.synerzip.feeds.ui.feedslist.FeedsListFragment
import com.synerzip.feeds.ui.feedslist.FeedsListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [FeedsListFragmentModule::class])
    abstract fun feedsFragment(): FeedsListFragment
}