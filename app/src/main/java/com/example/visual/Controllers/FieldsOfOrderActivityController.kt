package com.example.visual.Controllers

import android.R
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.visual.OrderActivity

class FieldsOfOrderActivityController( var view: OrderActivity) {
    private val employers = arrayOf(
        "Коротаев Александр",
        "Барбороскин Николай",
        "Куликовская Анастасия ",
        "Чурило Оксана",
        "Маршалов Пётр",
        "Касьян Кристина"
    )
    private var titleOfOrderFields = arrayOf(
        "Контроль",
        "Отдел исполнитель",
        "Сотрудник отдела исполнителя",
        "Подключенный отдел",
        "Сотрудники...",
        "Требуются на",
        "Контакт в ЦО"
    )
    private val department = arrayOf(
        "TCP", "TDP", "UDP", "AMD", "UFC"
    )
    private val actions = arrayOf("Согласование", "Разрешение", "Управление")
    fun getListSze():Int{
        return employers.size
    }
    fun getEmployers():Array<String>{
        return employers
    }
    fun getTitleOfOrderFields():Array<String>{
        return titleOfOrderFields
    }
    fun getDepartment():Array<String>{
        return department
    }
    fun getActions():Array<String>{
        return actions
    }
    fun createList(array: Array<String>,textArr:Array<String>):ListView{
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        var list=ListView(view)
        list.layoutParams=params
        val adapter = ArrayAdapter(
            view,
            R.layout.simple_list_item_1, array
        )
        list.adapter=adapter
        list.setBackgroundResource(R.color.white)

     return list
    }


}