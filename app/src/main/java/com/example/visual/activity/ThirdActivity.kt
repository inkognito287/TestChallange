package com.example.visual.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.RecyclerClass
import com.example.visual.databinding.ActivityThirdBinding

class ThirdActivity :
    AppCompatActivity(),
    View.OnClickListener,
    RecyclerAdapter.OnItemClickListener {
    private var position = 0
    lateinit var binding: ActivityThirdBinding
    private var recList = ArrayList<RecyclerClass>()
    private val adapter = RecyclerAdapter(recList,this)
    private val imageList = listOf(
        R.drawable.rc_1,
        R.drawable.rc_2,
        R.drawable.rc_3,
        R.drawable.rc_4,
        R.drawable.rc_5,
        R.drawable.rc_6,
        R.drawable.rc_7,
        R.drawable.rc_8,
        R.drawable.rc_9,
        R.drawable.rc_10
    )
    private val informationList = listOf(
        listOf("Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение"),
        listOf("Управление доступом","Управление доступом","Управление доступом","Управление доступом","Управление доступом","Управление доступом"),
        listOf("Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки","Компьютерные розетки"),
        listOf("Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах","Реклама на ТВ и мониторах"),
        listOf("HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение","HD Телевидение"),
        listOf("Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение","Эфирное телевидение"),
        listOf("Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление","Музыкальное оформление"),
        listOf("Бесперебойное питание","Бесперебойное питание","Бесперебойное питание","Бесперебойное питание","Бесперебойное питание"),
        listOf("Сенсорные киоски", "Сенсорные киоски","Сенсорные киоски"),
        listOf("Охранная сигнализация", "Охранная сигнализация", "Охранная сигнализация", "Охранная сигнализация")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        position = intent.getIntExtra("position", 0)
        binding.apply {
            adapter.recList.clear()
            rcView2.layoutManager = LinearLayoutManager(this@ThirdActivity)
            rcView2.adapter = adapter
            for (element in informationList[position]) {
                val item = RecyclerClass(imageList[position], element)
//                    RecyclerClass(imageList[position],informationList[position][x])
                addItem(item)
            }
        }
    }
    override fun onClick(v: View?) {
        Log.d("MyLog", "THIRD")
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(this@ThirdActivity, OrderActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
    private fun addItem(recycler: RecyclerClass) {
        recList.add(recycler)
    }
}