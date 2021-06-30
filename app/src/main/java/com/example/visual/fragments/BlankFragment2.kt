package com.example.visual.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visual.RecyclerAdapter
import com.example.visual.activity.OrderActivity
import com.example.visual.data.ThirdActivityDataClass
import com.example.visual.databinding.FragmentBlank2Binding
import com.example.visual.model.RecyclerClass


class BlankFragment2 : Fragment(), RecyclerAdapter.OnItemClickListener {
    private var position = 0
    private var recList = ArrayList<RecyclerClass>()
    private val adapter = RecyclerAdapter(recList,this)
    lateinit var binding:FragmentBlank2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBlank2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentnum = 2
        val bundle = arguments
        val info = bundle?.getInt("position")
        position = info!!
        adapter.recList.clear()
        /**инициализация [layoutManager]*/
        binding.rcView2.layoutManager = LinearLayoutManager(activity)
        /**инициализация [adapter]*/
        binding.rcView2.adapter = adapter
        /**достаём элементы из класса [ThirdActivityDataClass]*/
        recList.addAll(ThirdActivityDataClass().getList()[position])
    }

    override fun onItemClick(position: Int) {
        /**запуск  [OrderActivity]*/
        val intent = Intent(activity, OrderActivity::class.java)
        /**пердаём позицию*/
        intent.putExtra("position", position)
        startActivity(intent)
    }
}