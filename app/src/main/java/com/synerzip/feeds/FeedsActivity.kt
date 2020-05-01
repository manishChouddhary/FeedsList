package com.synerzip.feeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.synerzip.feeds.extentions.replace
import com.synerzip.feeds.ui.feedslist.FeedsListFragment
import kotlinx.android.synthetic.main.activity_feeds.*

class FeedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        setUpToolbar()
        replace(FeedsListFragment(),R.id.container,false,FeedsListFragment::class.java.canonicalName)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }
}
