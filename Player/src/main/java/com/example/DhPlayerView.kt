package com.example

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.ui.PlayerView
import com.example.player.R
import java.util.Formatter
import java.util.Locale

class DhPlayerView(context: Context, attributeSet: AttributeSet?, iPlayer: IPlayer?) :
    FrameLayout(context, attributeSet),
    Player.Listener,
    SeekBar.OnSeekBarChangeListener {

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, null)


    private var iPlayer: IPlayer? = null //khoi tao interface
    private lateinit var playerView: PlayerView
    private lateinit var exoController: FrameLayout

    private var exoPlayer: ExoPlayer? = null
    private var icPlay: ImageView? = null
    private var icForward: ImageView? = null

    private var icBack: ImageView? = null
    private var seekBar: SeekBar? = null
    private var tvProgressTime: TextView? = null
    private var tvTotalTime: TextView? = null

    private var eventLogger: EventLogger? = null
    private lateinit var thread: Thread


    private var STATE_IDLE = 1
    private var STATE_PLAYING = 2
    private var STATE_PAUSE = 3

    private var state = STATE_IDLE

    init {
        init()
        this.iPlayer = iPlayer
        bindView()
    }

    private fun init() {
        // Use LayoutInflater to inflate the layout into the custom view
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.dhplayer_view, this, true)
        exoPlayer = ExoPlayer.Builder(context).build()
        playerView = findViewById(R.id.video_view)
        playerView.player = exoPlayer
    }

    private fun bindView() {
        icPlay = findViewById(R.id.ic_play)
        icForward = findViewById(R.id.ic_foward)
        icBack = findViewById(R.id.ic_back)
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
        icBack?.setOnClickListener {
            seekReWind()
        }
        playerView.setOnClickListener {
            if (exoController.visibility == VISIBLE) {
                exoController.visibility = INVISIBLE
            } else if (exoController.visibility == INVISIBLE) {
                exoController.visibility = VISIBLE
            }
        }

        seekBar!!.setOnSeekBarChangeListener(this)

    }

    fun playVideoByUrl(url: String) {
        Log.d("ContentValues", "play video by url")
        try {
            icPlay?.setImageResource(R.drawable.ic_play)
            val mediaItem = MediaItem.fromUri(url)

            exoPlayer!!.setMediaItem(mediaItem)
            exoPlayer!!.prepare()
            exoPlayer!!.play()
//            exoPlayer!!.playWhenReady = true

            state = STATE_PLAYING
            exoPlayer!!.addListener(this) // get call back
            exoPlayer!!.addAnalyticsListener(EventLogger()) //add eventLogger to show log PlayerState in Logcat
            Log.d("URL", "URL = $url")

            startLooping()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ContentValues", "Exception when playing video: $e")
        }
    }
    fun pauseVideo(){
        if(exoPlayer!!.isPlaying){
            exoPlayer!!.pause()
            state = STATE_PAUSE
            Log.d("ContentValues", "state when switch fragment: state = $state")
        }
    }

    private fun playPause() {
        Log.d("ContentValues", "click on play or pause button state = $state")
        if (state == STATE_PLAYING && exoPlayer!!.isPlaying) {
            exoPlayer?.pause()
            icPlay?.setImageResource(R.drawable.ic_pause)
            state = STATE_PAUSE
        } else if (state == STATE_PAUSE) {
            exoPlayer?.play()
            icPlay?.setImageResource(R.drawable.ic_play)
            state = STATE_PLAYING
        }
    }
    fun isPlaying():Boolean{
        return exoPlayer!!.isPlaying
    }

    private fun seekForward() {
        Log.d("ContentValues", "click seek forward button")
        seekBar?.progress = seekBar?.progress!! + 15
        exoPlayer!!.seekForward()
    }

    private fun seekReWind() {
        seekBar?.progress = seekBar?.progress!! - 15
        exoPlayer!!.seekTo((exoPlayer!!.currentPosition - 15000))
    }

    //get Exoplayer Duration
    private fun getVideoDurationMileSeconds(exoPlayer: ExoPlayer): Int {
        return exoPlayer.duration.toInt()
    }

    private fun getVideoProgressMileSeconds(exoPlayer: ExoPlayer): Int {
        return exoPlayer.currentPosition.toInt()
    }

    //format Time to String
    private fun stringForTime(timeMs: Int): String {

        val mFormatBuilder = StringBuilder()
        val mFormatter = Formatter(mFormatBuilder, Locale.getDefault())
        val totalSeconds = timeMs / 1000
        //  videoDurationInSeconds = totalSeconds % 60;
        //  videoDurationInSeconds = totalSeconds % 60;
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600

        mFormatBuilder.setLength(0)
        return if (hours > 0) {
            mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
        } else {
            mFormatter.format("%02d:%02d", minutes, seconds).toString()
        }
    }

    private fun updateTime() {
        seekBar!!.max = getVideoDurationMileSeconds(exoPlayer!!) / 1000

        tvTotalTime!!.text = stringForTime(getVideoDurationMileSeconds(exoPlayer!!))
        tvProgressTime!!.text = stringForTime(getVideoProgressMileSeconds(exoPlayer!!))

        seekBar!!.progress = getVideoProgressMileSeconds(exoPlayer!!) / 1000
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_IDLE -> {
                Log.d("Xuantk", "Player.STATE_IDLE: ")
                iPlayer?.getPlayerState("Player: STATE_IDLE ")
                state = STATE_IDLE
            }

            Player.STATE_BUFFERING -> {
                iPlayer?.getPlayerState("Player: STATE_BUFFERING ")
            }

            Player.STATE_READY -> {
                iPlayer?.getPlayerState("Player: STATE_READY ")
                Thread {
                    startLooping()
                    Thread.sleep(1000)
                }.start()
                state = STATE_PLAYING
            }

            Player.STATE_ENDED -> {
                iPlayer?.getPlayerState("Player.STATE_ENDED -> video ended ")
                exoPlayer!!.repeatMode = ExoPlayer.REPEAT_MODE_ONE;

            }
        }
    }

    private fun startLooping() {
        thread = Thread {
            while (true) {
                try {
                    Thread.sleep(200)
                } catch (e: Exception) {
                    return@Thread
                }
                // Post a Runnable to the main looper's message queue
                android.os.Handler(Looper.getMainLooper()).post {
                    updateTime()
                }
            }
        }
        thread.start()
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)

        Log.d("Xuantk", "onPlayerError: " + error.errorCodeName)
        iPlayer?.getPlayerState("Player.ERROR: " + error.errorCodeName)
        when (error.errorCode) {
            ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> {
                state = STATE_PAUSE
            }
        }
    }

    override fun onEvents(player: Player, events: Player.Events) {
        super.onEvents(player, events)
//        iPlayer?.getPlayerState("Player:  $events")
        Log.d("dinhhuong", "Player State: onEvent $eventLogger")
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
        super.onTimelineChanged(timeline, reason)
        iPlayer?.getPlayerState("Player: TimelineChanged ")
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        var playOrPause: String
        Log.d("ContentValues", "onIsPlayingChanged: isPlaying = $isPlaying")
        if (isPlaying) {
            Log.d("ContentValues", "onIsPlayingChanged: change state = $STATE_PLAYING")
            playOrPause = "playing"
            state = STATE_PLAYING
        } else {
            Log.d("ContentValues", "onIsPlayingChanged: change state = $STATE_PAUSE")
            playOrPause = "paused"
            state = STATE_PAUSE
        }
        iPlayer?.getPlayerState("Player: IsPlayingChanged: ->  $playOrPause")

    }


    //method of seekbar
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (state == STATE_PLAYING || state == STATE_PAUSE) {
            Log.d("onStopTrackingTouch", " seekBar.Progress: " + seekBar!!.progress.toString())
            exoPlayer?.seekTo(seekBar!!.progress.toLong() * 1000L)
        }
    }

}
