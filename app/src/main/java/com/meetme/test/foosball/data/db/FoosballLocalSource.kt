package com.meetme.test.foosball.data.db

import android.arch.lifecycle.LiveData
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.Player


interface FoosballLocalSource {

    fun insertPlayer(player: Player)

    fun deletePlayer(player: Player)

    fun updatePlayer(player: Player)

    fun getPlayer(id: Long): LiveData<Player>

    fun getAllPlayers(): LiveData<List<Player>>

    fun insertGame(game: Game)

    fun deleteGame(game: Game)

    fun updateGame(game: Game)

    fun getGame(id: Long): LiveData<GameWithPlayers>

    fun getAllGames(): LiveData<List<GameWithPlayers>>
}