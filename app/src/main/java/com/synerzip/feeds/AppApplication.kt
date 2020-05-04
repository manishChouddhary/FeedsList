package com.synerzip.feeds

import android.app.Application
import androidx.fragment.app.Fragment
import com.synerzip.feeds.dependencyinjection.DaggerFeedsComponent
import com.synerzip.feeds.dependencyinjection.FeedsComponent
import com.synerzip.feeds.dependencyinjection.FeedsModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppApplication : Application(), HasAndroidInjector {
    companion object {
        lateinit var diComponent: FeedsComponent
        lateinit var application: AppApplication
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector as AndroidInjector<Any>
    }

    override fun onCreate() {
        super.onCreate()
        diComponent = DaggerFeedsComponent.builder()
            .appModule(FeedsModule(applicationContext))
            .application(applicationContext)
            .build()
        diComponent.inject(this)
        application = this
    }
}