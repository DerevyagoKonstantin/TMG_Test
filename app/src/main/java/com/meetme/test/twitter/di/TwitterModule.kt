package com.meetme.test.twitter.di

import com.github.salomonbrys.kodein.*
import com.meetme.test.twitter.data.TwitterService
import com.meetme.test.twitter.data.TwitterSource
import com.meetme.test.twitter.usecase.GetTimelineUseCase

/**
 * Created by Konstantin on 16.11.2017.
 */

val twitterModule = Kodein.Module {
    bind<TwitterSource>() with singleton { TwitterService() }

    bind<GetTimelineUseCase>() with provider { GetTimelineUseCase(instance()) }
}