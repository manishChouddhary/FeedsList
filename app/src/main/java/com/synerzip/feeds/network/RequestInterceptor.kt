package com.synerzip.feeds.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    companion object {
        const val CONTAINER: String = "container"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        request.url.queryParameter(CONTAINER)
        return chain.proceed(requestBuilder.build())
    }
}