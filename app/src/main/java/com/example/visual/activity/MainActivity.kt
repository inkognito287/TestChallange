package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.R

import com.example.visual.databinding.ActivityMainBinding
import com.example.visual.data.FirstActivityDataClass


class MainActivity : AppCompatActivity() {
    lateinit var rcView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Splash)
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.information = FirstActivityDataClass().getInformation()[0]

        val documentation = findViewById<View>(R.id.Documentation)
       // documentation.setBackgroundResource(R.drawable.main_item_select)
        val view=findViewById<View>(R.id.view)
        //view.setBackgroundResource(R.drawable.main_item_select)
        //documentation.sele
        documentation.setOnClickListener {

            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            startActivity(intent)
        }
    }
    fun buttonRecycler(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
    fun ordersClick(v:View){
        Toast.makeText(this, "aererer", Toast.LENGTH_SHORT).show()
    }
}