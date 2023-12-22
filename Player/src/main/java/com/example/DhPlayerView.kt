package com.example

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.ui.PlayerView
import com.example.player.R
import java.text.SimpleDateFormat
import java.util.Date

class DhPlayerView(context: Context, iPlayer: IPlayer) : FrameLayout(context), Player.Listener {

    private lateinit var totalTime: String
    private var iPlayer: IPlayer? = null //khoi tao interface

    private var icPlay: ImageView? = null
    private var icForward: ImageView? = null
    private var icPause: ImageView? = null


    private var icReplay: ImageView? = null
    private var seekBar: SeekBar? = null
    private var tvProgressTime: TextView? = null
    private var tvTotalTime: TextView? = null

    private var eventLogger: EventLogger? = null


    private var STATE_IDLE = 1
    private var STATE_PLAYING = 2
    private var STATE_PAUSE = 3

    var state = STATE_IDLE

    init {
        init()
        this.iPlayer = iPlayer
        bindView()
    }

    private fun init() {
        // Use LayoutInflater to inflate the layout into the custom view
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.dhplayer_view, this, true)
    }

    private fun bindView() {
        icPlay = findViewById(R.id.ic_play)
        icForward = findViewById(R.id.ic_foward)
        icReplay = findViewById(R.id.ic_replay)
        seekBar = findViewById(R.id.seekBar)
        tvProgressTime = findViewById(R.id.progress_time)
        tvTotalTime = findViewById(R.id.total_time)
    }

    fun playVideoByUrl(context: Context, url: String) {
        val mediaItem = MediaItem.fromUri(url)
        var exoPlayer = ExoPlayer.Builder(context).build()
        var playerView = findViewById<PlayerView>(R.id.video_view)

        playerView.player = exoPlayer
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        exoPlayer.addListener(this)
//        exoPlayer.addAnalyticsListener()
    }

    override fun onEvents(player: Player, events: Player.Events) {
        super.onEvents(player, events)
        Log.d("Xuantk", "event: ${events}")
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when (playbackState) {
            Player.STATE_IDLE -> {
                Log.d("Xuantk", "Player.STATE_IDLE: ")
                iPlayer?.testShowLog("Player.STATE_IDLE: ")
            }
            Player.STATE_BUFFERING -> {
                Log.d("Xuantk", "Player.STATE_BUFFERING: ")
                iPlayer?.testShowLog("Player.STATE_BUFFERING: ")
            }
            Player.STATE_READY -> {
                Log.d("Xuantk", "Player.STATE_READY: ")
                iPlayer?.testShowLog("Player.STATE_READY: ")
            }
            Player.EVENT_IS_PLAYING_CHANGED -> {
                iPlayer?.testShowLog("Player.EVENT_IS_PLAYING_CHANGED: ")
            }
            Player.STATE_ENDED -> {
                Log.d("Xuantk", "Player.STATE_ENDED: ")
                iPlayer?.testShowLog("Player.STATE_ENDED: ")
            }
            Player.EVENT_PLAYER_ERROR -> {
                iPlayer?.testShowLog("Player.EVENT_PLAYER_ERROR: ")
            }
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)

        Log.d("Xuantk", "onPlayerError: ${error.toString()} ")
        iPlayer?.testShowLog("Player.ERROR: ")
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)

        iPlayer?.testShowLog("Player.IsPlayingChanged: ")
    }

    override fun onPlayerErrorChanged(error: PlaybackException?) {
        super.onPlayerErrorChanged(error)

        iPlayer?.testShowLog("Player.PlayerErrorChanged: ")
    }

    override fun onVolumeChanged(volume: Float) {
        super.onVolumeChanged(volume)

        iPlayer?.testShowLog("Player.VolumeChanged: ")
    }

    override fun onSeekBackIncrementChanged(seekBackIncrementMs: Long) {
        super.onSeekBackIncrementChanged(seekBackIncrementMs)

        iPlayer?.testShowLog("Player.SeekBackIncrementChanged: ")
    }
}
