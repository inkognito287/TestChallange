package com.example.visual.data

import com.example.visual.R
import com.example.visual.model.RecyclerClass
import java.util.*
import kotlin.collections.ArrayList

class SecondActivityData:Observable() {
    private var list=ArrayList<RecyclerClass>()
    private var itemOfRecList: RecyclerClass = RecyclerClass(R.drawable.rc_1, "Видеонаблюдение")

    init {
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_2, "Управление доступом")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_3, "Компьютерные розетки")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_4, "Реклама на ТВ и мониторах")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_5, "HD Телевидение")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_6, "Эфирное телевидение")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_7, "Музыкальное оформление")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_8, "Бесперебойное питание")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_9, "Сенсорные киоски")
        list.add(itemOfRecList)
        itemOfRecList = RecyclerClass(R.drawable.rc_10, "Охранная сигнализация")
        list.add(itemOfRecList)
    }
    fun getList():ArrayList<RecyclerClass>{
        return list
    }

}