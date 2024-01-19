package com.example.videoplayerbykotlin.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.PlayerViewJava
import com.example.videoplayerbykotlin.R

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), IPlayer {
    // TODO: Rename and change types of parameters
    private var frameLayout: FrameLayout? = null
    private var editTextLinkVideo: EditText? = null
    private var btnConfig: Button? = null
    private var textViewShowConfig: TextView? = null
    private var dhPlayerView: DhPlayerView? = null
    private var playerViewJava: PlayerViewJava? = null

    private val TAG: String = "HomeFragment"

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
        editTextLinkVideo = view.findViewById<EditText>(R.id.enter_link)
        btnConfig = view.findViewById<Button>(R.id.btn_Stream)
        textViewShowConfig = view.findViewById<TextView>(R.id.player_state)

    }

    override fun onStart() {
        super.onStart()

        textViewShowConfig?.movementMethod = ScrollingMovementMethod()
        var url: String =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        var url1: String =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"

        dhPlayerView = context?.let { DhPlayerView(it, null, this) }
        playerViewJava = PlayerViewJava(context)

        //set view dhPlayer from Libs
        frameLayout?.addView(dhPlayerView)
        if (!dhPlayerView!!.isPlaying()) {
            dhPlayerView!!.playVideoByUrl(url)
        } else {
            dhPlayerView!!.pauseVideo()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getPlayerState(eventLog: String) {
//        Log.d("ContentValues", "log video state in home fragment: $eventLog")
        var currentLog: String = textViewShowConfig?.text.toString()
        textViewShowConfig?.movementMethod = ScrollingMovementMethod()
        textViewShowConfig?.text = "$eventLog \n $currentLog"
    }

    override fun onResume() {
        super.onResume()
        val playLink: String = editTextLinkVideo?.text.toString()
        Log.d(TAG, "Clicked button config!")

        //click on button Config
        btnConfig?.setOnClickListener {
            Log.d(TAG, "Clicked button config!")
            dhPlayerView!!.playVideoByUrl(playLink)
        }
    }

    override fun onPause() {
        super.onPause()


    }

    override fun onStop() {
        super.onStop()
        dhPlayerView!!.pauseVideo()
    }

    override fun onDestroy() {
        // Perform cleanup or resource release here before the fragment is destroyed.
        super.onDestroy()
    }
}
