package com.example.visual.activity

import android.content.Context
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.example.visual.model.ItemUrl
import com.squareup.picasso.Picasso
import org.junit.Test



internal class ImageAc2tivityTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    @Test
    fun setImageListener() {
        var itemList = ArrayList<ItemUrl>()
        itemList.add(ItemUrl("https://w-dog.ru/wallpapers/9/7/506744950768048/kanada-ozeero-morejn-el-priroda-gory-oblaka-nebo-les-sklon-kamni.jpg"))

        val position=0
        val imageOfCarousel = Picasso.get().load(itemList[position].getUrl())
        var imageview=ImageView(context)
        assert(true, { imageOfCarousel.into(imageview) })

    }

}