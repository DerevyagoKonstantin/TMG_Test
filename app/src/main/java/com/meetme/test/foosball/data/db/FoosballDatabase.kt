package com.meetme.test.foosball.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.meetme.test.foosball.data.db.dao.GameDao
import com.meetme.test.foosball.data.db.dao.PlayerDao
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.Player
import kotlinx.coroutines.experimental.async

/**
 * Created by Konstantin on 17.11.2017.
 */

const val FOOSBALL_DB_NAME = "foosball-db-name"

@Database(entities = arrayOf(Player::class, Game::class), version = 2)
abstract class FoosballDatabase : RoomDatabase(), FoosballLocalSource {
    abstract fun playerDao(): PlayerDao
    abstract fun gameDao(): GameDao

    override fun insertPlayer(player: Player) {
        async {
            playerDao().insert(player)
        }
    }

    override fun deletePlayer(player: Player) {
        async {
            playerDao().delete(player)
        }
    }

    override fun updatePlayer(player: Player) {
        async {
            playerDao().update(player)
        }
    }

    override fun getPlayer(id: Long): LiveData<Player> = playerDao().getPlayer(id)

    override fun getAllPlayers(): LiveData<List<Player>> = playerDao().getAll()

    override fun insertGame(game: Game) {
        async {
            gameDao().insert(game)
        }
    }

    override fun deleteGame(game: Game) {
        async {
            gameDao().delete(game)
        }
    }

    override fun updateGame(game: Game) {
        async {
            gameDao().update(game)
        }
    }

    override fun getGame(id: Long): LiveData<GameWithPlayers> = gameDao().getGame(id)

    override fun getAllGames(): LiveData<List<GameWithPlayers>> = gameDao().getAll()
}