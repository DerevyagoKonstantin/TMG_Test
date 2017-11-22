package com.meetme.test.foosball.utils

import android.R
import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.meetme.test.foosball.data.db.entity.Player


fun getScoreAdapter(context: Context): SpinnerAdapter {
    val scores = mutableListOf<Int>()
    (0..10).forEach {
        scores.add(it)
    }

    val adapter = ArrayAdapter<Int>(context, R.layout.simple_spinner_item, scores)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return adapter
}

fun getPlayersAdapter(context: Context, players: List<Player>): SpinnerAdapter {
    val adapter = ArrayAdapter<Player>(context, R.layout.simple_spinner_item, players)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return adapter
}

fun getScrollListener(floatingActionButton: FloatingActionButton) = object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 || dy < 0 && floatingActionButton.isShown) {
            floatingActionButton.hide()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            floatingActionButton.show()
        }
        super.onScrollStateChanged(recyclerView, newState)
    }
}