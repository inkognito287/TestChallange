package com.example.visual



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import com.synnapps.carouselview.ViewListener
import java.net.URL


class ImageActivity : AppCompatActivity() {
    lateinit var image:SubsamplingScaleImageView
    lateinit var iamgeSource:ImageSource
    lateinit var image1:Bitmap
    var c=0
//    lateinit var subscale1:SubsamplingScaleImageView
    var sampleImages = arrayOf(
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg",
        "https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg"
    )
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
       // subscale1= findViewById<SubsamplingScaleImageView>(R.id.sub)
        val carouselView = findViewById(R.id.carouselView) as CarouselView;
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener)
        image=findViewById(R.id.subscale)

       // val newurl =URL("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg")
      // Picasso.get().load("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg").into(image)
      //  Picasso.with(this).load("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg").into(image)
       Thread(Runnable()  {
            try {

                val imageURL = URL("https://images.hdqwalls.com/wallpapers/godzilla-king-of-the-monsters-10k-he.jpg")
                 image1 = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream())
                                runOnUiThread {
                                  //  var bytes = image1.by;
                                    iamgeSource = ImageSource.bitmap(image1)
                                    //image.setImage(iamgeSource)
                                }
            }catch (e:Exception){
                Log.d("MyLog",e.toString())
            }
        }).start()

    }
//    var cx=nn("https://cs6.pikabu.ru/avatars/980/x980752-313007080.png")
//    runOnUiThread { image.setImageDrawable(cx) }
    var imageListener: ImageListener = object : ImageListener {


        override fun setImageForPosition(position: Int, imageView: ImageView) {

            //var im=imageView as(SubsamplingScaleImageView)
                //var im=imageView as(ZoomInImageView)
            // You can use Glide or Picasso here
            imageView.setOnClickListener {
                //var l=SubsamplingScaleImageView(this@ImageActivity)
                if(c==0) {
                    image.setImage(iamgeSource)
                    image.scaleX=2.0F
                    image.scaleY=2.0F
                    image.visibility = View.VISIBLE
                c++
                }
                //setContentView(R.layout.subscale)
               // l.setImage(Picasso.get().load(sampleImages[position]))
                //var c:SubsamplingScaleImageView=imageView


            }
            Thread(){
                Thread.sleep(3000)
            runOnUiThread {

                imageView.setImageBitmap(image1)
            }}.start()
            //Picasso.get().load(sampleImages[position]).into(imageView)

            }
    }



}
fun nn(url:String): BitmapDrawable {
    var x:Bitmap
    var connection= URL(url).openConnection()
    connection.setRequestProperty("User-agent","Mozilla/4.0")
    connection.connect()
    var input=connection.getInputStream()
    x=BitmapFactory.decodeStream(input)
    return BitmapDrawable(x)
}