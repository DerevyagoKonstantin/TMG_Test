package com.meetme.test.photo.data.api

import retrofit2.Call
import retrofit2.http.GET


const val BASE_URL = "http://www.echo-photo.com/"

interface PhotosApi {

    @GET("gallery/")
    fun getPhotos(): Call<Any>
}