package com.example.visual.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.data.SecondActivityDataClass
import com.example.visual.databinding.ActivitySecondBinding
import com.example.visual.fragments.BlankFragment1
import com.example.visual.model.RecyclerClass
import java.util.*
import kotlin.collections.ArrayList

/**
 * [filteredList] глобальная переменная хранящая в себе отфильтрованный список, после
 * использования поиска по ключевым словам
 */
var filteredList: ArrayList<RecyclerClass> = ArrayList()

/**
 *[RecyclerAdapter.OnItemClickListener] наследуемся от него для того чтобы осуществить свою
 * реализацию в данном активити
 */
class SecondActivity :
    AppCompatActivity(),
    RecyclerAdapter.OnItemClickListener {
    /**переменная для биндинга в layout файле*/
    lateinit var binding: ActivitySecondBinding
    /**вспомогательный массив для хранения позиций item после фильтрации*/
    private lateinit var array: MutableList<Int>
    /**экземпляр класса [SecondActivityDataClass] черезе который достаётся информация*/
    private lateinit var model: SecondActivityDataClass
    /**лист хранящий в себе объекты класса [RecyclerClass]*/
    private var recList = ArrayList<RecyclerClass>()
    /**переменная которая передаёт в [RecyclerAdapter]
     *@param recList список элементов класса [RecyclerClass]
     *@param this слушатель нажатий - [SecondActivity]*/
    private val adapter = RecyclerAdapter(recList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**создание объекта класса [ActivitySecondBinding]*/
        binding = ActivitySecondBinding.inflate(layoutInflater)
        /**установка корневого View в качестве ContentView*/
        setContentView(binding.root)
        var fragment1=BlankFragment1()
        var fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment1)
        fragmentTransaction.commit()
        var search=findViewById<EditText>(R.id.search)
        /**создание объекта [ArrayList]*/
        array = ArrayList()
        /**применяем [binding] ко всему блоку*/
        binding.apply {
            /**создаём объект класса*/
            model = SecondActivityDataClass()
            /**получаем list, присваиваем содержимое [recList]*/
            recList.addAll(model.getList())
            /**инициализация layoutManager*/

            var rcView=findViewById<RecyclerView>(R.id.rcView)
            rcView.layoutManager = LinearLayoutManager(this@SecondActivity)
            /**установка адаптера*/
            rcView.adapter = adapter
            /**устанавливаем слушатель изменения текста*/

            search.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        /**во время изменения текста вызываем функцию [filter]*/
                        filter(s.toString())
                    }
                }
            )
        }
    }
/**
 * @param text текст по которому производится фильтрация*/
    fun filter(text: String) {
        /**очищаем отфильтрованный список*/
        filteredList.clear()
        /**очищаем вспомогательный массив*/
        array.clear()
        /**цикл с каждым последующим элементов [d] в списке [recList]*/
        for ((index, d) in recList.withIndex()) {
            /**если элемент [d.title] в малом регистре содержит [text] в малом регистре*/
            if (d.title.lowercase().contains(text.lowercase())) {
                /**то вызываем функцию [filteredList]*/
                filteredList.add(d)
                /**запоминаем позицию элемента во вспомогательном массиве*/
                array.add(index)
            }
        }
    /**присваиваем отфильтрованный список в [recList]*/
        adapter.filterList(filteredList)
    }
    /**функция заупска [ThirdActivity]*/
    fun send() {
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        startActivity(intent)
    }

    /**перегружаем [onItemClick]
     * @param position позиция нажатого элемента*/
    override fun onItemClick(position: Int) {
        /**запуск [ThirdActivity]*/
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        /**если форма поиска пустая, то в качестве позиции передаём позицию из
         * вспомогательного массива*/
        var search=findViewById<EditText>(R.id.search)
        if (search.text.toString() != "")
            intent.putExtra("position", array[position])
        /**в противном случае саму позицию элемента*/
        else intent.putExtra("position", position)

        startActivity(intent)
    }
    /**завершение активити на нажатию на кнопку стрелочка*/
    fun back(v: View) {
        finish()
    }

}