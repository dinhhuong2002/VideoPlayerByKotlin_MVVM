package com.example.videoplayerbykotlin

import android.widget.ImageView

class Video {
    lateinit var title: String
     var url: String
         get() {
             return url
         }
    lateinit var description: String
    private lateinit var thumb: ImageView

    constructor(title: String, url: String, description: String, thumb: ImageView) {
        this.title = title
        this.url = url
        this.description = description
        this.thumb = thumb
    }

    constructor(url: String) {
        this.url = url
    }
//    private fun getListVideo(): ArrayList<Video> {
//        val videoList: ArrayList<Video> = ArrayList()
//        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
//        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
//        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
//        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
//        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
//        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
//        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
//        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
//        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
//        videoList.add(Video("https://file-examples.com/storage/fe19e15eac6560f8c936c41/2017/04/file_example_MP4_640_3MG.mp4"))
//        videoList.add(Video("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
//        videoList.add(Video("http://techslides.com/demos/sample-videos/small.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/30/WomanWalking-1609303462769.mp4"))
//        videoList.add(Video("https://43324700545123422b.lotuscdn.vn/201204812902309888/2020/12/2/918127046-1606890428689386032470.mp4"))
//        return videoList
//    }
}