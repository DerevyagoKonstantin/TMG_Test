package com.meetme.test.twitter.data

import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

interface TwitterSource {

    fun getTimeline(search: String?): Timeline<Tweet>
}