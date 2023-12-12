package com.example.videoplayerbykotlin

import android.widget.ImageView

class Video {
    lateinit var title: String
    lateinit var url: String
    lateinit var description: String
    lateinit var thumb: ImageView

    constructor(title: String, url: String, description: String, thumb: ImageView) {
        this.title = title
        this.url = url
        this.description = description
        this.thumb = thumb
    }

    constructor(url: String) {
        this.url = url
    }
}