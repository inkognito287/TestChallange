package com.example.visual

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivitySecondBinding
    var listener1 = SecondActivity()
    private val adapter= RecyclerAdapter()
    private var index=0

    var instance: SecondActivity? = null
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
        //Log.d("MyLog","Second")

        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rcView=findViewById<RecyclerView>(R.id.rcView)
        var i=findViewById<ConstraintLayout>(R.id.cvc)
        i.setOnClickListener(View.OnClickListener() {

            val intent4: Intent = Intent(it?.getContext(), ThirdActivity::class.java)
            intent4.putExtra("position", pos.posit)
            it?.context?.startActivity(intent4)

            // it.context.startActivity(intent)
            Log.d("MyLog", "SECOND")
        })
        binding.apply {

            rcView.layoutManager= LinearLayoutManager(this@SecondActivity)
            rcView.adapter = adapter
            for (x in 0..imageList.size-1){
            val item = RecyclerClass(imageList[index], titleList[index])
            adapter.addItem(item)
            index++

        }

        }

    }
fun send(){
    val intent4:Intent= Intent(this@SecondActivity,ThirdActivity::class.java)
    startActivity(intent4)

}
    override fun onClick(v: View?) {
        View.OnClickListener() {

            val intent4: Intent = Intent(v?.getContext(), ThirdActivity::class.java)
            intent4.putExtra("position", pos.posit)
            v?.context?.startActivity(intent4)

            // it.context.startActivity(intent)
            Log.d("MyLog", "SECOND")
        }
    }


}