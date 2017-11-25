package com.meetme.test.foosball.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

const val PLAYER = "player"
const val PLAYER_ID = "id"
const val FIRST_NAME = "first_name"
const val LAST_NAME = "last_name"

@Entity(tableName = PLAYER)
data class Player(
        @ColumnInfo(name = FIRST_NAME)
        var firstName: String = "",
        @ColumnInfo(name = LAST_NAME)
        var lastName: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PLAYER_ID)
    var id: Long = 0

    constructor(id: Long, firstName: String, lastName: String) : this(firstName, lastName) {
        this.id = id
    }

    override fun toString() = (firstName + " " + lastName).trim()
}


