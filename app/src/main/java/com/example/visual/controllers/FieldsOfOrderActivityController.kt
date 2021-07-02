package com.example.visual.controllers

open class FieldsOfOrderActivityController {


    private val employers = arrayOf(
        "Коротаев Александр",
        "Барбороскин Николай",
        "Куликовская Анастасия ",
        "Чурило Оксана",
        "Маршалов Пётр",
        "Касьян Кристина"
    )
    private val department = arrayOf(
        "TCP",
        "TDP",
        "UDP",
        "AMD",
        "UFC"
    )
    private val actions = arrayOf("Согласование", "Разрешение", "Управление")
    fun getEmployers(): Array<String> {
        return employers
    }
    fun getDepartment(): Array<String> {
        return department
    }
    fun getActions(): Array<String> {
        return actions
    }

}