package com.example.videoplayerbykotlin.ui.list

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.videoplayerbykotlin.R
import com.example.videoplayerbykotlin.Video
import org.json.JSONObject


class ListFragment : Fragment(), IPlayer {

    private var linearLayoutManager: LinearLayoutManager? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private val listVideoInFragment: MutableList<Video> = ArrayList()
    private var dhPlayerView: DhPlayerView? = null
    private var frameLayout: FrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_video_view)

        getDataFromJsonRaw()

        //init layoutManager
        linearLayoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = linearLayoutManager

        dhPlayerView = DhPlayerView(requireContext(), null, null)
        // Initialize the adapter with the sample data
        videoAdapter = VideoAdapter(listVideoInFragment, dhPlayerView!!)
        recyclerView.adapter = videoAdapter


        return view
    }



    @SuppressLint("UnsafeOptInUsageError")
    fun getDataFromJsonRaw() {
        val jsonData = resources.openRawResource(R.raw.music).bufferedReader().use { it.readText() }
        val outputJsonString = JSONObject(jsonData)

        val videoList = outputJsonString.getJSONArray("Video")
        for (i in 0 until videoList.length()) {

            val itemObj: JSONObject = videoList.getJSONObject(i)

            var title = itemObj.getString("title")
            var url = itemObj.getString("sources")
            var description = itemObj.getString("description")
            var thumb = itemObj.getString("thumb")
            var subTitle = itemObj.getString("subtitle")

            var video = Video(title, url, description, thumb, subTitle)
            listVideoInFragment.add(video)
        }
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @OptIn(UnstableApi::class)
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            @SuppressLint("MissingInflatedId")
            @OptIn(UnstableApi::class)
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visiblePosSelected =
                    linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                Log.d(TAG, "VisiblePosition: $visiblePosSelected")

                if (visiblePosSelected > -1) {
                    val linkVideoSelected = listVideoInFragment[visiblePosSelected].url
                    Log.d(TAG, "title: " + listVideoInFragment[visiblePosSelected].title)

                    var v = linearLayoutManager!!.findViewByPosition(visiblePosSelected)
                    frameLayout = v!!.findViewById(R.id.videoInList)
                    if (dhPlayerView!!.parent != null) {
                        (dhPlayerView!!.parent as ViewGroup).removeView(dhPlayerView)

                    }
                    frameLayout!!.addView(dhPlayerView)
                    dhPlayerView!!.playVideoByUrl(linkVideoSelected)
                }
                videoAdapter.notifyDataSetChanged();
            }
        })
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
    @OptIn(UnstableApi::class)
    override fun getPlayerState(eventLog: String) {

    }
}