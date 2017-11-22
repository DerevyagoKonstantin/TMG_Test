package com.meetme.test.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity

/**
 * Created by Konstantin on 13.11.2017.
 */
abstract class BaseActivity : KodeinAppCompatActivity() {
    abstract val viewId: Int

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewId)
        initUI()
        bindVM()
    }

    open protected fun initUI() {
    }

    open protected fun bindVM() {
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}