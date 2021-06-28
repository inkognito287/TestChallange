package com.example.visual.activity

import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.example.visual.data.FirstActivityDataClass
import com.example.visual.data.ImageActivityDataClass
import com.example.visual.model.ItemUrl
import com.squareup.picasso.Picasso
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

class MainActivityTest{


    @Test
    fun `First test`() {
        val url= ImageActivityDataClass()
        Assertions.assertTrue(url.getList().isNotEmpty())
    }
    @Test
    fun `Check that ItemUrl can add element`() {
        val array= ArrayList<ItemUrl>()
        array.add(ItemUrl("erere"))
        array.add(ItemUrl("23232"))
        array.add(ItemUrl("46574"))
        assert(array.size==3)
    }
    @Test
    fun `Check ImageDataClass`() {
        val imageClass= ImageActivityDataClass()
       assert(imageClass.getList()[0].url=="https://i1.wallbox.ru/wallpapers/main/201120/2d56741e0a7ee12673870def1d8a9856.jpg")
    }
    @Test
    fun `Check FirstActivityDataClass`() {
        val classData=FirstActivityDataClass()
        assert(classData.getInformation()[0].imgId.size==5)
        assert(classData.getInformation()[0].title[3]=="Поиск заявок")
    }
    @Test
    fun `heheeh`(){
    var imageView= ImageView(ApplicationProvider.getApplicationContext())
    Handler(Looper.getMainLooper()).post {
        Mockito.verify( Picasso.get().load("rerete").into(imageView))
    }


}}