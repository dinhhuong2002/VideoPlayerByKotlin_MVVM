package com.example

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.ui.PlayerView
import com.example.player.R
import java.text.SimpleDateFormat
import java.util.Date

class DhPlayerView(context: Context, iPlayer: IPlayer) : FrameLayout(context), Player.Listener,
    SeekBar.OnSeekBarChangeListener {

    private lateinit var totalTime: String
    private lateinit var currentTime: String
    private var iPlayer: IPlayer? = null //khoi tao interface
    private lateinit var playerView: PlayerView
    private lateinit var exoController: FrameLayout
    private var exoPlayer: ExoPlayer? = null
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
        playerView = findViewById(R.id.video_view)
        exoController = findViewById(R.id.controller)



        icPlay?.setOnClickListener {
            playPause()
        }
        icForward?.setOnClickListener {
            seekForward()
        }
        icReplay?.setOnClickListener {
            seekReplay()
        }
        playerView.setOnClickListener {
            if (exoController.visibility == VISIBLE) {
                exoController.visibility = INVISIBLE
            } else if (exoController.visibility == INVISIBLE) {
                exoController.visibility = VISIBLE
            }
        }
        seekBar?.setOnSeekBarChangeListener(this)
    }

    fun playVideoByUrl(context: Context, url: String) {
        try {
            val mediaItem = MediaItem.fromUri(url)
            exoPlayer = ExoPlayer.Builder(context).build()
            playerView = findViewById<PlayerView>(R.id.video_view)

            playerView.player = exoPlayer
            exoPlayer!!.setMediaItem(mediaItem)
            exoPlayer!!.prepare()
            exoPlayer!!.play()

            state = STATE_PLAYING
            exoPlayer!!.addListener(this) // get call back
//        exoPlayer.addAnalyticsListener() //add eventLogger to show log PlayerState in Logcat
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playPause() {
        state = if (state == STATE_PLAYING && exoPlayer!!.isPlaying) {
            exoPlayer?.pause()
            icPlay?.setImageResource(R.drawable.ic_pause)
            STATE_PAUSE
        } else {
            exoPlayer?.play()
            icPlay?.setImageResource(R.drawable.ic_play)
            STATE_PLAYING
        }
    }

    fun seekForward() {
        seekBar?.progress = seekBar?.progress!! + 10

    }

    fun seekReplay() {
        seekBar?.progress = seekBar?.progress!! - 10
    }

    private fun updateTime() {
        totalTime = exoPlayer?.duration.toString()

        if (state == STATE_PLAYING || state == STATE_PAUSE) {

            currentTime = (exoPlayer!!.currentPosition).toString()

            tvProgressTime!!.text = getTime(currentTime.toInt())
            tvTotalTime!!.text = getTime(totalTime.toInt())
            seekBar!!.progress = currentTime.toInt()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(time: Int): String? {
        return SimpleDateFormat("mm:ss").format(Date(time.toLong()))
    }


    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when (playbackState) {
            Player.STATE_IDLE -> {
                Log.d("Xuantk", "Player.STATE_IDLE: ")
                iPlayer?.getPlayerState("Player: STATE_IDLE ")
            }

            Player.STATE_BUFFERING -> {
                Log.d("Xuantk", "Player.STATE_BUFFERING: ")
                iPlayer?.getPlayerState("Player: STATE_BUFFERING ")
            }

            Player.STATE_READY -> {
                Log.d("Xuantk", "Player.STATE_READY: ")
                iPlayer?.getPlayerState("Player: STATE_READY ")
            }

            Player.STATE_ENDED -> {
                Log.d("Xuantk", "Player.STATE_ENDED: ")
                iPlayer?.getPlayerState("Player.STATE_ENDED -> video ended ")
            }
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)

        Log.d("Xuantk", "onPlayerError: " + error.errorCodeName)
        iPlayer?.getPlayerState("Player.ERROR: " + error.errorCodeName)
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        var playOrPause: String

        if (isPlaying) {
            playOrPause = "playing"
        } else {
            playOrPause = "paused"
        }
        Log.d("Xuantk", "onPlayerError: $playOrPause")
        iPlayer?.getPlayerState("Player: IsPlayingChanged: ->  $playOrPause")
    }


    //method of seekbar
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (state == STATE_PLAYING || state == STATE_PAUSE) {
            this.exoPlayer?.seekTo(seekBar!!.progress.toLong())
        }
    }
}
