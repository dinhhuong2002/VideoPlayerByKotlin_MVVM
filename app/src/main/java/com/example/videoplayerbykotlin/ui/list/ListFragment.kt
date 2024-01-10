package com.example.videoplayerbykotlin.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.videoplayerbykotlin.R
import com.example.videoplayerbykotlin.Video


class ListFragment : Fragment(),IPlayer {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private var dhPlayerView: DhPlayerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_video_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val videoList = listOf(
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
            Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),

            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
            Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),

            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
            Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),

            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
            Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"),
            Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),

            )

        // Initialize the adapter with the sample data
        videoAdapter = VideoAdapter(videoList)
        recyclerView.adapter = videoAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visiblePosition: Int =
                    layoutManager.findFirstCompletelyVisibleItemPosition() //find view completely visiable
                if (visiblePosition > -1) {
                    val v: View? = layoutManager.findViewByPosition(visiblePosition)
                    val url: String = videoList[visiblePosition].url
                    dhPlayerView = context?.let { DhPlayerView(it,null,null) }
                    dhPlayerView!!.playVideoByUrl(url)
                }
            }
        })
        return view
    }

    private fun getListVideo(): ArrayList<Video> {
        val videoList: ArrayList<Video> = ArrayList()
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))

        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))

        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))

        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))
        return videoList
    }

    companion object {

    }

    override fun getPlayerState(eventLog: String) {
    }

    override fun onDestroy() {
        // Perform cleanup or resource release here before the fragment is destroyed.
        super.onDestroy()
    }
}