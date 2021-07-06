package com.example.visual.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.visual.data.ImageActivityData
import com.example.visual.databinding.ActivityImageBinding

class Adapter(fragment:FragmentActivity,
    /**
     * @return количество картинок хранящихся в классе
     * */
              var binding: ActivityImageBinding
):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {

    return ImageActivityData().getList().size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ImageFragment(binding)
        /**получить все объекты*/
        val array = ImageActivityData().getList()

        /**вкладываем аргументы с ссылкой на картинку*/
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT, array[position].url)
           }

        /**возвращаем фрагмент*/
        return fragment
    }
}