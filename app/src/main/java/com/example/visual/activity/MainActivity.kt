package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.visual.R
import com.example.visual.data.MainActivityData
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
        val informationObject=MainActivityData().getMenuItems()
        binding?.information = informationObject

        /**  customLayout по нажатию на которую создаётся [ImageActivity]*/
        val documentationView = findViewById<View>(R.id.Documentation)
        documentationView.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }

        binding?.buttonCreateOrderBinding?.root?.setOnClickListener() {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}