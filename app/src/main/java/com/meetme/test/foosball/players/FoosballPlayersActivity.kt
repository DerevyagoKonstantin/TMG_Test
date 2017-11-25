package com.meetme.test.foosball.players

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseActivity
import com.meetme.test.extensions.visible
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.adapter.FoosballPlayersAdapter
import com.meetme.test.foosball.players.add.FoosballAddPlayerDialog
import com.meetme.test.foosball.players.delete.FoosballDeletePlayerDialog
import com.meetme.test.foosball.players.di.foosballPlayersModule
import com.meetme.test.foosball.players.entity.PlayersSort
import com.meetme.test.foosball.players.update.FoosballUpdatePlayerDialog
import com.meetme.test.foosball.utils.getScrollListener
import kotlinx.android.synthetic.main.activity_foosball_players.*


class FoosballPlayersActivity : BaseActivity() {

    companion object {
        fun open(context: Context) {
            Intent(context, FoosballPlayersActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    private var menu: Menu? = null

    private val adapter = FoosballPlayersAdapter()

    private val viewModelFactory by instance<FoosballPlayersViewModelFactory>()
    private lateinit var viewModel: FoosballPlayersViewModel

    override val viewId = R.layout.activity_foosball_players

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballPlayersModule)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.foosball_players_menu, menu)
        this.menu = menu
        viewModel.sort.value?.let { setSortedItem(it) }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.sort_win_percentage -> sortItemSelected(PlayersSort.WIN_PERCENTAGE)
        R.id.sort_games -> sortItemSelected(PlayersSort.GAMES)
        R.id.sort_wins -> sortItemSelected(PlayersSort.WINS)
        R.id.sort_losses -> sortItemSelected(PlayersSort.LOSSES)
        R.id.sort_draws -> sortItemSelected(PlayersSort.DRAWS)
        else -> super.onOptionsItemSelected(item)
    }

    private fun sortItemSelected(sort: PlayersSort): Boolean {
        viewModel.sort.value = sort
        return true
    }

    private fun setSortedItem(sort: PlayersSort) {
        when (sort) {
            PlayersSort.WIN_PERCENTAGE -> menu?.findItem(R.id.sort_win_percentage)?.isChecked = true
            PlayersSort.GAMES -> menu?.findItem(R.id.sort_games)?.isChecked = true
            PlayersSort.WINS -> menu?.findItem(R.id.sort_wins)?.isChecked = true
            PlayersSort.LOSSES -> menu?.findItem(R.id.sort_losses)?.isChecked = true
            PlayersSort.DRAWS -> menu?.findItem(R.id.sort_draws)?.isChecked = true
        }
    }

    override fun initUI() {
        setSupportActionBar(foosballPlayersToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        foosballPlayersRecyclerView.adapter = adapter
        foosballPlayersRecyclerView.addOnScrollListener(getScrollListener(foosballPlayerAdd))

        foosballPlayerAdd.setOnClickListener {
            viewModel.addPlayer.value = Unit
        }
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballPlayersViewModel::class.java)

        adapter.updatePlayer = viewModel.updatePlayer
        adapter.deletePlayer = viewModel.deletePlayer

        viewModel.sort.observe(this, Observer { sort -> sort?.let { setSortedItem(it) } })

        viewModel.sortedPlayers.observe(this, Observer {
            adapter.players = it ?: listOf()
        })

        viewModel.addPlayer.observe(this, Observer {
            showAddPlayerDialog()
        })

        viewModel.updatePlayer.observe(this, Observer { player ->
            player?.let { showUpdatePlayerDialog(it) }
        })

        viewModel.deletePlayer.observe(this, Observer { player ->
            player?.let { showDeletePlayerDialog(it) }
        })

        viewModel.emptyVisibility.observe(this, Observer {
            foosballPlayersEmptyView.visible = it ?: false
        })

        foosballPlayersSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.search.value = p0.toString()
            }

        })
    }

    private fun showAddPlayerDialog() {
        FoosballAddPlayerDialog().show(supportFragmentManager, null)
    }

    private fun showUpdatePlayerDialog(player: Player) {
        FoosballUpdatePlayerDialog.getInstance(player.id).show(supportFragmentManager, null)
    }

    private fun showDeletePlayerDialog(player: Player) {
        FoosballDeletePlayerDialog.getInstance(player.id).show(supportFragmentManager, null)
    }
}