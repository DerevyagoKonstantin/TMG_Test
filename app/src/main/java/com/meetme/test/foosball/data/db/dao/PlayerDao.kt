package com.meetme.test.foosball.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.meetme.test.foosball.data.db.entity.*

/**
 * Created by Konstantin on 17.11.2017.
 */

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Delete
    fun delete(player: Player)

    @Update
    fun update(player: Player)

    @Query("SELECT * FROM $PLAYER WHERE $PLAYER_ID = :arg0")
    fun getPlayer(id: Long): LiveData<Player>

    @Query("SELECT * FROM $PLAYER")
    fun getAll(): LiveData<List<Player>>

    @Query("SELECT player_id $PLAYER_ID, $FIRST_NAME, $LAST_NAME, SUM(win) $WINS, SUM(loss) $LOSES, SUM(draw) $DRAWS " +
            "FROM " +
            "(SELECT $FIRST_PLAYER player_id, player.$FIRST_NAME, player.$LAST_NAME, " +
            "CASE WHEN $FIRST_SCORE > $SECOND_SCORE THEN 1 ELSE 0 END win, " +
            "CASE WHEN $FIRST_SCORE < $SECOND_SCORE THEN 1 ELSE 0 END loss, " +
            "CASE WHEN $FIRST_SCORE = $SECOND_SCORE THEN 1 ELSE 0 END draw " +
            "FROM $GAME " +
            "LEFT JOIN $PLAYER player ON player_id = player.$PLAYER_ID " +
            "UNION ALL " +
            "SELECT $SECOND_PLAYER player_id, player.$FIRST_NAME, player.$LAST_NAME, " +
            "CASE WHEN $SECOND_SCORE > $FIRST_SCORE THEN 1 ELSE 0 END win, " +
            "CASE WHEN $SECOND_SCORE < $FIRST_SCORE THEN 1 ELSE 0 END loss, " +
            "CASE WHEN $FIRST_SCORE = $SECOND_SCORE THEN 1 ELSE 0 END draw " +
            "FROM $GAME " +
            "LEFT JOIN $PLAYER player ON player_id = player.$PLAYER_ID " +
            ") " +
            "GROUP BY player_id")
    fun getPlayersWithGames(): LiveData<List<PlayerWithGames>>
}