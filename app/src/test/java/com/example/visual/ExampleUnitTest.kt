package com.example.visual

import com.example.visual.activity.ImageActivity
import com.example.visual.activity.OrderActivity
import com.example.visual.controllers.FieldsOfOrderActivityController
import com.example.visual.dataClasses.Information
import com.example.visual.dataClasses.ItemUrl
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun correct1() {
        assertEquals("3", ItemUrl("3").getUrl())
    }


}