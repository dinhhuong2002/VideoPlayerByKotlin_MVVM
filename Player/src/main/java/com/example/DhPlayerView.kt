package com.example

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.player.R

class DhPlayerView @JvmOverloads constructor(
    context: Context
) : PlayerView(context) {

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
    }
}
