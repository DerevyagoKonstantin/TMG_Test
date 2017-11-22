package com.meetme.test.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein
import com.meetme.test.R


abstract class BaseDialogFragment : DialogFragment(), KodeinInjected {

    override val injector: KodeinInjector = KodeinInjector()

    open protected val viewId: Int = R.layout.empty
    protected lateinit var dialogView: View

    open protected val titleTextId = R.string.empty
    open protected val messageTextId = R.string.empty
    open protected val positiveTextId = R.string.ok
    open protected val negativeTextId = R.string.cancel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(initKodein())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context != null) {
            dialogView = LayoutInflater.from(context).inflate(viewId, null)
            initUI()

            val builder = AlertDialog.Builder(context!!)

            if (titleTextId != R.string.empty) {
                builder.setTitle(titleTextId)
            }
            if (messageTextId != R.string.empty) {
                builder.setMessage(messageTextId)
            }
            if (viewId != R.layout.empty) {
                builder.setView(dialogView)
            }

            val dialog = builder
                    .setPositiveButton(positiveTextId, null)
                    .setNegativeButton(negativeTextId, { _, _ -> negativeClick() })
                    .create()

            dialog.setOnShowListener {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                    if (positiveClick()) {
                        dialog.dismiss()
                    }
                }
            }

            dialog
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindVM()
    }

    open protected fun initUI() {
    }

    open protected fun bindVM() {
    }

    open protected fun positiveClick(): Boolean = true

    open protected fun negativeClick() {
    }

    private fun initKodein() = Kodein {
        extend(appKodein())
        bind<Context>() with provider { context!! }
        import(provideOverridingModule())
    }

    open protected fun provideOverridingModule() = Kodein.Module {
    }

}