package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.R
import com.example.visual.dataClasses.Information

import com.example.visual.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var rcView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.information =
            Information(
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
            )
        val documentation = findViewById<View>(R.id.Documentation)
        documentation.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }
    }
    fun buttonRecycler(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}