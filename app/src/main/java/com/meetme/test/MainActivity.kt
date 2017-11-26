package com.meetme.test

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.meetme.test.base.BaseActivity
import com.meetme.test.extensions.hideKeyboard
import com.meetme.test.extensions.hideStatusBar
import com.meetme.test.extensions.showStatusBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val viewId = R.layout.activity_main

    private lateinit var adapter: MainPagerAdapter

    override fun initUI() {
        adapter = MainPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = adapter
        mainViewPager.offscreenPageLimit = adapter.count

        mainNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.main_navigation_twitter -> {
            mainViewPager.currentItem = MainPagerAdapter.TWITTER
            showStatusBar()
            true
        }
        R.id.main_navigation_photo -> {
            mainViewPager.currentItem = MainPagerAdapter.PHOTO
            hideStatusBar()
            hideKeyboard()
            true
        }
        R.id.main_navigation_foosball -> {
            mainViewPager.currentItem = MainPagerAdapter.FOOSBALL
            showStatusBar()
            true
        }
        else -> false
    }
}
