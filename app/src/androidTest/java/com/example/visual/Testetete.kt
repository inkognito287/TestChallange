package com.example.visual

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.example.visual.data.SecondActivityDataClass
import com.squareup.picasso.Picasso
import org.junit.Test
import org.mockito.Mockito

class Testetete {


    @Test
    fun `CheckSecondActivityDatxaClassc`() {
        var context= ApplicationProvider.getApplicationContext<Context>().applicationContext
        val classData= SecondActivityDataClass()
        print("erere")
        var imageView=ImageView(ApplicationProvider.getApplicationContext())
        Handler(Looper.getMainLooper()).post {
            Mockito.verify( Picasso.get().load("rerete").into(imageView))
        }



    }
}