package com.meetme.test.foosball

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseFragment
import com.meetme.test.foosball.adapter.FoosballGamesAdapter
import com.meetme.test.foosball.add.FoosballAddGameDialog
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.delete.FoosballDeleteGameDialog
import com.meetme.test.foosball.di.foosballModule
import com.meetme.test.foosball.players.FoosballPlayersActivity
import com.meetme.test.foosball.update.FoosballUpdateGameDialog
import com.meetme.test.foosball.utils.getScrollListener
import kotlinx.android.synthetic.main.fragment_foosball.view.*

/**
 * Created by Konstantin on 13.11.2017.
 */
class FoosballFragment : BaseFragment() {

    private val viewModelFactory by instance<FoosballViewModelFactory>()
    private lateinit var viewModel: FoosballViewModel

    private val adapter = FoosballGamesAdapter()

    override val viewId = R.layout.fragment_foosball

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballModule)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.foosball_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.foosball_players -> {
            viewModel.openPlayers.value = Unit
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun initUI(view: View) {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(view.foosballToolbar)
        }
        setHasOptionsMenu(true)

        view.foosballGamesRecyclerView.adapter = adapter
        view.foosballGamesRecyclerView.addOnScrollListener(getScrollListener(view.foosballGameAdd))

        view.foosballGameAdd.setOnClickListener {
            viewModel.addGame.value = Unit
        }
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballViewModel::class.java)

        adapter.updateGame = viewModel.updateGame
        adapter.deleteGame = viewModel.deleteGame

        viewModel.games.observe(this, Observer {
            adapter.games = it ?: listOf()
        })

        viewModel.openPlayers.observe(this, Observer {
            openPlayers()
        })

        viewModel.addGame.observe(this, Observer {
            showAddGameDialog()
        })

        viewModel.updateGame.observe(this, Observer { game ->
            game?.let { showUpdateGameDialog(it) }
        })

        viewModel.deleteGame.observe(this, Observer { game ->
            game?.let { showDeleteGameDialog(it) }
        })
    }

    private fun openPlayers() {
        context?.let { FoosballPlayersActivity.open(it) }
    }

    private fun showAddGameDialog() {
        FoosballAddGameDialog().show(childFragmentManager, null)
    }

    private fun showUpdateGameDialog(game: Game) {
        FoosballUpdateGameDialog.getInstance(game.id).show(childFragmentManager, null)
    }

    private fun showDeleteGameDialog(game: Game) {
        FoosballDeleteGameDialog.getInstance(game.id).show(childFragmentManager, null)
    }

}