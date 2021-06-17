package com.example.visual.Controllers

import com.example.visual.dataClasses.Images
import com.example.visual.ImageActivity

class ImagesController(var model:Images, var view:ImageActivity ) {
    var sampleImages = arrayOf(
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://kenrockwell.com/leica/images/50mm-f35/sample-images/L1012285.jpg",
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://www.esato.com/phonephotos/cam/samsung/sm_g900f/201406011711P0ER52.jpg"
    )
    fun getImageUrl():String{
        return model.url
    }
    fun getPageCount():Int{
        return sampleImages.size
    }
    fun setImageUrl(position:Int){
        model.url=sampleImages[position]
    }
    fun updateView(){
        view.printDetails(model.url)
    }
}