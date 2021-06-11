package com.example.visual

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.ActivitySecondBinding
import java.util.*
import kotlin.collections.ArrayList

var filteredList: ArrayList<RecyclerClass> = ArrayList()
class SecondActivity : AppCompatActivity(), View.OnClickListener,
    RecyclerAdapter.OnItemClickListener {
    lateinit var binding: ActivitySecondBinding

    private var index = 0
    var recList = ArrayList<RecyclerClass>()
    private val adapter = RecyclerAdapter(recList, this)
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
    private val titleList = listOf(
        "Видеонаблюдение ",
        "Управление доступом",
        "Компьютерные розетки",
        "Реклама на ТВ и мониторах",
        "HD Телевидение",
        "Эфирное телевидение",
        "Музыкальное оформление",
        "Бесперебойное питание",
        "Сенсорные киоски",
        "Охранная сигнализация"
    )
    private val informationList = listOf(
        listOf(
            "Видеонаблюдение",
            "Видеонаблюдение",
            "Видеонаблюдение",
            "Видеонаблюдение",
            "Видеонаблюдение",
            "Видеонаблюдение"
        ),
        listOf(
            "Управление доступом",
            "Управление доступом",
            "Управление доступом",
            "Управление доступом",
            "Управление доступом",
            "Управление доступом"
        ),
        listOf("3", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("4", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("5", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("6", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("7", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("8", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("9", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("10", "3", "3", "3", "3", "3", "3", "3", "3", "3"),
        listOf("3", "3", "3", "3", "3", "3", "3", "3", "3", "3")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("MyLog","Second")
        // adapter.setOnItemClickListener(RecyclerAdapter.OnItemClickListener)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var rcView = findViewById<RecyclerView>(R.id.rcView)
        binding.apply {

            rcView.layoutManager = LinearLayoutManager(this@SecondActivity)
            rcView.adapter = adapter
            for (x in 0..imageList.size - 1) {
                val item = RecyclerClass(imageList[index], titleList[index])
                addItem(item)
                index++

            }

        }
        var editText:EditText=findViewById<EditText>(R.id.search)
        editText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                filter (s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }
    fun filter(text:String){

        for (d in recList ){

            if(d.title.lowercase().contains(text.lowercase()))
                filteredList.add(d)
        }
        //recList=filteredList
        adapter.filterList(filteredList)
    }

    fun send() {
        val intent4: Intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        startActivity(intent4)

    }

    override fun onClick(v: View?) {
        View.OnClickListener() {

        }
    }

    override fun onItemClick(position: Int) {

        val intent4: Intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        intent4.putExtra("position", position)
        startActivity(intent4)
//        Toast.makeText(this, "Item$position clicked", Toast.LENGTH_SHORT).show()
//        val clickedItem = recList[position]
//        clickedItem.title="YRA"
//        adapter.notifyItemChanged(position)
    }

    fun addItem(recycler: RecyclerClass) {
        recList.add(recycler)
    }

}