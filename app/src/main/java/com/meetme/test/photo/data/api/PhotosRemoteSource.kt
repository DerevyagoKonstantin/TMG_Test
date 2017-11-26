package com.meetme.test.photo.data.api

import android.arch.lifecycle.MutableLiveData
import com.meetme.test.photo.entity.Photo


interface PhotosRemoteSource {

    fun getPhotos(result: MutableLiveData<List<Photo>>, error: MutableLiveData<Unit>)
}