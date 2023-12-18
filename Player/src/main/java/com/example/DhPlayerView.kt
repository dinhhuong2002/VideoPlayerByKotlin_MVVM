package com.example

import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.player.R

class DhPlayerView(context: Context) : PlayerView(context) {

    var exoPlayer: ExoPlayer? = null
//    var playerView: PlayerView? = null

    init {
        init()
    }


    //initMethod
    fun init() {
        View.inflate(context, R.layout.dhplayer_view, this)
        bindItems()
    }

    //bind items
    fun bindItems() {

//        val playerView = findViewById<VideoView>(R.id.video_view)
        val ic_play = findViewById<ImageView>(R.id.ic_play)
        val ic_forward = findViewById<ImageView>(R.id.ic_foward)
        val ic_replay = findViewById<ImageView>(R.id.ic_replay)
        var tv_total_time = findViewById<TextView>(R.id.total_time)
        var tv_progress_time = findViewById<TextView>(R.id.progress_time)
        var seekbar = findViewById<SeekBar>(R.id.seekBar)

    }

    companion object {

        //playVideo by url
        fun playvideo(exoPlayer: ExoPlayer, playerView: DhPlayerView, url: String) {

            playerView.player = exoPlayer
            val mediaItem: MediaItem = MediaItem.fromUri(url)//setMediaItem
            exoPlayer?.setMediaItem(mediaItem)

            exoPlayer?.prepare()

            exoPlayer?.play()
            Log.d(TAG, exoPlayer.isPlaying().toString())
        }

        //show config: state of player
        fun showConfig(player: Player): String {
            return "show Config"
        }

    }


}