package com.example

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.SeekBar
import java.text.SimpleDateFormat
import java.util.Date

class DhPlayer: MediaPlayer() {

    var state = 0
    var STATE_IDLE = 1
    var STATE_PLAYING = 2
    var STATE_PAUSE = 3
    var STATE_STOP = 4
    var playerView: DhPlayerView = TODO()


    //playvideo
    fun play() {

    }

    override fun prepare() {
        super.prepare()
    }

    override fun start() {
        super.start()
    }

    override fun stop() {
        super.stop()
    }

    override fun isPlaying(): Boolean {
        return super.isPlaying()
    }

    override fun seekTo(msec: Long, mode: Int) {
        super.seekTo(msec, mode)
    }

    override fun seekTo(msec: Int) {
        super.seekTo(msec)
    }

    override fun getCurrentPosition(): Int {
        return super.getCurrentPosition()
    }

    override fun getDuration(): Int {
        return super.getDuration()
    }

    override fun reset() {
        super.reset()
    }

    override fun isLooping(): Boolean {
        return super.isLooping()
    }

    override fun addTimedTextSource(path: String?, mimeType: String?) {
        super.addTimedTextSource(path, mimeType)
    }

    override fun addTimedTextSource(context: Context?, uri: Uri?, mimeType: String?) {
        super.addTimedTextSource(context, uri, mimeType)
    }

    override fun setOnSeekCompleteListener(listener: OnSeekCompleteListener?) {
        super.setOnSeekCompleteListener(listener)
    }

    override fun setOnErrorListener(listener: OnErrorListener?) {
        super.setOnErrorListener(listener)
    }

    //pause video
    override fun pause() {

    }


    //format time
    @SuppressLint("SimpleDateFormat")
    private fun getTime(time: Int): String? {
        return SimpleDateFormat("mm:ss").format(Date(time.toLong()))
    }

}