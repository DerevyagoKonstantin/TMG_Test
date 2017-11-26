package com.meetme.test.foosball.players.update

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.data.db.entity.PLAYER_ID
import com.meetme.test.foosball.players.update.di.foosballUpdatePlayerModule
import kotlinx.android.synthetic.main.dialog_update_player.view.*


class FoosballUpdatePlayerDialog : BaseDialogFragment() {

    companion object {
        fun getInstance(playerId: Long): FoosballUpdatePlayerDialog {
            val bundle = Bundle()
            bundle.putLong(PLAYER_ID, playerId)

            val dialog = FoosballUpdatePlayerDialog()
            dialog.arguments = bundle

            return dialog
        }
    }

    private val viewModelFactory by instance<FoosballUpdatePlayerViewModelFactory>()
    private lateinit var viewModel: FoosballUpdatePlayerViewModel

    override val viewId = R.layout.dialog_update_player

    override val titleTextId = R.string.foosball_update_player
    override val positiveTextId = R.string.update

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballUpdatePlayerModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballUpdatePlayerViewModel::class.java)

        viewModel.playerId.value = arguments?.getLong(PLAYER_ID)

        viewModel.player.observe(this, Observer { player ->
            if (player != null) {
                dialogView.updatePlayerFirstName.setText(player.firstName)
                dialogView.updatePlayerFirstName.setSelection(player.firstName.length)
                dialogView.updatePlayerLastName.setText(player.lastName)
            }
        })

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun positiveClick(): Boolean {
        val firstName = dialogView.updatePlayerFirstName.text.toString()
        val lastName = dialogView.updatePlayerLastName.text.toString()

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName)) {
            Toast.makeText(context, R.string.foosball_player_empty_name, Toast.LENGTH_SHORT).show()
            return false
        } else {
            val player = viewModel.player.value
            if (player != null) {
                player.firstName = firstName
                player.lastName = lastName
                viewModel.updatePlayer(player)
            }
        }

        return true
    }
}