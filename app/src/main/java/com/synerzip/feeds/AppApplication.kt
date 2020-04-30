package com.synerzip.feeds

import android.app.Application
import com.synerzip.feeds.dependencyinjection.DaggerFeedsComponent
import com.synerzip.feeds.dependencyinjection.FeedsComponent
import com.synerzip.feeds.dependencyinjection.FeedsModule

class AppApplication:Application() {
    companion object{
        lateinit var diComponent: FeedsComponent
    }

    override fun onCreate() {
        super.onCreate()
        diComponent = DaggerFeedsComponent.builder()
            .feedsModule(FeedsModule())
            .build()
    }
}