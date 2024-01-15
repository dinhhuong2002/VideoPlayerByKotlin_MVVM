package com.example.videoplayerbykotlin

class Video {
    var title: String
    var url: String
    var description: String
    var thumb: String
    var subTitle: String

    constructor(title: String, url: String, description: String, thumb: String, subTitle: String) {
        this.title = title
        this.url = url
        this.description = description
        this.thumb = thumb
        this.subTitle = subTitle
    }

}