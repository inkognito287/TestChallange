package com.example.visual.controllers

import com.example.visual.R
import com.example.visual.activity.OrderActivity

open class FieldsOfOrderActivityController(var view: OrderActivity?) {
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
        "Контакт в ЦО",
        "Поступила:"
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
        "TCP",
        "TDP",
        "UDP",
        "AMD",
        "UFC"
    )
    private val actions = arrayOf("Согласование", "Разрешение", "Управление")
    fun getListSze(): Int {
        return employers.size
    }
    fun getEmployers(): Array<String> {
        return employers
    }
    fun getTitleOfOrderFields(): Array<String> {
        return titleOfOrderFields
    }
    fun getImageOfOrderFields(): Array<Int> {
        return imageOfOrderFields
    }
    fun getDepartment(): Array<String> {
        return department
    }
    fun getActions(): Array<String> {
        return actions
    }

}