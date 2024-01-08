package com.example.videoplayerbykotlin.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.DhPlayerView
import com.example.videoplayerbykotlin.R
import com.example.videoplayerbykotlin.Video


class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var dhPlayerView: DhPlayerView? = null
    private var videoArrayList: ArrayList<Video> = ArrayList<Video>()
    private var layoutManager: LinearLayoutManager? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoArrayList = getListVideo()
        recyclerView = view.findViewById(R.id.recycler_video_view)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = VideoAdapter(requireContext(), videoArrayList)

        layoutManager = LinearLayoutManager(context)

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visiblePosition =
                    layoutManager!!.findFirstCompletelyVisibleItemPosition() //find view completely visiable
                if (visiblePosition > -1) {
                    val url: String = videoArrayList[visiblePosition].url
                    dhPlayerView!!.playVideoByUrl(requireContext(), url)
                }
            }
        })
    }

    private fun getListVideo(): ArrayList<Video> {
        val videoList: ArrayList<Video> = ArrayList()
        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
        return videoList
    }
    companion object {

    }

}