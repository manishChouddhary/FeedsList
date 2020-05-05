package com.synerzip.feeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.extentions.replace
import com.synerzip.feeds.ui.feedslist.FeedsListFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_feeds.*
import javax.inject.Inject

class FeedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        setUpToolbar()
        replace(FeedsListFragment.getInstance(),R.id.container,false,FeedsListFragment::class.java.canonicalName)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }
}
