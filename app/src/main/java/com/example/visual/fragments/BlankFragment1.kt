package com.example.visual.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.R
import com.example.visual.RecyclerAdapter
import com.example.visual.activity.SecondActivity
import com.example.visual.activity.filteredList
import com.example.visual.data.SecondActivityDataClass
import com.example.visual.databinding.FragmentBlank1Binding
import com.example.visual.model.RecyclerClass

var fragmentnum=0
class BlankFragment1 : Fragment(), RecyclerAdapter.OnItemClickListener {
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
    lateinit var binding:FragmentBlank1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentBlank1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentnum=1


        //var search=activity?.findViewById<EditText>(R.id.search)
        /**создание объекта [ArrayList]*/
        array = ArrayList()
        /**создаём объект класса*/
        model = SecondActivityDataClass()
        /**получаем list, присваиваем содержимое [recList]*/
        recList.addAll(model.getList())
        //var rcView=activity?.findViewById<RecyclerView>(R.id.rcView)
        binding.rcView.layoutManager = LinearLayoutManager(SecondActivity())
        /**установка адаптера*/
        binding.rcView.adapter = adapter
        binding.search.addTextChangedListener(
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

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        var fragment2=BlankFragment2()
        fragment2.arguments=bundle
        var fragmentTransaction=activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer,fragment2)
        fragmentTransaction?.commit()

    }


}