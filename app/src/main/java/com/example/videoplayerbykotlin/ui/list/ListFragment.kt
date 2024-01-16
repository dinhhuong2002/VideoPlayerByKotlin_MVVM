package com.example.videoplayerbykotlin.ui.list

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        dhPlayerView = DhPlayerView(requireContext(), null, null)
        recyclerView = view.findViewById(R.id.recycler_video_view)

        getDataFromJsonRaw()

        //init layoutManager
        linearLayoutManager = LinearLayoutManager(context)


        // Initialize the adapter with the sample data
        videoAdapter = context?.let { VideoAdapter(listVideoInFragment, it) }!!

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @OptIn(UnstableApi::class)
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
        }

            @OptIn(UnstableApi::class)
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visiblePosSelected = linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                Log.d(TAG,"VisiblePosition: $visiblePosSelected")

                if(visiblePosSelected > -1){
                    val linkVideoSelected = listVideoInFragment[visiblePosSelected].url
                    Log.d(TAG, "title: " + listVideoInFragment[visiblePosSelected].title)
                    dhPlayerView!!.playVideoByUrl(linkVideoSelected)
                }
            }
        })
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = videoAdapter
        videoAdapter.notifyDataSetChanged();
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

    @OptIn(UnstableApi::class)
    override fun getPlayerState(eventLog: String) {

    }

    override fun onDestroy() {
        // Perform cleanup or resource release here before the fragment is destroyed.
        super.onDestroy()
    }
}