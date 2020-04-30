package com.synerzip.feeds.dependencyinjection

import com.synerzip.feeds.base.BaseFragment
import dagger.Component

@Component(modules = [FeedsModule::class])
interface FeedsComponent {
    fun inject(fragment: BaseFragment)
}