package com.example.player

class VideoViewModel {
    lateinit var url: String

    fun playVideo(url:String){
        this.url=url
    }

}