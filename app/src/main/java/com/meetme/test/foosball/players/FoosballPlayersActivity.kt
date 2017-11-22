package com.meetme.test.foosball.players

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseActivity
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.adapter.FoosballPlayersAdapter
import com.meetme.test.foosball.players.add.FoosballAddPlayerDialog
import com.meetme.test.foosball.players.di.foosballPlayersModule
import com.meetme.test.foosball.players.update.FoosballUpdatePlayerDialog
import kotlinx.android.synthetic.main.activity_foosball_players.*


class FoosballPlayersActivity : BaseActivity() {

    companion object {
        fun open(context: Context) {
            Intent(context, FoosballPlayersActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    private val adapter = FoosballPlayersAdapter()

    private val viewModelFactory by instance<FoosballPlayersViewModelFactory>()

    override val viewId = R.layout.activity_foosball_players

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballPlayersModule)
    }

    override fun initUI() {
        foosballPlayersRecyclerView.adapter = adapter

        foosballPlayerAdd.setOnClickListener { showAddPlayerDialog() }
    }

    override fun bindVM() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballPlayersViewModel::class.java)

        adapter.updatePlayer = viewModel.updatePlayer
        adapter.deletePlayer = viewModel.deletePlayer

        viewModel.updatePlayer.observe(this, Observer { player ->
            player?.let { showUpdatePlayerDialog(it) }
        })

        viewModel.deletePlayerObserver.observe(this, Observer {
            Toast.makeText(this, getString(R.string.foosball_player_delete, it), Toast.LENGTH_SHORT).show()
        })

        viewModel.players.observe(this, Observer {
            adapter.players = it ?: listOf()
        })
    }

    private fun showAddPlayerDialog() {
        FoosballAddPlayerDialog().show(supportFragmentManager, null)
    }

    private fun showUpdatePlayerDialog(player: Player) {
        FoosballUpdatePlayerDialog.getInstance(player.id).show(supportFragmentManager, null)
    }
}