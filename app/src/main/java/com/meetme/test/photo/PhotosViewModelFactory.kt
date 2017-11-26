package com.meetme.test.photo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.photo.usecase.GetPhotosUseCase


class PhotosViewModelFactory(private val getPhotosUseCase: GetPhotosUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PhotosViewModel(getPhotosUseCase) as T
}