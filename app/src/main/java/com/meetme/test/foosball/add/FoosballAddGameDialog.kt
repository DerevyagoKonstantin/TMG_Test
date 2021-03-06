package com.meetme.test.foosball.add

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.add.di.foosballAddGameModule
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.utils.getPlayersAdapter
import com.meetme.test.foosball.utils.getScoreAdapter
import kotlinx.android.synthetic.main.dialog_add_game.view.*


class FoosballAddGameDialog : BaseDialogFragment() {

    private val viewModelFactory by instance<FoosballAddGameViewModelFactory>()
    private lateinit var viewModel: FoosballAddGameViewModel

    override val viewId = R.layout.dialog_add_game

    override val titleTextId = R.string.foosball_add_game
    override val positiveTextId = R.string.save

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballAddGameModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballAddGameViewModel::class.java)

        viewModel.players.observe(this, Observer {
            val players = it ?: listOf()
            dialogView.addGameFirstPlayer.adapter = getPlayersAdapter(context!!, players)
            dialogView.addGameSecondPlayer.adapter = getPlayersAdapter(context!!, players)
            if (dialogView.addGameSecondPlayer.adapter.count > 1) {
                dialogView.addGameSecondPlayer.setSelection(1)
            }
        })

        dialogView.addGameFirstScore.adapter = getScoreAdapter(context!!)
        dialogView.addGameSecondScore.adapter = getScoreAdapter(context!!)
    }

    override fun positiveClick(): Boolean {
        return if (dialogView.addGameFirstPlayer.adapter.count > 0 && dialogView.addGameSecondPlayer.adapter.count > 0) {
            val firstPlayer = dialogView.addGameFirstPlayer.selectedItem as Player
            val secondPlayer = dialogView.addGameSecondPlayer.selectedItem as Player
            val firstScore = dialogView.addGameFirstScore.selectedItem as Int
            val secondScore = dialogView.addGameSecondScore.selectedItem as Int

            viewModel.saveGame(GameWithPlayers(firstPlayer, secondPlayer, firstScore, secondScore))

            true
        } else {
            Toast.makeText(context, R.string.foosball_game_empty_players, Toast.LENGTH_SHORT).show()
            false
        }
    }
}