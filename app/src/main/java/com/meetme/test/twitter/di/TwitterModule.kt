package com.meetme.test.twitter.di

import com.github.salomonbrys.kodein.*
import com.meetme.test.twitter.TwitterViewModelFactory
import com.meetme.test.twitter.data.TwitterService
import com.meetme.test.twitter.data.TwitterSource
import com.meetme.test.twitter.usecase.GetTimelineUseCase

val twitterModule = Kodein.Module {
    bind<TwitterSource>() with singleton { TwitterService() }

    bind<GetTimelineUseCase>() with provider { GetTimelineUseCase(instance()) }

    bind<TwitterViewModelFactory>() with provider { TwitterViewModelFactory(instance()) }
}