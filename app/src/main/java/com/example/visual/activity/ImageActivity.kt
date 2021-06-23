package com.example.visual.activity


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.contains
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.visual.R
import com.example.visual.dataClasses.ItemUrl
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class ImageActivity : AppCompatActivity() {
    lateinit var carousel: CarouselView
    lateinit var saveParamsOfCarousel: ViewGroup.LayoutParams
    lateinit var item: ItemUrl
    lateinit var mainConstraintLayout: ConstraintLayout
    lateinit var m:SubsamplingScaleImageView
    var itemList = ArrayList<ItemUrl>()
    private var switchOfCarouselSize = 0

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        mainConstraintLayout=findViewById(R.id.main)
        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        carouselView.setImageListener(imageListener)
        carousel = findViewById(R.id.carouselView)
        saveParamsOfCarousel = carousel.layoutParams
        item =
            ItemUrl("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg")
        itemList.add(item)
        item= ItemUrl("https://i1.wallbox.ru/wallpapers/main/201120/2d56741e0a7ee12673870def1d8a9856.jpg")
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        carouselView.pageCount = itemList.size
    }

    var imageListener: ImageListener = ImageListener { position, imageView ->

        val imageOfCarousel = Picasso.get().load(itemList[position].getUrl())

        imageOfCarousel.rotate(90f)
        imageOfCarousel.into(imageView)

        imageView.setOnClickListener {
            m=SubsamplingScaleImageView(this)
            Thread(Runnable {
                var image=imageOfCarousel.get()
                var zxc=ImageSource.bitmap(image)
                runOnUiThread(){
                    m.setImage(zxc)
                }
             }).start()

            mainConstraintLayout.addView(m)

//            if (switchOfCarouselSize == 0) {
//                carousel.layoutParams = ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.MATCH_PARENT,
//                    ConstraintLayout.LayoutParams.MATCH_PARENT
//                )
//                switchOfCarouselSize++
//            } else {
//                carousel.layoutParams = saveParamsOfCarousel
//                switchOfCarouselSize = 0
//            }
        }
    }

    override fun onBackPressed() {
        if (mainConstraintLayout.contains(m))
        mainConstraintLayout.removeView(m)
        else super.onBackPressed()
    }
    fun back(v: View) {
        finish()
    }

}
