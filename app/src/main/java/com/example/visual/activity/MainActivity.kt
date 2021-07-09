package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.visual.R
import com.example.visual.data.MainActivityData
import com.example.visual.databinding.ActivityMainBinding

/**
 * Класс launcher
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        /**установка загрузочного экрана с помощью стиля*/
        setTheme(R.style.Splash)
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar)

        /** переменная необходимая для DataBinding, биндинга переменных в layout файле*/
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val informationObject = MainActivityData().getMenuItems()
        binding.information = informationObject
        binding.activity = this
        /**  customLayout по нажатию на которую создаётся [ImageActivity]*/
        binding.Documentation.root.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCreateOrderBinding.root.setOnClickListener() {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }
    fun jobOrdersClick(v: View) {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }
    fun coordinationOrderClick(v: View) {

        val intent=Intent(this,AuthorizationRegistration::class.java)
        startActivity(intent)
        //Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }
    fun completeOrdersClick(v: View) {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }
    fun findOrdersClick(v: View) {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }

}