package com.meetme.test.twitter.data

import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

/**
 * Created by Konstantin on 16.11.2017.
 */
interface TwitterSource {

    fun getTimeline(search: String?): Timeline<Tweet>
}