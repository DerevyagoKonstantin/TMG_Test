package com.meetme.test.twitter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.AnimatedVectorDrawable
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseFragment
import com.meetme.test.twitter.di.twitterModule
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.TimelineResult
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_twitter.*
import kotlinx.android.synthetic.main.fragment_twitter.view.*

class TwitterFragment : BaseFragment() {

    private val viewModelFactory by instance<TwitterViewModelFactory>()

    override val viewId = R.layout.fragment_twitter

    override fun provideOverridingModule() = Kodein.Module {
        import(twitterModule)
    }

    override fun initUI(view: View) {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(view.twitterToolbar)
        }

        val drawable = context?.getDrawable(R.drawable.twitter_anim) as AnimatedVectorDrawable
        view.twitterEmptyView.setImageDrawable(drawable)
        drawable.start()
    }

    override fun bindVM() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TwitterViewModel::class.java)

        twitterSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchQuery.value = p0.toString()
            }
        })

        twitterSwipeRefresh.setOnRefreshListener {
            val adapter = twitterRecyclerView.adapter
            if (adapter is TweetTimelineRecyclerViewAdapter) {
                adapter.refresh(object : Callback<TimelineResult<Tweet>>() {
                    override fun success(result: Result<TimelineResult<Tweet>>?) {
                        twitterSwipeRefresh.isRefreshing = false
                    }

                    override fun failure(exception: TwitterException?) {
                        twitterSwipeRefresh.isRefreshing = false
                        Toast.makeText(context, R.string.twitter_refresh_failed, Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                twitterSwipeRefresh.isRefreshing = false
            }
        }

        viewModel.timeline.observe(this, Observer {
            twitterRecyclerView.adapter = TweetTimelineRecyclerViewAdapter.Builder(context)
                    .setTimeline(it)
                    .setViewStyle(R.style.tw__TweetLightStyle)
                    .build()
        })
    }
}