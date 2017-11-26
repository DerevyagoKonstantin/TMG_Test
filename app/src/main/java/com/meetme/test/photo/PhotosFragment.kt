package com.meetme.test.photo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseFragment
import com.meetme.test.extensions.visible
import com.meetme.test.photo.adapter.PhotosPagerAdapter
import com.meetme.test.photo.di.photosModule
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.fragment_photos.view.*

class PhotosFragment : BaseFragment() {

    private val viewModelFactory by instance<PhotosViewModelFactory>()

    private val adapter = PhotosPagerAdapter()

    override val viewId = R.layout.fragment_photos

    override fun provideOverridingModule() = Kodein.Module {
        import(photosModule)
    }

    override fun initUI(view: View) {
        view.photosViewPager.adapter = adapter
    }

    override fun bindVM() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PhotosViewModel::class.java)

        viewModel.photos.observe(this, Observer {
            adapter.photos = it ?: listOf()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(context, R.string.photos_error, Toast.LENGTH_SHORT).show()
        })

        viewModel.emptyVisibility.observe(this, Observer {
            photosEmptyView.visible = it ?: false
        })

        viewModel.uploadVisibility.observe(this, Observer {
            photosUploadButton.visible = it ?: false
        })

        viewModel.uploadingVisibility.observe(this, Observer {
            photosUploadingProgressBar.visible = it ?: false
        })

        viewModel.uploadFinished.observe(this, Observer { })

        photosUploadButton.setOnClickListener {
            viewModel.uploadPhotos()
        }
    }
}