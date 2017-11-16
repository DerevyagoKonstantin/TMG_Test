package com.meetme.test.twitter.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.twitter.data.TwitterSource
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

/**
 * Created by Konstantin on 16.11.2017.
 */
class GetTimelineUseCase(private val twitterSource: TwitterSource) : UseCase<String?, Timeline<Tweet>> {

    override fun execute(input: String?) = twitterSource.getTimeline(input)
}