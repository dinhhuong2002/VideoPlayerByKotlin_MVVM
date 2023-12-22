package com.example

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.analytics.AnalyticsListener

interface IPlayer {
    // Hàm trả về trạng thái của player
    fun getPlayerState(context: Context, url:String): String

    fun addAnalyticsListener(listener: AnalyticsListener?)
    fun testShowLog(eventLog: String)
    
}

// Khai báo các trạng thái của player
object PlayerState {
    const val STATE_IDLE = 1
    const val STATE_BUFFERING = 2
    const val STATE_READY = 3
    const val STATE_ENDED = 4
}
