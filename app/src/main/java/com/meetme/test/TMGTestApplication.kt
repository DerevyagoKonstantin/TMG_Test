package com.meetme.test

import android.app.Application
import com.twitter.sdk.android.core.Twitter

/**
 * Created by Konstantin on 12.11.2017.
 */
class TMGTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTwitter()
    }

    private fun initTwitter() {
        Twitter.initialize(this)
    }
}