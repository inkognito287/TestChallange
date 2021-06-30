package com.example.visual.activity

import com.example.visual.data.SecondActivityDataClass
import com.example.visual.model.RecyclerClass
import org.junit.jupiter.api.Test



internal class SecondActivityTest {

    @Test
    fun filter() {
        var array: MutableList<Int>
       var recList = ArrayList<RecyclerClass>()
        var  kek=SecondActivityDataClass()
        array= ArrayList()
        filteredList.clear()
        array.clear()
     recList.addAll(kek.getList())
        val list= listOf<String>("Управление","Розетки","Абракадабра","A<b")
        val text= list[(0..1).random()]
        for ((index, d) in recList.withIndex()) {
            if (d.title.lowercase().contains(text.lowercase())) {
                filteredList.add(d)
                array.add(index)
            }
        }
        assert(!array.isEmpty())
    }


}