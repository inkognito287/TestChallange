package com.example.visual.Controllers


import com.example.visual.R
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.visual.activity.OrderActivity

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
    private var imageOfOrderFields = arrayOf(
        R.drawable.notification,
        R.drawable.notification,
         R.drawable.notification,
         R.drawable.notification,
         R.drawable.notification,
         R.drawable.notification,
         R.drawable.notification,
         R.drawable.notification
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
    fun getImageOfOrderFields():Array<Int>{
        return imageOfOrderFields
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
            android.R.layout.simple_list_item_1, array
        )
        list.adapter=adapter
        list.setBackgroundResource(com.example.visual.R.drawable.notification)

     return list
    }
}