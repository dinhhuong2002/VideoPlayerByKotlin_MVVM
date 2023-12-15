package com.example

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.player.R

class DhPlayerView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    //initMethod
    private fun init() {
        View.inflate(context, R.layout.dhplayer_view, this)
    }

    //bind items
    private fun bindItems() {

        val ic_play = findViewById<ImageView>(R.id.ic_play)
        val ic_forward = findViewById<ImageView>(R.id.ic_foward)
        val ic_replay = findViewById<ImageView>(R.id.ic_replay)
        var tv_total_time = findViewById<TextView>(R.id.total_time)
        var tv_progress_time = findViewById<TextView>(R.id.progress_time)
        var seekbar = findViewById<SeekBar>(R.id.seekBar)

    }

    public fun setPlayer() {

    }

}