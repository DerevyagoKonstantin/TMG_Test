package com.meetme.test.foosball.players.add

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.meetme.test.R
import com.meetme.test.base.BaseDialogFragment
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.add.di.foosballAddPlayerModule
import kotlinx.android.synthetic.main.dialog_add_player.view.*


class FoosballAddPlayerDialog : BaseDialogFragment() {

    private val viewModelFactory by instance<FoosballAddPlayerViewModelFactory>()
    private lateinit var viewModel: FoosballAddPlayerViewModel

    override val viewId = R.layout.dialog_add_player

    override val titleTextId = R.string.foosball_add_player
    override val positiveTextId = R.string.save

    override fun provideOverridingModule() = Kodein.Module {
        import(foosballAddPlayerModule)
    }

    override fun bindVM() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FoosballAddPlayerViewModel::class.java)

        viewModel.savePlayerObserver.observe(this, Observer {
            Toast.makeText(context, getString(R.string.foosball_player_save, it), Toast.LENGTH_SHORT).show()
        })

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun positiveClick(): Boolean {
        val firstName = dialogView.addPlayerFirstName.text.toString()
        val lastName = dialogView.addPlayerLastName.text.toString()

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName)) {
            Toast.makeText(context, R.string.foosball_player_empty_name, Toast.LENGTH_SHORT).show()
            return false
        } else {
            viewModel.savePlayer.value = Player(firstName, lastName)
        }

        return true
    }
}