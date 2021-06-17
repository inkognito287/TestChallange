package com.example.visual.Controllers

class ItemUrl {
    private lateinit var url:String

    constructor(url: String) {
        this.url = url
    }
    fun getUrl():String{
        return url
    }
    fun setUrl(url: String){
        this.url=url
    }
}