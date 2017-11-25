package com.meetme.test.twitter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.twitter.usecase.GetTimelineUseCase
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

class TwitterViewModel(getTimelineUseCase: GetTimelineUseCase) : ViewModel() {

    val searchQuery = MutableLiveData<String>()
    val timeline: LiveData<Timeline<Tweet>> = Transformations.map(searchQuery, {
        getTimelineUseCase.execute(it)
    })
}