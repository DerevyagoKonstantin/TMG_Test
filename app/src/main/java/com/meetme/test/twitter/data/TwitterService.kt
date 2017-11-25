package com.meetme.test.twitter.data

import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.SearchTimeline
import com.twitter.sdk.android.tweetui.Timeline

class TwitterService : TwitterSource {

    override fun getTimeline(search: String?): Timeline<Tweet> = SearchTimeline.Builder()
            .query(search)
            .build()
}