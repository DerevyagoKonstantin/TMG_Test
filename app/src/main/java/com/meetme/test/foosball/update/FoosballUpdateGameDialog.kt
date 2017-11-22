package com.meetme.test.foosball.update

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.data.db.entity.GAME_ID
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.update.di.foosballUpdateGameModule
import com.meetme.test.foosball.utils.getPlayersAdapter
import com.meetme.test.foosball.utils.getScoreAdapter
import kotlinx.android.synthetic.main.dialog_update_game.view.*


class FoosballUpdateGameDialog : BaseDialogFragment() {

    companion object {
        fun getInstance(playerId: Long): FoosballUpdateGameDialog {
            val bundle = Bundle()
            bundle.putLong(GAME_ID, playerId)

            val dialog = FoosballUpdateGameDialog()
            dialog.arguments = bundle

            return dialog
        }
    }

    private val viewModelFactory by instance<FoosballUpdateGameViewModelFactory>()
    private lateinit var viewModel: FoosballUpdateGameViewModel

    override val viewId = R.layout.dialog_update_game

    override val titleTextId = R.string.foosball_update_game
    override val positiveTextId = R.string.update

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballUpdateGameModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballUpdateGameViewModel::class.java)

        viewModel.gameId.value = arguments?.getLong(GAME_ID)

        viewModel.game.observe(this, Observer { game ->
            if (game != null) {
                setSpinnerPosition(dialogView.updateGameFirstPlayer, game.getFirstPlayer())
                setSpinnerPosition(dialogView.updateGameSecondPlayer, game.getSecondPlayer())
                setSpinnerPosition(dialogView.updateGameFirstScore, game.firstScore)
                setSpinnerPosition(dialogView.updateGameSecondScore, game.secondScore)
            }
        })

        viewModel.players.observe(this, Observer {
            val players = it ?: listOf()
            dialogView.updateGameFirstPlayer.adapter = getPlayersAdapter(context!!, players)
            dialogView.updateGameSecondPlayer.adapter = getPlayersAdapter(context!!, players)

            val game = viewModel.game.value
            if (game != null) {
                setSpinnerPosition(dialogView.updateGameFirstPlayer, game.getFirstPlayer())
                setSpinnerPosition(dialogView.updateGameSecondPlayer, game.getSecondPlayer())
            }
        })

        dialogView.updateGameFirstScore.adapter = getScoreAdapter(context!!)
        dialogView.updateGameSecondScore.adapter = getScoreAdapter(context!!)

        viewModel.updateGameObserver.observe(this, Observer {
            Toast.makeText(context, getString(R.string.foosball_game_update, it), Toast.LENGTH_SHORT).show()
        })
    }

    private fun <T> setSpinnerPosition(spinner: Spinner, item: T) {
        val adapter = spinner.adapter
        if (adapter != null) {
            spinner.setSelection((adapter as ArrayAdapter<T>).getPosition(item))
        }
    }

    override fun positiveClick(): Boolean {
        val firstPlayer = (dialogView.updateGameFirstPlayer.selectedItem as Player).id
        val secondPlayer = (dialogView.updateGameSecondPlayer.selectedItem as Player).id
        val firstScore = dialogView.updateGameFirstScore.selectedItem as Int
        val secondScore = dialogView.updateGameSecondScore.selectedItem as Int

        val game = viewModel.game.value
        if (game != null) {
            viewModel.updateGame.value = Game(game.id, firstPlayer, secondPlayer, firstScore, secondScore)
        }

        return true
    }
}