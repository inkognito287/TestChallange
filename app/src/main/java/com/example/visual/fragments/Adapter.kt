package com.example.visual.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.visual.data.ImageActivityDataClass

class Adapter(fragment:FragmentActivity):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
    return 4

    }

    override fun createFragment(position: Int): Fragment {
        val fragment=ImageFragment()
        val array=ImageActivityDataClass().getList()
        fragment.arguments= Bundle().apply {
            putString(ARG_OBJECT,array[position].url)
        }
        return fragment
    }
}