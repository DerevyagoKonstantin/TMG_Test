package com.meetme.test.foosball.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.meetme.test.foosball.data.db.entity.*

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

    @Query("SELECT player_id $PLAYER_ID, $FIRST_NAME, $LAST_NAME, SUM(win) $WINS, SUM(loss) $LOSSES, SUM(draw) $DRAWS " +
            "FROM " +
            "(SELECT player.$PLAYER_ID player_id, player.$FIRST_NAME, player.$LAST_NAME, " +
            "CASE WHEN $FIRST_SCORE > $SECOND_SCORE THEN 1 ELSE 0 END win, " +
            "CASE WHEN $FIRST_SCORE < $SECOND_SCORE THEN 1 ELSE 0 END loss, " +
            "CASE WHEN $FIRST_SCORE = $SECOND_SCORE THEN 1 ELSE 0 END draw " +
            "FROM $PLAYER player " +
            "LEFT JOIN $GAME game ON game.$FIRST_PLAYER = player.$PLAYER_ID " +
            "UNION ALL " +
            "SELECT player.$PLAYER_ID player_id, player.$FIRST_NAME, player.$LAST_NAME, " +
            "CASE WHEN $SECOND_SCORE > $FIRST_SCORE THEN 1 ELSE 0 END win, " +
            "CASE WHEN $SECOND_SCORE < $FIRST_SCORE THEN 1 ELSE 0 END loss, " +
            "CASE WHEN $FIRST_SCORE = $SECOND_SCORE THEN 1 ELSE 0 END draw " +
            "FROM $PLAYER player " +
            "LEFT JOIN $GAME game ON game.$SECOND_PLAYER = player.$PLAYER_ID " +
            ") " +
            "GROUP BY player_id")
    fun getPlayersWithGames(): LiveData<List<PlayerWithGames>>
}