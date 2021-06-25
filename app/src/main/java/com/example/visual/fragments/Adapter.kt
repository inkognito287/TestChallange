package com.example.visual.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapter(fragment:FragmentActivity):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
    return 4

    }

    override fun createFragment(position: Int): Fragment {
        val fragment=ImageFragment()
        fragment.arguments= Bundle().apply {
            putString(ARG_OBJECT,"https://get.wallhere.com/photo/2560x1600-px-lake-mountain-nature-1092998.jpg")
        }
        return fragment
    }
}