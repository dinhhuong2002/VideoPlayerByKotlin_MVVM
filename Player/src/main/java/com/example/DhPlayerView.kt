package com.example

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.ui.PlayerView
import com.example.player.R

class DhPlayerView(context: Context) : PlayerView(context) {

    init {
        init()
    }

    private fun init() {
        // Use LayoutInflater to inflate the layout into the custom view
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.dhplayer_view, this, true)
    }

    fun playVideoByUrl(context: Context, url: String) {
        val exoPlayer = ExoPlayer.Builder(context).build()
        val playerView = findViewById<PlayerView>(R.id.video_view)

        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(url)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
        exoPlayer.addAnalyticsListener(EventLogger())
    }
}
