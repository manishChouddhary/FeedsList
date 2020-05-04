package com.synerzip.feeds.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.synerzip.feeds.R

abstract class BaseFragment : Fragment() {

    abstract var layoutId : ()->Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(layoutId.invoke(),container,false)

    open fun setUpToolBar(title: String?) {
        (requireActivity() as AppCompatActivity).apply{
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