package com.meetme.test.foosball.players.delete

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.data.db.entity.GAME_ID
import com.meetme.test.foosball.data.db.entity.PLAYER_ID
import com.meetme.test.foosball.players.delete.di.foosballDeletePlayerModule


class FoosballDeletePlayerDialog : BaseDialogFragment() {

    companion object {
        fun getInstance(playerId: Long): FoosballDeletePlayerDialog {
            val bundle = Bundle()
            bundle.putLong(PLAYER_ID, playerId)

            val dialog = FoosballDeletePlayerDialog()
            dialog.arguments = bundle

            return dialog
        }
    }

    private val viewModelFactory by instance<FoosballDeletePlayerViewModelFactory>()
    private lateinit var viewModel: FoosballDeletePlayerViewModel

    override val titleTextId = R.string.foosball_delete_player
    override val messageTextId = R.string.foosball_delete_player_message
    override val positiveTextId = R.string.delete

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballDeletePlayerModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballDeletePlayerViewModel::class.java)

        viewModel.playerId.value = arguments?.getLong(GAME_ID)

        viewModel.player.observe(this, Observer { })

        viewModel.deletePlayerObserver.observe(this, Observer {
            Toast.makeText(context, getString(R.string.foosball_game_delete, it), Toast.LENGTH_SHORT).show()
        })
    }

    override fun positiveClick(): Boolean {
        viewModel.deletePlayer.value = Unit

        return true
    }
}