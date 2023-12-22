package com.example.videoplayerbykotlin.ui.home

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.media3.exoplayer.analytics.AnalyticsListener
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.PlayerViewJava
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

        textViewShowConfig?.movementMethod = ScrollingMovementMethod()

        var url: String =
            "http://techslides.com/demos/sample-videos/small.mp4"
        var url1: String =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        var url2: String =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
//        enterLink?.setText(url)

        dhPlayerView = context?.let { DhPlayerView(it, this) }
        playerViewJava = PlayerViewJava(context)

        //set view dhPlayer from Libs
        frameLayout?.addView(dhPlayerView)


        /*play video default*/
//        dhPlayerView!!.playVideoByUrl(requireContext(), url)

        //click on button Config
        btnConfig?.setOnClickListener {
            val playLink: String = editTextLinkVideo?.text.toString()
            Log.d(TAG, "Clicked button config!")
            Log.d(TAG, url)

            dhPlayerView!!.playVideoByUrl(requireContext(), playLink)
        }

    }

    override fun getPlayerState(context: Context, url: String): String {
        TODO("Not yet implemented")
    }

    override fun addAnalyticsListener(listener: AnalyticsListener?) {
        TODO("Not yet implemented")
    }

    @SuppressLint("SetTextI18n")
    override fun testShowLog(eventLog: String) {
        var currentLog: String = textViewShowConfig?.text.toString()
        textViewShowConfig?.text = "$currentLog \n $eventLog"
    }


}
