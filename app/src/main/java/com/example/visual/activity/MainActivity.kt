package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.visual.R
import com.example.visual.data.FirstActivityDataClass
import com.example.visual.databinding.ActivityMainBinding

/**
 * Класс launcher
 *
 */
class MainActivity : AppCompatActivity() {
    /**
     * [rcView] глобальная переменная отвечающая за содержимое recyclerView во втором и третьем активити
     */
    //lateinit var rcView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        /**установка загрузочного экрана с помощью стиля*/
        setTheme(R.style.Splash)
        super.onCreate(savedInstanceState)
        /** переменная необходимая для DataBinding, биндинга переменных в layout файле*/
        val binding: ActivityMainBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        /** биндинг информации, находящейся в классе [FirstActivityDataClass]*/
        binding?.information = FirstActivityDataClass().getInformation()[0]
        /**  customLayout по нажатию на которую создаётся [ImageActivity]*/
        val documentation = findViewById<View>(R.id.Documentation)
        /** установка слушателя нажатий*/
        documentation.setOnClickListener {
            /*переменная отвечающая за вызов нужного активити*/
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            /**запуск активити*/
            startActivity(intent)
        }
    }

    /**
     * @param v необходимый параметр, для связи свойства кнопки onclick с методом [buttonRecycler]
     * данный метод осуществляте переход с [MainActivity] в [SecondActivity]
     */
    fun buttonRecycler(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}