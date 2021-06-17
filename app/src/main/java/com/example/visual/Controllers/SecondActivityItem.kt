package com.example.visual.Controllers

class SecondActivityItem {
    private var title:String
    private var image:Int
    constructor(title: String, image: Int) {
        this.title = title
        this.image = image
    }
    fun getTitle():String{
        return title
    }
    fun getImage():Int{
        return image
    }
    fun setTitle(title: String){
        this.title=title
    }
    fun setImage(image: Int){
        this.image=image
    }
}