package com.example.visual.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.dataClasses.RecyclerClass
import com.example.visual.databinding.ActivityThirdBinding
import com.example.visual.modelClasses.ModelOfThirdActivity

class ThirdActivity :
    AppCompatActivity(),
    View.OnClickListener,
    RecyclerAdapter.OnItemClickListener {
    private var position = 0
    lateinit var binding: ActivityThirdBinding
    private var recList = ArrayList<RecyclerClass>()
    private val adapter = RecyclerAdapter(recList,this)
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
            recList.addAll(ModelOfThirdActivity().getList()[position])
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
}