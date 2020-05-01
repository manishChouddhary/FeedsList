package com.synerzip.feeds.ui.feedslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.synerzip.feeds.R
import com.synerzip.feeds.databinding.FeedItemBinding
import com.synerzip.feeds.extentions.getMaxHeightLink
import com.synerzip.feeds.model.ImEntity

class FeedsListAdaptor : RecyclerView.Adapter<FeedsListAdaptor.ViewHolder>() {
    var contentItems: List<ImEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FeedItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contentItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(contentItems[position])

    fun setList(contentList: List<ImEntity>) {
        val listSize = contentItems.size
        (contentItems as ArrayList).addAll(contentList)
        notifyItemRangeChanged(if (listSize <= 0) 0 else listSize - 1, contentItems.size)
    }

    inner class ViewHolder(private val cItemView: FeedItemBinding) :
        RecyclerView.ViewHolder(cItemView.root) {
        fun bind(content: ImEntity) {
            cItemView.contentItem = content
            Glide.with(cItemView.root.context)
                .load(content.image.getMaxHeightLink())
                .error(R.drawable.error_place_holder)
                .placeholder(R.drawable.ic_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(cItemView.ivContent)
        }
    }
}