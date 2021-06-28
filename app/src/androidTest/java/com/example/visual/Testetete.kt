package com.example.visual

import android.content.Context
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.example.visual.data.SecondActivityDataClass
import com.squareup.picasso.Picasso
import org.junit.Test

class Testetete {


    @Test
    fun `CheckSecondActivityDatxaClassc`() {
        var context= ApplicationProvider.getApplicationContext<Context>().applicationContext
        val classData= SecondActivityDataClass()
        print("erere")
        var imageView=ImageView(ApplicationProvider.getApplicationContext())
       Picasso.get().load("rerete").into(imageView)
            assert(imageView.getDrawable()!= null)
    }
}