package com.meetme.test.twitter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.twitter.usecase.GetTimelineUseCase


class TwitterViewModelFactory(private val getTimelineUseCase: GetTimelineUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TwitterViewModel(getTimelineUseCase) as T
}