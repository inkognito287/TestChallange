package com.example.visual.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.visual.data.ImageActivityDataClass

class Adapter(fragment:FragmentActivity):FragmentStateAdapter(fragment) {
    /**
     * @return количество картинок хранящихся в классе
     * */
    override fun getItemCount(): Int {
    return ImageActivityDataClass().getList().size
    }

    override fun createFragment(position: Int): Fragment {
        /**объект класса [ImageFragment]*/
        val fragment=ImageFragment()
        /**получить все объекты*/
        val array=ImageActivityDataClass().getList()
        /**вкладываем аргументы с ссылкой на картинку*/
        fragment.arguments= Bundle().apply {
            putString(ARG_OBJECT,array[position].url)
        }
        /**возвращаем фрагмент*/
        return fragment
    }
}