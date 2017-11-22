package com.meetme.test.foosball.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.content.Context
import com.meetme.test.R

const val FIRST_PLAYER_ID = FIRST_PLAYER + "_" + PLAYER_ID
const val FIRST_PLAYER_FIRST_NAME = FIRST_PLAYER + "_" + FIRST_NAME
const val FIRST_PLAYER_LAST_NAME = FIRST_PLAYER + "_" + LAST_NAME
const val SECOND_PLAYER_ID = SECOND_PLAYER + "_" + PLAYER_ID
const val SECOND_PLAYER_FIRST_NAME = SECOND_PLAYER + "_" + FIRST_NAME
const val SECOND_PLAYER_LAST_NAME = SECOND_PLAYER + "_" + LAST_NAME

data class GameWithPlayers(
        @ColumnInfo(name = GAME_ID)
        var id: Long = 0,
        @ColumnInfo(name = FIRST_PLAYER_ID)
        var firstPlayerId: Long = 0,
        @ColumnInfo(name = FIRST_PLAYER_FIRST_NAME)
        var firstPlayerFirstName: String = "",
        @ColumnInfo(name = FIRST_PLAYER_LAST_NAME)
        var firstPlayerLastName: String = "",
        @ColumnInfo(name = SECOND_PLAYER_ID)
        var secondPlayerId: Long = 0,
        @ColumnInfo(name = SECOND_PLAYER_FIRST_NAME)
        var secondPlayerFirstName: String = "",
        @ColumnInfo(name = SECOND_PLAYER_LAST_NAME)
        var secondPlayerLastName: String = "",
        @ColumnInfo(name = FIRST_SCORE)
        var firstScore: Int = 0,
        @ColumnInfo(name = SECOND_SCORE)
        var secondScore: Int = 0
) {
    constructor(firstPlayer: Player, secondPlayer: Player, firstScore: Int, secondScore: Int) : this(
            firstPlayerId = firstPlayer.id,
            firstPlayerFirstName = firstPlayer.firstName,
            firstPlayerLastName = firstPlayer.lastName,
            secondPlayerId = secondPlayer.id,
            secondPlayerFirstName = secondPlayer.firstName,
            secondPlayerLastName = secondPlayer.lastName,
            firstScore = firstScore,
            secondScore = secondScore
    )

    constructor(id: Long, firstPlayer: Player, secondPlayer: Player, firstScore: Int, secondScore: Int) : this(
            firstPlayer, secondPlayer, firstScore, secondScore
    ) {
        this.id = id
    }

    fun getFirstPlayer(): Player = Player(firstPlayerId, firstPlayerFirstName, firstPlayerLastName)

    fun getSecondPlayer(): Player = Player(secondPlayerId, secondPlayerFirstName, secondPlayerLastName)

    fun getWinner(context: Context): String = when {
        firstScore > secondScore -> context.getString(R.string.foosball_player_won, getFirstPlayer().getFullName())
        secondScore > firstScore -> context.getString(R.string.foosball_player_won, getSecondPlayer().getFullName())
        else -> context.getString(R.string.foosball_draw)
    }

    fun getGame(): Game = Game(id, firstPlayerId, secondPlayerId, firstScore, secondScore)

    override fun toString() = "${getFirstPlayer()} $firstScore : $secondScore ${getSecondPlayer()}"
}