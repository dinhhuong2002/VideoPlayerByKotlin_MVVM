package com.example.videoplayerbykotlin.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.RecyclerView
import com.example.DhPlayerView
import com.example.IPlayer
import com.example.videoplayerbykotlin.R
import com.example.videoplayerbykotlin.Video
import org.json.JSONObject

class VideoAdapter(
    private val myVideoList: List<Video>,
    private val context: Context
) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() , IPlayer{

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView),
        IPlayer {

        var dhPlayerViewInList: DhPlayerView = itemView.findViewById(R.id.videoInList)
        var tvTitleVideoInList: TextView = itemView.findViewById(R.id.linkOfTheVideo)

        override fun getPlayerState(eventLog: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_items, parent, false)

        return ViewHolder(view)
    }

    @OptIn(UnstableApi::class)
    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        val itemVideo = myVideoList[position]


        holder.tvTitleVideoInList.text = itemVideo.url
        holder.dhPlayerViewInList.playVideoByUrl(itemVideo.url)

    }

    override fun getItemCount(): Int {
        return myVideoList.size
//        return 5
    }

    override fun getPlayerState(eventLog: String) {

    }
}