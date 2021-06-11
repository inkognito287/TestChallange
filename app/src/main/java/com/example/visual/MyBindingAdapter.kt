package com.example.visual

import android.widget.ImageView
import androidx.databinding.BindingAdapter

class MyBindingAdapter {
    companion object{
        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }
    }
}