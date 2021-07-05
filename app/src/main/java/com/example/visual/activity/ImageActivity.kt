package com.example.visual.activity


import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.visual.R
import com.example.visual.fragments.Adapter

class ImageActivity : FragmentActivity() {
    private lateinit var imageAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val viewPager2: ViewPager2 = findViewById(R.id.viewpager2)
        /**устанавливаем количество подгружаемых страниц для viewpager*/
        viewPager2.offscreenPageLimit = 3
        imageAdapter = Adapter(this)
        viewPager2.adapter = imageAdapter
    }

    /**выход по нажатию стрелки*/
    fun back(v: View) {
        finish()
    }
}