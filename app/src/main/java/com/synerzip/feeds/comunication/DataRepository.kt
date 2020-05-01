package com.synerzip.feeds.comunication

import androidx.lifecycle.MutableLiveData
import com.synerzip.feeds.exception.FeedsException
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.network.CommunicationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DataRepository(private val communicationService: CommunicationService) {

    val loadingStateLiveData = MutableLiveData<Boolean>()
    private val GENERIC_ERROR_MESSAGE = "Some error occured"
    val feedsResponseLiveData = MutableLiveData<FeedsResponse>()
    val errorOnFeedsResponse = MutableLiveData<FeedsException>()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getFeeds() {
        loadingStateLiveData.postValue(true)
       val disposable =  communicationService.getFeeds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            })
        compositeDisposable.add(disposable)
    }

    private fun handleErrorResponse(exception: Throwable?) {
        loadingStateLiveData.postValue(false)
        errorOnFeedsResponse.value = FeedsException(exception?.message ?: GENERIC_ERROR_MESSAGE)
    }

    private fun handleSuccessResponse(feedsResponse: FeedsResponse?) {
        loadingStateLiveData.postValue(false)
        feedsResponseLiveData.value = feedsResponse
    }
}