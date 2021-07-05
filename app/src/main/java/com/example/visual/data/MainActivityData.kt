package com.example.visual.data

import com.example.visual.R
import com.example.visual.model.MenuItem
import com.example.visual.model.MenuItems

class MainActivityData {
    private val jobOrders = MenuItem("Заявки в работе", R.drawable.but1)
    private val coordinationOrder = MenuItem("Согласование заявок", R.drawable.but2)
    private val completeOrders = MenuItem("Выполненные заявки", R.drawable.but3)
    private val findOrder = MenuItem("Поиск заявок", R.drawable.but4)
    private val documentation = MenuItem("Документация", R.drawable.but5)
    private val informationObject =
        MenuItems(jobOrders, coordinationOrder, completeOrders, findOrder, documentation)
    fun getMenuItems(): MenuItems {
        return informationObject
    }
}