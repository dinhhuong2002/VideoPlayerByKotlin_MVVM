package com.example

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener

interface IPlayerKotlin {
    // Hàm trả về trạng thái của player
    fun getPlayerState(context: Context, url:String): String

    fun addAnalyticsListener(listener: AnalyticsListener?)
}

// Khai báo các trạng thái của player
object PlayerState {
    const val STATE_IDLE = 1
    const val STATE_BUFFERING = 2
    const val STATE_READY = 3
    const val STATE_ENDED = 4
}

object Player {
//    val exoPlayer: ExoPlayer? = null
}