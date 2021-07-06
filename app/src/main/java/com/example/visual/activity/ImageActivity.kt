package com.example.visual.activity


import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.visual.databinding.ActivityImageBinding
import com.example.visual.fragments.Adapter

class ImageActivity : FragmentActivity() {
    private lateinit var imageAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_image)

        val binding: ActivityImageBinding =
            ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**устанавливаем количество подгружаемых страниц для viewpager*/
        binding.viewpager2.offscreenPageLimit = 3
        imageAdapter = Adapter(this,binding)
        binding.viewpager2.adapter = imageAdapter
    }

    /**выход по нажатию стрелки*/
    fun back(v: View) {
        finish()
    }
}