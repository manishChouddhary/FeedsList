package com.synerzip.feeds.dependencyinjection

import com.synerzip.feeds.FeedsActivity
import dagger.Component

@Component(modules = [FeedsModule::class])
interface FeedsComponent {
    fun inject(activity: FeedsActivity)
}