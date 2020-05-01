package com.synerzip.feeds.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.synerzip.feeds.AppApplication
import com.synerzip.feeds.R
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.extentions.getMaxHeightLink
import com.synerzip.feeds.ui.FeedsViewModel
import com.synerzip.feeds.ui.ViewModelProviderFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var dataRepository: DataRepository

    abstract var layoutId : ()->Int

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
        = inflater.inflate(layoutId.invoke(),container,false)

    open fun setUpToolBar(title: String?) {
        (requireActivity() as AppCompatActivity)?.apply{
            supportActionBar?.apply {
                Glide.with(requireContext())
                    .load(R.drawable.app)
                    .error(R.drawable.error_place_holder)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(findViewById(R.id.app_icon))

                findViewById<TextView>(R.id.app_title).text = title
            }
        }
    }
}