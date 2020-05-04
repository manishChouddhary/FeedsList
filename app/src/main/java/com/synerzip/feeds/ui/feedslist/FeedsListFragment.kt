package com.synerzip.feeds.ui.feedslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.synerzip.feeds.AppApplication
import com.synerzip.feeds.R
import com.synerzip.feeds.base.BaseFragment
import com.synerzip.feeds.model.ImEntity
import com.synerzip.feeds.ui.FeedsViewModel
import kotlinx.android.synthetic.main.fragment_feeds_list.*
import javax.inject.Inject

class FeedsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: FeedsViewModel

    private lateinit var rvAdapter: FeedsListAdaptor

    override var layoutId: () -> Int = { R.layout.fragment_feeds_list }

    companion object{
        fun getInstance(): FeedsListFragment{
            return FeedsListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.application.androidInjector().inject(this)
        viewModel.getFeedsUpdate(requireContext().hasNetworkAvailable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpToolBar(getString(R.string.app_name))
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