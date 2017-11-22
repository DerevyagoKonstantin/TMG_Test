package com.meetme.test.foosball.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.meetme.test.foosball.data.db.entity.FIRST_NAME
import com.meetme.test.foosball.data.db.entity.FIRST_PLAYER
import com.meetme.test.foosball.data.db.entity.FIRST_PLAYER_FIRST_NAME
import com.meetme.test.foosball.data.db.entity.FIRST_PLAYER_ID
import com.meetme.test.foosball.data.db.entity.FIRST_PLAYER_LAST_NAME
import com.meetme.test.foosball.data.db.entity.FIRST_SCORE
import com.meetme.test.foosball.data.db.entity.GAME
import com.meetme.test.foosball.data.db.entity.GAME_ID
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.data.db.entity.LAST_NAME
import com.meetme.test.foosball.data.db.entity.PLAYER
import com.meetme.test.foosball.data.db.entity.PLAYER_ID
import com.meetme.test.foosball.data.db.entity.SECOND_PLAYER
import com.meetme.test.foosball.data.db.entity.SECOND_PLAYER_FIRST_NAME
import com.meetme.test.foosball.data.db.entity.SECOND_PLAYER_ID
import com.meetme.test.foosball.data.db.entity.SECOND_PLAYER_LAST_NAME
import com.meetme.test.foosball.data.db.entity.SECOND_SCORE

/**
 * Created by Konstantin on 17.11.2017.
 */

@Dao
interface GameDao {

    @Insert
    fun insert(game: Game)

    @Delete
    fun delete(game: Game)

    @Update
    fun update(game: Game)

    @Query("SELECT game.$GAME_ID, " +
            "first.$PLAYER_ID $FIRST_PLAYER_ID, first.$FIRST_NAME $FIRST_PLAYER_FIRST_NAME, first.$LAST_NAME $FIRST_PLAYER_LAST_NAME, " +
            "second.$PLAYER_ID $SECOND_PLAYER_ID, second.$FIRST_NAME $SECOND_PLAYER_FIRST_NAME, second.$LAST_NAME $SECOND_PLAYER_LAST_NAME, " +
            "game.$FIRST_SCORE, game.$SECOND_SCORE " +
            "FROM $GAME game " +
            "INNER JOIN $PLAYER first ON game.$FIRST_PLAYER = first.$PLAYER_ID " +
            "INNER JOIN $PLAYER second ON game.$SECOND_PLAYER = second.$PLAYER_ID " +
            "WHERE game.$GAME_ID = :arg0")
    fun getGame(id: Long): LiveData<GameWithPlayers>

    @Query("SELECT game.$GAME_ID, " +
            "first.$PLAYER_ID $FIRST_PLAYER_ID, first.$FIRST_NAME $FIRST_PLAYER_FIRST_NAME, first.$LAST_NAME $FIRST_PLAYER_LAST_NAME, " +
            "second.$PLAYER_ID $SECOND_PLAYER_ID, second.$FIRST_NAME $SECOND_PLAYER_FIRST_NAME, second.$LAST_NAME $SECOND_PLAYER_LAST_NAME, " +
            "game.$FIRST_SCORE, game.$SECOND_SCORE " +
            "FROM $GAME game " +
            "INNER JOIN $PLAYER first ON game.$FIRST_PLAYER = first.$PLAYER_ID " +
            "INNER JOIN $PLAYER second ON game.$SECOND_PLAYER = second.$PLAYER_ID")
    fun getAll(): LiveData<List<GameWithPlayers>>
}