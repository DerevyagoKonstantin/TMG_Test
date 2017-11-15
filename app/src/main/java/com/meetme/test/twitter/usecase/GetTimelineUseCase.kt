package com.meetme.test.twitter.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.twitter.data.TwitterService
import com.meetme.test.twitter.data.TwitterSource
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

/**
 * Created by Konstantin on 16.11.2017.
 */
class GetTimelineUseCase : UseCase<String?, Timeline<Tweet>> {

    override fun execute(input: String?): Timeline<Tweet> {
        val twitterSource: TwitterSource = TwitterService()
        return twitterSource.getTimeline(input)
    }
}