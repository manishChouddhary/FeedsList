package com.synerzip.feeds.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.synerzip.feeds.AppApplication
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.ui.FeedsViewModel
import com.synerzip.feeds.ui.ViewModelProviderFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var dataRepository: DataRepository

    lateinit var layoutId : ()->Int

    lateinit var viewModel: FeedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.diComponent.inject(this)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProviderFactory(FeedsViewModel::class){
                FeedsViewModel(dataRepository)
            }).get(FeedsViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = View.inflate(requireContext(),layoutId.invoke(),container)

    fun setUpToolBar(title: String) {
        (requireActivity() as AppCompatActivity).apply{
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setTitle(title)
            }
        }
    }
}