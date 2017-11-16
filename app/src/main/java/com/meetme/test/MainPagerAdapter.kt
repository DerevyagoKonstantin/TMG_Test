package com.meetme.test

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.meetme.test.foosball.FoosballFragment
import com.meetme.test.photo.PhotoFragment
import com.meetme.test.twitter.TwitterFragment

/**
 * Created by Konstantin on 13.11.2017.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    companion object {
        val TWITTER = 0
        val PHOTO = 1
        val FOOSBALL = 2
    }

    override fun getCount() = 3

    override fun getItem(position: Int) = when (position) {
        0 -> TwitterFragment()
        1 -> PhotoFragment()
        else -> FoosballFragment()
    }
}