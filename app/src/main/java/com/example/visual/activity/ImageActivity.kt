package com.example.visual.activity



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.visual.controllers.ItemUrl
import com.example.visual.R
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class ImageActivity : AppCompatActivity() {
    lateinit var carousel: CarouselView
    lateinit var saveparams: ViewGroup.LayoutParams
    lateinit var item: ItemUrl
    var itemList = ArrayList<ItemUrl>()
    private var switchOfCarouselSize = 0
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        carouselView.setImageListener(imageListener)
        carousel = findViewById(R.id.carouselView)
        saveparams = carousel.layoutParams
        item = ItemUrl("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg")
        itemList.add(item)
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
            if (switchOfCarouselSize == 0) {
                carousel.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
                switchOfCarouselSize++
            } else {
                carousel.layoutParams = saveparams
                switchOfCarouselSize = 0
            }
        }
    }
    fun back(v: View) {
        finish()
    }
}