package com.meetme.test.foosball.players.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meetme.test.R
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.data.db.entity.PlayerWithGames
import kotlinx.android.synthetic.main.activity_foosball_players.view.*
import kotlinx.android.synthetic.main.foosball_player_item.view.*


class FoosballPlayersAdapter : RecyclerView.Adapter<FoosballPlayersAdapter.FoosballPlayerViewHolder>() {

    var players: List<PlayerWithGames> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var updatePlayer = MutableLiveData<Player>()
    var deletePlayer = MutableLiveData<Player>()

    override fun getItemCount() = players.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FoosballPlayerViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.foosball_player_item, parent, false)
        return FoosballPlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoosballPlayerViewHolder?, position: Int) {
        holder?.bind(players[position], updatePlayer, deletePlayer)
    }

    class FoosballPlayerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: PlayerWithGames, editClick: MutableLiveData<Player>, deleteClick: MutableLiveData<Player>) {
            itemView.foosballPlayerName.text = player.getPlayer().toString()
            itemView.foosballPlayerTitle.text = player.getGamesStat()

            itemView.foosballPlayerEdit.setOnClickListener {
                editClick.value = player.getPlayer()
            }

            itemView.foosballPlayerDelete.setOnClickListener {
                deleteClick.value = player.getPlayer()
            }
        }
    }
}