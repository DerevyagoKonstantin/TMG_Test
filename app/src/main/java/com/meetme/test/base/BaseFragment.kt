package com.meetme.test.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Konstantin on 13.11.2017.
 */
abstract class BaseFragment : Fragment() {

    abstract val viewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewId, container, false)
    }
}