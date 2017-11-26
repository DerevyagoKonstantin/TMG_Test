package com.meetme.test.photo.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.photo.PhotosViewModelFactory
import com.meetme.test.photo.usecase.GetPhotosUseCase


val photosModule = Kodein.Module {
    bind<GetPhotosUseCase>() with provider { GetPhotosUseCase(instance()) }

    bind<PhotosViewModelFactory>() with provider { PhotosViewModelFactory(instance()) }
}