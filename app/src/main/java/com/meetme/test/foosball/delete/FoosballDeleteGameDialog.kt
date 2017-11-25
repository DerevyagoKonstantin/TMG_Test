package com.meetme.test.foosball.delete

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.data.db.entity.GAME_ID
import com.meetme.test.foosball.delete.di.foosballDeleteGameModule


class FoosballDeleteGameDialog : BaseDialogFragment() {

    companion object {
        fun getInstance(gameId: Long): FoosballDeleteGameDialog {
            val bundle = Bundle()
            bundle.putLong(GAME_ID, gameId)

            val dialog = FoosballDeleteGameDialog()
            dialog.arguments = bundle

            return dialog
        }
    }

    private val viewModelFactory by instance<FoosballDeleteGameViewModelFactory>()
    private lateinit var viewModel: FoosballDeleteGameViewModel

    override val titleTextId = R.string.foosball_delete_game
    override val messageTextId = R.string.foosball_delete_game_message
    override val positiveTextId = R.string.delete

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballDeleteGameModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballDeleteGameViewModel::class.java)

        viewModel.gameId.value = arguments?.getLong(GAME_ID)

        viewModel.game.observe(this, Observer { })

        viewModel.deleteGameObserver.observe(this, Observer {
            if (it != null) {
                Toast.makeText(context, getString(R.string.foosball_game_delete, it), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun positiveClick(): Boolean {
        viewModel.deleteGame.value = Unit

        return true
    }
}