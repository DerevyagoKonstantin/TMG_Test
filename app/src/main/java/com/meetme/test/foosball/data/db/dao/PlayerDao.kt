package com.meetme.test.foosball.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.meetme.test.foosball.data.db.entity.PLAYER
import com.meetme.test.foosball.data.db.entity.PLAYER_ID
import com.meetme.test.foosball.data.db.entity.Player

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
}