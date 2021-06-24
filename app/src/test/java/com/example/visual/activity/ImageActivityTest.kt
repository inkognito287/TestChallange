package com.example.visual.activity

import android.content.Context
import com.example.visual.controllers.FieldsOfOrderActivityController
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class ImageActivityTest {

 var z:Context= mock(Context::class.java)


//    @org.junit.Test
//    fun setImageListener() {
//        var context = ApplicationProvider.getApplicationContext<Context>()
//        var itemList = ArrayList<ItemUrl>()
//        itemList.add(ItemUrl("https://w-dog.ru/wallpapers/9/7/506744950768048/kanada-ozeero-morejn-el-priroda-gory-oblaka-nebo-les-sklon-kamni.jpg"))
//
//        val position=0
//        val imageOfCarousel = Picasso.get().load(itemList[position].getUrl())
//       var imageview= ImageView(z)
//        assert(true) { imageOfCarousel.into(imageview) }
//
//    }
    @Test
    fun doAction_doesSomething(){
        /* Given */
        val mock = mock(FieldsOfOrderActivityController::class.java)
        Mockito.`when`(mock.getListSze()).thenReturn(6)
        verify(mock).getListSze()
    }
//
//    @Test
//    fun getCarousel() {
//    }
//
//    @Test
//    fun onBackPressed() {
//    }
}