package com.example.videoplayerbykotlin.ui.home

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.DhPlayer
import com.example.DhPlayerView
import com.example.videoplayerbykotlin.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var frameLayout: FrameLayout? = null
    private var enterLink: EditText? = null
    private var btnConfig: Button? = null
    private var showConfig: TextView? = null
    private var dhPlayerView: DhPlayerView? = null

    private val TAG: String = "HomeFragment"

    private var exoPlayer: ExoPlayer?=null
//    private val playerView: PlayerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frameLayout = view.findViewById<FrameLayout>(R.id.home_video)
        enterLink = view.findViewById<EditText>(R.id.enter_link)
        btnConfig = view.findViewById<Button>(R.id.btn_Stream)
        showConfig = view.findViewById<TextView>(R.id.player_state)

        var url: String =
            "http://techslides.com/demos/sample-videos/small.mp4"
        enterLink?.setText(url)

        dhPlayerView = context?.let { DhPlayerView(it) }
        exoPlayer= context?.let { ExoPlayer.Builder(it).build() }

        frameLayout?.addView(dhPlayerView) //set view dhPlayer from Libs

        btnConfig?.setOnClickListener{
            Log.d(TAG,"Clicked button config!")
            Log.d(TAG,url)
            if (exoPlayer != null) {
                dhPlayerView?.let { it1 -> DhPlayerView.playvideo(exoPlayer!!, it1,url) }
            }

//            showConfig(exoPlayer)
        }
    }
}
