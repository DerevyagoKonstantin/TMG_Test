package com.meetme.test.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Konstantin on 13.11.2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract val viewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewId)
    }
}