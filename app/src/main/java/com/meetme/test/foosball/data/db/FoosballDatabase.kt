package com.meetme.test.foosball.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.meetme.test.foosball.data.db.dao.GameDao
import com.meetme.test.foosball.data.db.dao.PlayerDao
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.data.db.entity.PlayerWithGames
import kotlinx.coroutines.experimental.async

const val FOOSBALL_DB_NAME = "foosball-db-name"

@Database(entities = arrayOf(Player::class, Game::class), version = 4)
abstract class FoosballDatabase : RoomDatabase(), FoosballLocalSource {
    abstract fun playerDao(): PlayerDao
    abstract fun gameDao(): GameDao

    override fun insertPlayer(player: Player) {
        async {
            playerDao().insert(player)
        }
    }

    override fun deletePlayer(id: Long) {
        async {
            playerDao().delete(id)
        }
    }

    override fun updatePlayer(player: Player) {
        async {
            playerDao().update(player)
        }
    }

    override fun getPlayer(id: Long): LiveData<Player> = playerDao().getPlayer(id)

    override fun getPlayersWithGames(): LiveData<List<PlayerWithGames>> = playerDao().getPlayersWithGames()

    override fun getAllPlayers(): LiveData<List<Player>> = playerDao().getAll()

    override fun insertGame(game: Game) {
        async {
            gameDao().insert(game)
        }
    }

    override fun deleteGame(id: Long) {
        async {
            gameDao().delete(id)
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