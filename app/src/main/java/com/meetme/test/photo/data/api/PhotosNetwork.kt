package com.meetme.test.photo.data.api

import android.arch.lifecycle.MutableLiveData
import com.meetme.test.photo.entity.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotosNetwork(private val api: PhotosApi) : PhotosRemoteSource {

    override fun getPhotos(result: MutableLiveData<List<Photo>>, error: MutableLiveData<Unit>) {
        api.getPhotos().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response != null && response.isSuccessful) {
//                    result.postValue(response.body())
                    result.postValue(getDummyPhotos())
                } else {
                    error.postValue(Unit)
                }
            }

            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                error.postValue(Unit)
            }
        })
    }

    // for testing
    private fun getDummyPhotos() = listOf(
            Photo("http://mirpozitiva.ru/uploads/posts/2016-09/1474011210_15.jpg"),
            Photo("http://cdn.fishki.net/upload/post/2017/03/19/2245758/tn/02-funny-cat-wallpapercat-wallpaper.jpg"),
            Photo("http://bm.img.com.ua/nxs/img/prikol/images/large/3/9/315193.jpg"),
            Photo("http://minionomaniya.ru/wp-content/uploads/2016/01/%D0%BC%D0%B8%D0%BD%D1%8C%D0%BE%D0%BD%D1%8B-%D0%BF%D1%80%D0%B8%D0%BA%D0%BE%D0%BB%D1%8B-%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8.jpg"),
            Photo("http://minionomaniya.ru/wp-content/uploads/2015/10/%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8-%D0%B8%D0%B7-%D0%BC%D1%83%D0%BB%D1%8C%D1%82%D1%84%D0%B8%D0%BB%D1%8C%D0%BC%D0%B0-%D0%BC%D0%B8%D0%BD%D1%8C%D0%BE%D0%BD%D1%8B-2015.jpg"),
            Photo("http://www.cruzo.net/user/images/k/ecc3ecf42c75db1ffce5d06cbe95b1e6_644.jpg"),
            Photo("https://cs8.pikabu.ru/post_img/2017/11/09/4/1510200277156090261.jpg"),
            Photo("http://www.coolwebmasters.com/uploads/posts/2011-03/1298971265_animated-gif-22.gif"),
            Photo("https://media.giphy.com/media/3NtY188QaxDdC/giphy.gif"),
            Photo("https://s-media-cache-ak0.pinimg.com/originals/81/e2/55/81e255e0ceeca98084787662c4f7814c.gif")
    )
}