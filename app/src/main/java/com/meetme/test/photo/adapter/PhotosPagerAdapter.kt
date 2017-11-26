package com.meetme.test.photo.adapter

import android.graphics.drawable.Drawable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.meetme.test.R
import com.meetme.test.extensions.visible
import com.meetme.test.photo.entity.Photo
import kotlinx.android.synthetic.main.photo_item.view.*


class PhotosPagerAdapter : PagerAdapter() {
    var photos: List<Photo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = photos.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.photo_item, container, false)
        bindPhoto(view, photos[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    private fun bindPhoto(view: View, photo: Photo) {
        Glide.with(view)
                .load(photo.url)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        view.photoProgressBar.visible = false
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean) = false

                })
                .into(view.photoImageView)
    }
}