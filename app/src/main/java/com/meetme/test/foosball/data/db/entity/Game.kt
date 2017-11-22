package com.meetme.test.foosball.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Konstantin on 17.11.2017.
 */

const val GAME = "game"
const val GAME_ID = "id"
const val FIRST_PLAYER = "first_player"
const val SECOND_PLAYER = "second_player"
const val FIRST_SCORE = "first_score"
const val SECOND_SCORE = "second_score"

@Entity(tableName = GAME,
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = Player::class,
                        parentColumns = arrayOf(PLAYER_ID),
                        childColumns = arrayOf(FIRST_PLAYER),
                        onDelete = ForeignKey.CASCADE
                ),
                ForeignKey(
                        entity = Player::class,
                        parentColumns = arrayOf(PLAYER_ID),
                        childColumns = arrayOf(SECOND_PLAYER),
                        onDelete = ForeignKey.CASCADE
                )
        )
)
data class Game(
        @ColumnInfo(name = FIRST_PLAYER, index = true)
        var firstPlayer: Long = 0,
        @ColumnInfo(name = SECOND_PLAYER, index = true)
        var secondPlayer: Long = 0,
        @ColumnInfo(name = FIRST_SCORE)
        var firstScore: Int = 0,
        @ColumnInfo(name = SECOND_SCORE)
        var secondScore: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = GAME_ID)
    var id: Long = 0

    constructor(
            id: Long,
            firstPlayer: Long,
            secondPlayer: Long,
            firstScore: Int,
            secondScore: Int
    ) : this(firstPlayer, secondPlayer, firstScore, secondScore) {
        this.id = id
    }

    override fun toString() = "$firstScore : $secondScore"
}