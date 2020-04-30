package com.synerzip.feeds.network

import com.synerzip.feeds.model.FeedsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CommunicationService {
    @GET("/us/rss/newfreeapplications/limit=2/json")
    fun getFeeds() : Single<FeedsResponse>
}