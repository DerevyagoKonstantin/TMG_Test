package com.meetme.test.photo.usecase

import android.arch.lifecycle.MutableLiveData
import com.meetme.test.base.UseCase
import com.meetme.test.photo.data.api.PhotosRemoteSource
import com.meetme.test.photo.entity.Photo


class GetPhotosUseCase(
        private val photosRemoteSource: PhotosRemoteSource
) : UseCase<GetPhotosUseCase.GetPhotosModel, Unit> {

    override fun execute(input: GetPhotosModel) {
        photosRemoteSource.getPhotos(input.result, input.error)
    }

    data class GetPhotosModel(val result: MutableLiveData<List<Photo>>, val error: MutableLiveData<Unit>)
}