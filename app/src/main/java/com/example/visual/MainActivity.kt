package com.example.visual

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.ActivityMainBinding


lateinit var rcView:RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var Binding: ActivityMainBinding? =DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.abrakadabra)
        Binding?.name="Заявки в работе"
        Binding?.name2="Согласование заявок"
        Binding?.name3="Выполненные заявки"
        Binding?.name4="Поиск заявок"
        Binding?.name5="Документация"

        Binding?.vari=R.drawable.but4
        Binding?.vari2=R.drawable.but5


    }
    fun buttonRecycler(view: View){
        var intent= Intent(this@MainActivity,SecondActivity::class.java)
        startActivity(intent)
    }
}

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}