package com.example.videoplayerbykotlin.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.videoplayerbykotlin.R
import com.example.videoplayerbykotlin.Video

class VideoAdapter(private val myVideoList: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView),
        IPlayer {
//        private val context: Context
//            get() = itemView.context

        var dhPlayerViewInList = DhPlayerView(ItemView.context, attributeSet = null, iPlayer = this)
        var tvLinkVideoInList: TextView = itemView.findViewById(R.id.linkOfTheVideo)
        override fun getPlayerState(eventLog: String) {

        }
//        var frVideoInList: FrameLayout = itemView.findViewById(R.id.videoInList)
//        val image:ImageView = itemView.findViewById(R.id.image)
//        val textView:TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_items, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        val itemVideo = myVideoList[position]

//        holder.image.setImageResource(R.drawable.img_test)
//        holder.textView.text="This is layout test!"

        holder.tvLinkVideoInList.text = itemVideo.url
        holder.dhPlayerViewInList.tag = itemVideo
    }

    override fun getItemCount(): Int {
        return myVideoList.size
//        return 10
    }


}