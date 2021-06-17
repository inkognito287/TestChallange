package com.example.visual



import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.visual.Controllers.ImagesController
import com.example.visual.Controllers.ItemUrl
import com.example.visual.dataClasses.Images
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class ImageActivity : AppCompatActivity() {
    lateinit var iamgeSource:ImageSource
    lateinit var image1:Bitmap
    var switch=0
    lateinit var carousel:CarouselView
    lateinit var saveparams:ViewGroup.LayoutParams
    lateinit var model:Images
    lateinit var controller:ImagesController
    lateinit var item: ItemUrl
    var itemList = ArrayList<ItemUrl>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        model=getDatafromDb()
        var view=ImageActivity()
        controller=ImagesController(model,view)
        val carouselView = findViewById(R.id.carouselView) as CarouselView;

        carouselView.setImageListener(imageListener)
        carousel=findViewById(R.id.carouselView)
        saveparams=carousel.layoutParams
        item=ItemUrl("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg")
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        carouselView.setPageCount(itemList.size);
        Log.d("MyLog",model.url)
    }
    private fun getDatafromDb(): Images {
        return Images("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-11k-he.jpg")
    }
    fun printDetails(url: String) {
    }
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
           var a=Picasso.get().load(itemList[position].getUrl())
//            controller.setImageUrl(position)
//            var a=Picasso.get().load(model.url)
            a.rotate(90f)
            a.into(imageView)
            imageView.setOnClickListener {
                if(switch==0){
                carousel.layoutParams=ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT)
                switch++
                }
                else
                {
                    carousel.layoutParams=saveparams
                    switch=0
                }
            }
            }
    }

}
