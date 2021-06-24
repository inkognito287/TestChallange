package com.example.visual.model

import com.example.visual.R
import com.example.visual.data.Information
import java.util.*
import kotlin.collections.ArrayList

class ModelOfFirstActivity:Observable() {
   private var list=ArrayList<Information>()

    init {
        list.add(Information(
            arrayOf(
                R.drawable.but1,
                R.drawable.but2,
                R.drawable.but3,
                R.drawable.but4,
                R.drawable.but5
            ),
            arrayOf(
                "Заявки в работе",
                "Согласование заявок",
                "Выполненные заявки",
                "Поиск заявок",
                "Документация"
            )
        ))
    }
    fun getInformation():ArrayList<Information>{
        return list
    }
}