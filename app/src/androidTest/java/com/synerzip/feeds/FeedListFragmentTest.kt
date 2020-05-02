package com.synerzip.feeds

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.synerzip.feeds.extentions.RecyclerViewMatcher
import io.mockk.MockKAnnotations
import io.mockk.every
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedListFragmentTest : TestActivityBase() {
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        setUpMocks()
    }

    @Test
    fun testForListIsShowing() {
        setUpFeedsListFragment()
        onView(RecyclerViewMatcher(R.id.rvFeedsList).atPositionOnView(0,R.id.tvContentTitle))
            .check(matches(isDisplayed()))
            .check(matches(withText(MockProvider.getMockEntity().name.label)))
        onView(RecyclerViewMatcher(R.id.rvFeedsList).atPositionOnView(0,R.id.ivContent))
            .check(matches(isDisplayed()))
            .check(matches(withContentDescription(MockProvider.getMockEntity().name.label)))
        onView(RecyclerViewMatcher(R.id.rvFeedsList).atPositionOnView(0,R.id.tvArtist))
            .check(matches(isDisplayed()))
            .check(matches(withText(MockProvider.getMockEntity().artist.label)))
        onView(RecyclerViewMatcher(R.id.rvFeedsList).atPositionOnView(0,R.id.tvCategory))
            .check(matches(isDisplayed()))
            .check(matches(withText(MockProvider.getMockEntity().category.attributes.label)))
        onView(RecyclerViewMatcher(R.id.rvFeedsList).atPositionOnView(0,R.id.tvReleased))
            .check(matches(isDisplayed()))
            .check(matches(withText(MockProvider.getMockEntity().releaseDate.attributes.label)))
    }

    private fun setUpMocks() {
        every{communicationService.getFeeds()} returns Single.just(MockProvider.getMockFeedsData())
    }
}