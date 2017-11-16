package com.meetme.test.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


/**
 * Created by Konstantin on 16.11.2017.
 */

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}