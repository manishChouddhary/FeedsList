package com.synerzip.feeds.ui.feedslist

import android.os.Bundle
import android.view.View
import com.synerzip.feeds.base.BaseFragment

class FeedsListFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFeedsUpdate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}