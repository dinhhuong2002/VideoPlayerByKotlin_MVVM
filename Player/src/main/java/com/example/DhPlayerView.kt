package com.example

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.player.R
import com.example.player.databinding.DhplayerViewBinding

class DhPlayerView(context: Context) : PlayerView(context) {

    init {
        init()
        createExoPlayer(context)
    }

    companion object {

        private val binding: DhplayerViewBinding
            get() {
                TODO()
            }


        var exoPlayer: ExoPlayer? = null
        var mediaItem: MediaItem? = null

        fun playVideoByURL(context: Context, url: String) {

            exoPlayer = createExoPlayer(context)
            var playerView = binding.videoView

            playerView!!.player = exoPlayer
            mediaItem = MediaItem.fromUri(url) //setMediaItem
            exoPlayer!!.setMediaItem(mediaItem!!)
            exoPlayer!!.prepare()
            exoPlayer!!.play()

            Log.d(TAG, exoPlayer!!.isPlaying().toString())
        }

        //new ExoPlayer
        fun createExoPlayer(context: Context): ExoPlayer {
            return ExoPlayer.Builder(context).build()
        }
    }

    fun init() {
        val inflate = View.inflate(context, R.layout.dhplayer_view, this)
    }
}

//bind items
//    fun bindItems() {
//
////        var video_view = findViewById<PlayerView>(R.id.video_view)
////        val ic_play = findViewById<ImageView>(R.id.ic_play)
////        val ic_forward = findViewById<ImageView>(R.id.ic_foward)
////        val ic_replay = findViewById<ImageView>(R.id.ic_replay)
////        var tv_total_time = findViewById<TextView>(R.id.total_time)
////        var tv_progress_time = findViewById<TextView>(R.id.progress_time)
////        var seekbar = findViewById<SeekBar>(R.id.seekBar)
////
////        video_view= playerView
//
//    }
