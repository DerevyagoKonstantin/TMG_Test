package com.meetme.test.foosball.data.db

import android.arch.lifecycle.LiveData
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.data.db.entity.PlayerWithGames


interface FoosballLocalSource {

    fun insertPlayer(player: Player)

    fun deletePlayer(id: Long)

    fun updatePlayer(player: Player)

    fun getPlayer(id: Long): LiveData<Player>

    fun getPlayersWithGames(): LiveData<List<PlayerWithGames>>

    fun getAllPlayers(): LiveData<List<Player>>

    fun insertGame(game: Game)

    fun deleteGame(id: Long)

    fun updateGame(game: Game)

    fun getGame(id: Long): LiveData<GameWithPlayers>

    fun getAllGames(): LiveData<List<GameWithPlayers>>
}