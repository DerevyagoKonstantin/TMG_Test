package com.meetme.test.twitter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.meetme.test.twitter.usecase.GetTimelineUseCase
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline

/**
 * Created by Konstantin on 16.11.2017.
 */
class TwitterViewModel : ViewModel() {

    val searchQuery = MutableLiveData<String>()
    private val searchQueryObserver = MediatorLiveData<Timeline<Tweet>>()

    val timeline: LiveData<Timeline<Tweet>> = searchQueryObserver

    init {
        searchQueryObserver.addSource(searchQuery, { search: String? ->
            searchQueryObserver.value = GetTimelineUseCase().execute(search)
        })
    }
}