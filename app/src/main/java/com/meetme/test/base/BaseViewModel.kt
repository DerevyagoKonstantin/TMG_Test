package com.meetme.test.base

import android.arch.lifecycle.ViewModel
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector

/**
 * Created by Konstantin on 16.11.2017.
 */
abstract class BaseViewModel : ViewModel(), KodeinInjected {
    override val injector = KodeinInjector()
}