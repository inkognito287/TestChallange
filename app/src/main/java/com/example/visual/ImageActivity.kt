package com.example.visual



import android.opengl.Matrix
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import com.synnapps.carouselview.ViewListener
import com.zolad.zoominimageview.ZoomInImageView


class ImageActivity : AppCompatActivity() {
    var sampleImages = arrayOf(
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val carouselView = findViewById(R.id.carouselView) as CarouselView;
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener)

    }
    var imageListener: ImageListener = object : ImageListener {


        override fun setImageForPosition(position: Int, imageView: ImageView) {
            //var im=imageView as(SubsamplingScaleImageView)
                //var im=imageView as(ZoomInImageView)
            // You can use Glide or Picasso here
            imageView.setOnClickListener {
                var l=SubsamplingScaleImageView(this@ImageActivity)
               // l.setImage(Picasso.get().load(sampleImages[position]))
                //var c:SubsamplingScaleImageView=imageView


            }
            Picasso.get().load(sampleImages[position]).into(imageView)
        }
    }
}
