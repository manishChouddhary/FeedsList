package com.synerzip.feeds

import android.app.Instrumentation
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.network.CommunicationService
import com.synerzip.feeds.ui.feedslist.FeedsListFragment
import io.mockk.impl.annotations.MockK

open class TestActivityBase {

    @JvmField
    val activityRule = ActivityTestRule(TestActivity::class.java,false,false)

    @MockK
    lateinit var communicationService: CommunicationService

    @MockK
    lateinit var feedsDao: FeedsDao

    lateinit var dataRepository: DataRepository

    lateinit var feedsFragment: FeedsListFragment

    private lateinit var instrumentation : Instrumentation

    fun setUpFeedsListFragment() {
        instrumentation = InstrumentationRegistry.getInstrumentation()
        activityRule.launchActivity(null)
        dataRepository = DataRepository(communicationService , feedsDao)
        feedsFragment = FeedsListFragment.getInstance()
        val fragmentManager = activityRule.activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container,feedsFragment)
            .commit()
        instrumentation.waitForIdleSync()
    }
}