package com.synerzip.feeds.comunication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.exception.FeedsException
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.model.ImEntity
import com.synerzip.feeds.network.CommunicationService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class DataRepository(private val communicationService: CommunicationService, private val feedsDao: FeedsDao) {

    companion object{
        const val GENERIC_ERROR_MESSAGE = "Some error occurred"
    }
    val loadingStateLiveData = MutableLiveData<Boolean>()
    val feedsResponseLiveData = MutableLiveData<FeedsResponse>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val errorOnFeedsResponse = MutableLiveData<FeedsException>()
    val feedsEntityLiveData : LiveData<List<ImEntity>> = feedsDao.getFeedEntities()

    suspend fun insert(entity: ImEntity) {
        feedsDao.insert(entity)
    }

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
        /*suspend {
            feedsResponse?.feed?.entry?.forEach { feedsDao.insert(it) }
        }*/
        (feedsEntityLiveData as MutableLiveData).value = feedsResponse?.feed?.entry
    }


}