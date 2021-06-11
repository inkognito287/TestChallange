package com.example.visual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity(),View.OnClickListener,RecyclerAdapter.OnItemClickListener {
    var position=0
    lateinit var binding: ActivityThirdBinding
    var recList = ArrayList<RecyclerClass>()
    private val adapter= RecyclerAdapter(recList,this)
    private val imageList= listOf(R.drawable.rc_1,R.drawable.rc_2,R.drawable.rc_3,R.drawable.rc_4,R.drawable.rc_5,R.drawable.rc_6,R.drawable.rc_7,R.drawable.rc_8,R.drawable.rc_9,R.drawable.rc_10)
    private val titleList= listOf("Видеонаблюдение ","Управление доступом","Компьютерные розетки","Реклама на ТВ и мониторах","HD Телевидение","Эфирное телевидение","Музыкальное оформление","Бесперебойное питание","Сенсорные киоски","Охранная сигнализация")
    private val informationList= listOf(
        listOf("Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение","Видеонаблюдение"),
        listOf("Управление доступом","Управление доступом","Управление доступом","Управление доступом","Управление доступом","Управление доступом"),
        listOf("3","3","3","3","3","3","3","3","3","3"),
        listOf("4","3","3","3","3","3","3","3","3","3"),
        listOf("5","3","3","3","3","3","3","3","3","3"),
        listOf("6","3","3","3","3","3","3","3","3","3"),
        listOf("7","3","3","3","3","3","3","3","3","3"),
        listOf("8","3","3","3","3","3","3","3","3","3"),
        listOf("9","3","3","3","3","3","3","3","3","3"),
        listOf("10","3","3","3","3","3","3","3","3","3"),
        listOf("3","3","3","3","3","3","3","3","3","3")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("MyLog","Third")
        binding= ActivityThirdBinding.inflate(layoutInflater)
         setContentView(binding.root)
        val intent= getIntent()
  position=intent.getIntExtra("position",0)
           // position=pos.posit
        //rcView=findViewById<RecyclerView>(R.id.rcView)
        binding.apply {
            adapter.recList.clear()
            rcView2.layoutManager= LinearLayoutManager(this@ThirdActivity)
            rcView2.adapter =    adapter
            for (x in 0..informationList[position].size-1){
                val item =RecyclerClass(imageList[position],informationList[position][x])
//                    RecyclerClass(imageList[position],informationList[position][x])
                addItem(item)

            }

        }
    }

    override fun onClick(v: View?) {
        Log.d("MyLog","THIRD")

    }

    override fun onItemClick(position: Int) {
         Toast.makeText(this,"Item$position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = recList[position]
        clickedItem.title="YRA"
        adapter.notifyItemChanged(position)
    }
    fun addItem(recycler: RecyclerClass) {
        recList.add(recycler)
    }
}