package com.synerzip.feeds.ui.feedslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.synerzip.feeds.R
import com.synerzip.feeds.base.BaseFragment
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.extentions.getColumnCountOnOrientation
import com.synerzip.feeds.extentions.getDimen
import com.synerzip.feeds.extentions.gone
import com.synerzip.feeds.extentions.visible
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.model.ImEntity
import kotlinx.android.synthetic.main.fragment_feeds_list.*

class FeedsListFragment : BaseFragment() {

    private lateinit var rvAdapter: FeedsListAdaptor

    override var layoutId: () -> Int = { R.layout.fragment_feeds_list }

    companion object{
        fun getInstance(dataRepository: DataRepository): FeedsListFragment{
            return FeedsListFragment().apply {
                this.dataRepository = dataRepository
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFeedsUpdate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeLoadingState()
        observeListState()
        observeErrorState()
    }

    private fun setUpRecyclerView() {
        with(rvFeedsList){
            rvAdapter = FeedsListAdaptor()
            adapter = rvAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                this.getColumnCountOnOrientation(requireContext()),
                GridLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                SpacesItemDecoration(
                    sideSpace = requireContext().getDimen(R.dimen.item_side_spacing),
                    bottomSpace = requireContext().getDimen(R.dimen.item_bottom_spacing),
                    topSpace = requireContext().getDimen(R.dimen.item_top_spacing),
                    orentationSpan = getColumnCountOnOrientation(requireContext())
                )
            )
        }
    }

    private fun observeLoadingState() {
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner, Observer{
            when(it){
                true -> { pvLoading?.visible() }
                false -> { pvLoading?.gone() }
            }
        })
    }

    private fun observeListState() {
        viewModel.feedEntityLiveData.observe(viewLifecycleOwner, Observer<List<ImEntity>> {
            rvAdapter.setList(it)
            rvFeedsList?.visible()
        })
    }

    private fun observeErrorState() {
        viewModel.errorStateLiveData.observe(viewLifecycleOwner, Observer{
            tvError?.visible()
            tvError?.text = it.message
        })
    }
}