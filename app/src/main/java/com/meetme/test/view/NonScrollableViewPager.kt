package com.meetme.test.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


/**
 * Created by Konstantin on 13.11.2017.
 */
class NonScrollableViewPager : ViewPager {

    var isPagingEnable = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?) = isPagingEnable && super.onTouchEvent(ev)

    override fun onInterceptTouchEvent(ev: MotionEvent?) = isPagingEnable && super.onInterceptTouchEvent(ev)
}
