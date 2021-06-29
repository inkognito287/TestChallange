package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.RecyclerAdapter
import com.example.visual.data.ThirdActivityDataClass
import com.example.visual.databinding.ActivityThirdBinding
import com.example.visual.model.RecyclerClass

/**
 *[RecyclerAdapter.OnItemClickListener] наследуемся от него для того чтобы осуществить свою
 * реализацию в данном активити
 */
class ThirdActivity :
    AppCompatActivity(),
    RecyclerAdapter.OnItemClickListener {
    private var position = 0
    /**переменная для биндинга в layout файле*/
    lateinit var binding: ActivityThirdBinding
    /**лист хранящий в себе объекты класса [RecyclerClass]*/
    private var recList = ArrayList<RecyclerClass>()
    /**переменная которая передаёт в [RecyclerAdapter]
     *@param recList список элементов класса [RecyclerClass]
     *@param this слушатель нажатий - [ThirdActivity]*/
    private val adapter = RecyclerAdapter(recList,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**создаём объект класса [ActivityThirdBinding]*/
        binding = ActivityThirdBinding.inflate(layoutInflater)
        /**устанавливаем корневой View в качестве content*/
        setContentView(binding.root)
        /**принимаем данные из предыдущего активити [SecondActivity]*/
        val intent = intent
        /**получаем позицию*/
        position = intent.getIntExtra("position", 0)
        /**применяем [binding] к блоку*/
        binding.apply {
            /**очищаем [recList]*/
            adapter.recList.clear()
            /**инициализация [layoutManager]*/
            rcView2.layoutManager = LinearLayoutManager(this@ThirdActivity)
            /**инициализация [adapter]*/
            rcView2.adapter = adapter
            /**достаём элементы из класса [ThirdActivityDataClass]*/
            recList.addAll(ThirdActivityDataClass().getList()[position])
        }
    }
    /**Реализация [onItemClick]*/
    override fun onItemClick(position: Int) {
        /**запуск  [OrderActivity]*/
        val intent = Intent(this@ThirdActivity, OrderActivity::class.java)
        /**пердаём позицию*/
        intent.putExtra("position", position)
        startActivity(intent)
    }
    /**завершение активити на нажатию на кнопку стрелочка*/
    fun back(v:View){
    finish()
    }
}