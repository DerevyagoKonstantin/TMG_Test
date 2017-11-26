package com.meetme.test.photo.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.meetme.test.photo.data.api.BASE_URL
import com.meetme.test.photo.data.api.PhotosApi
import com.meetme.test.photo.data.api.PhotosNetwork
import com.meetme.test.photo.data.api.PhotosRemoteSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val photosRemoteModule = Kodein.Module {
    val timeout = 10L

    bind<Gson>() with singleton {
        GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create()
    }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build()
    }

    bind<PhotosApi>() with singleton {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .client(instance())
                .build()
                .create(PhotosApi::class.java)
    }

    bind<PhotosRemoteSource>() with singleton {
        PhotosNetwork(instance())
    }
}