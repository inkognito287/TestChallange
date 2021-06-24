package com.example.visual.controllers

import org.junit.Assert
import org.junit.Test


internal class FieldsOfOrderActivityControllerTest {


    @Test
    fun getListSze() {
        val m=FieldsOfOrderActivityController(null)
        Assert.assertEquals(6,m.getListSze())
    }
    @Test
    fun Lol(){
        val z=FieldsOfOrderActivityController(null)
        Assert.assertTrue("ahaaha",z.getEmployers()[0].toString()=="Коротаев Александр")
    }
}