package com.meetme.test.photo

import android.arch.lifecycle.*
import com.meetme.test.photo.entity.Photo
import com.meetme.test.photo.usecase.GetPhotosUseCase


class PhotosViewModel(private val getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()
    val error = MutableLiveData<Unit>()
    val uploadFinished = MediatorLiveData<Unit>()

    val emptyVisibility: LiveData<Boolean> = Transformations.map(photos, { it.isEmpty() })
    val uploadVisibility = MutableLiveData<Boolean>()
    val uploadingVisibility = MutableLiveData<Boolean>()

    init {
        uploadPhotos()

        uploadFinished.addSource(photos, { uploadFinished.value = finishUpload() })
        uploadFinished.addSource(error, { uploadFinished.value = finishUpload() })
    }

    fun uploadPhotos() {
        uploadVisibility.value = false
        uploadingVisibility.value = true
        getPhotosUseCase.execute(GetPhotosUseCase.GetPhotosModel(photos, error))
    }

    private fun finishUpload() {
        uploadVisibility.value = true
        uploadingVisibility.value = false
    }
}