package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.visual.R
import com.example.visual.databinding.ActivityMainBinding
import com.example.visual.model.MenuItem
import com.example.visual.model.MenuItems

/**
 * Класс launcher
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        /**установка загрузочного экрана с помощью стиля*/
        setTheme(R.style.Splash)
        super.onCreate(savedInstanceState)
        /** переменная необходимая для DataBinding, биндинга переменных в layout файле*/
        val binding: ActivityMainBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val jobOrders = MenuItem("Заявки в работе", R.drawable.but1)
        val coordinationOrder = MenuItem("Согласование заявок", R.drawable.but2)
        val completeOrders = MenuItem("Выполненные заявки", R.drawable.but3)
        val findOrder = MenuItem("Поиск заявок", R.drawable.but4)
        val documentation = MenuItem("Документация", R.drawable.but5)
        val informationObject= MenuItems(jobOrders, coordinationOrder, completeOrders, findOrder, documentation)
        binding?.information = informationObject
        /**  customLayout по нажатию на которую создаётся [ImageActivity]*/
        val documentationView = findViewById<View>(R.id.Documentation)
        documentationView.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }
    }
    /**
     * @param v необходимый параметр, для связи свойства кнопки onclick с методом [buttonRecycler]
     */
    fun buttonRecycler(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}