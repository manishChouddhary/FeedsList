package com.synerzip.feeds.comunication

import androidx.lifecycle.MutableLiveData
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.exception.FeedsException
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.model.ImEntity
import com.synerzip.feeds.network.CommunicationService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val GENERIC_ERROR_MESSAGE = "Some error occurred"

open class DataRepository(
    private val communicationService: CommunicationService,
    private val feedsDao: FeedsDao) {

    val loadingStateLiveData by lazy { MutableLiveData<Boolean>() }
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val errorOnFeedsResponse by lazy { MutableLiveData<FeedsException>() }

    var feedsEntityLiveData = MutableLiveData<List<ImEntity>>()

    private fun insert(entity: List<ImEntity>) {
        feedsDao.updateData(entity)
    }

    fun getFeeds(onLine: Boolean) {
        loadingStateLiveData.postValue(true)
        if (!onLine) {
            compositeDisposable.add(
                feedsDao.getFeedEntities().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        handleSuccessResponse(it)
                    }, {
                        handleErrorResponse(it)
                    })
            )
            return
        }
        val disposable = communicationService.getFeeds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it.feed.entry, true)
            }, {
                handleErrorResponse(it)
            })
        compositeDisposable.add(disposable)
    }

    private fun handleErrorResponse(exception: Throwable?) {
        loadingStateLiveData.postValue(false)
        errorOnFeedsResponse.value = FeedsException(exception?.message ?: GENERIC_ERROR_MESSAGE)
    }

    private fun handleSuccessResponse(feedsEntity: List<ImEntity>, deleteAll: Boolean = false) {
        loadingStateLiveData.postValue(false)
        if (deleteAll)
            compositeDisposable.add(Completable.fromAction {
                insert(feedsEntity)
            }.subscribeOn(Schedulers.io()).subscribe({}, { handleErrorResponse(it) }))
        feedsEntityLiveData.value = feedsEntity
    }
}