package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.dataClasses.RecyclerClass
import com.example.visual.databinding.ActivitySecondBinding
import com.example.visual.modelClasses.ModelOfSecondActivity
import java.util.*
import kotlin.collections.ArrayList

var filteredList: ArrayList<RecyclerClass> = ArrayList()


class SecondActivity :
    AppCompatActivity(),
    View.OnClickListener,
    RecyclerAdapter.OnItemClickListener {
    lateinit var binding: ActivitySecondBinding
    private lateinit var array: MutableList<Int>
    private lateinit var model:ModelOfSecondActivity
    private var recList = ArrayList<RecyclerClass>()
    private val adapter = RecyclerAdapter(recList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        array = ArrayList()
        val rcView = findViewById<RecyclerView>(R.id.rcView)
        binding.apply {
            model=ModelOfSecondActivity()
            recList.addAll(model.getList())
            rcView.layoutManager = LinearLayoutManager(this@SecondActivity)
            rcView.adapter = adapter


        }
        val editText: EditText = findViewById(R.id.search)
        editText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filter(s.toString())
                }
            }
        )
    }
    fun filter(text: String) {
        filteredList.clear()
        array.clear()
        for ((index, d) in recList.withIndex()) {
            if (d.title.lowercase().contains(text.lowercase())) {
                filteredList.add(d)
                array.add(index)
            }
        }
        adapter.filterList(filteredList)
    }
    fun send() {
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        startActivity(intent)
    }
    override fun onClick(v: View?) {
        View.OnClickListener {
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog", "Destroy")
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        val search = findViewById<EditText>(R.id.search)
        if (search.text.toString() != "")
            intent.putExtra("position", array[position])
        else intent.putExtra("position", position)
        startActivity(intent)
        finish()
    }
}