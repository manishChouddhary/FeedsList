package com.synerzip.feeds.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.exception.FeedsException
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.model.ImEntity

class FeedsViewModel(private val repository: DataRepository): ViewModel() {

    val feedsLiveData:LiveData<FeedsResponse> = repository.feedsResponseLiveData
    val errorStateLiveData:LiveData<FeedsException> = repository.errorOnFeedsResponse
    val loadingStateLiveData :LiveData<Boolean> = repository.loadingStateLiveData
    val feedEntityLiveData : LiveData<List<ImEntity>> = repository.feedsEntityLiveData

    fun getFeedsUpdate() {
        repository.getFeeds()
    }
}