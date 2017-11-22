package com.meetme.test.foosball.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meetme.test.R
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import kotlinx.android.synthetic.main.foosball_game_item.view.*


class FoosballGamesAdapter : RecyclerView.Adapter<FoosballGamesAdapter.FoosballGameViewHolder>() {

    var games: List<GameWithPlayers> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var updateGame = MutableLiveData<Game>()
    var deleteGame = MutableLiveData<Game>()

    override fun getItemCount() = games.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FoosballGameViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.foosball_game_item, parent, false)
        return FoosballGameViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoosballGameViewHolder?, position: Int) {
        holder?.bind(games[position], updateGame, deleteGame)
    }

    class FoosballGameViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: GameWithPlayers, editClick: MutableLiveData<Game>, deleteCLick: MutableLiveData<Game>) {
            itemView.foosballGameFirstPlayer.text = game.getFirstPlayer().toString()
            itemView.foosballGameSecondPlayer.text = game.getSecondPlayer().toString()
            itemView.foosballGameFirstScore.text = game.firstScore.toString()
            itemView.foosballGameSecondScore.text = game.secondScore.toString()

            itemView.foosballGameTitle.text = game.getWinner(itemView.context)

            itemView.foosballGameEdit.setOnClickListener {
                editClick.value = Game(game.id, game.firstPlayerId, game.secondPlayerId, game.firstScore, game.secondScore)
            }

            itemView.foosballGameDelete.setOnClickListener {
                deleteCLick.value = Game(game.id, game.firstPlayerId, game.secondPlayerId, game.firstScore, game.secondScore)
            }
        }
    }
}