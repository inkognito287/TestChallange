package com.example.visual

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.dataClasses.Information

import com.example.visual.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var rcView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var inf=Information(R.drawable.but1,"sus")
        val Binding: ActivityMainBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Binding?.information=
            Information(arrayOf(R.drawable.but1,R.drawable.but2,R.drawable.but3,R.drawable.but4,R.drawable.but5), arrayOf("Заявки в работе","Согласование заявок","Выполненные заявки","Поиск заявок","Документация"))
        //setContentView(R.layout.abrakadabra)
        var Documentation=findViewById<View>(R.id.Documentation)
        Documentation.setOnClickListener {
            var intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }
    }

    fun buttonRecycler(view: View) {
        var intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}

