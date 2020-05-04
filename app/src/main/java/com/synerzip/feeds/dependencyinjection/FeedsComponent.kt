package com.synerzip.feeds.dependencyinjection

import android.content.Context
import com.synerzip.feeds.AppApplication
import com.synerzip.feeds.FeedsActivity
import com.synerzip.feeds.ui.feedslist.FeedsListFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class,FeedsModule::class])
@Singleton
interface FeedsComponent {
    fun inject(application: AppApplication)
    @Component.Builder
    abstract class Builder {
        abstract fun build() : FeedsComponent
        abstract fun appModule(appModule: FeedsModule): Builder

        @BindsInstance
        abstract fun application(context: Context): Builder
    }
}