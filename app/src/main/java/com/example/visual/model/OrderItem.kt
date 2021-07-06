package com.example.visual.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class OrderItem(
    val title: String,
    var image: ObservableInt,
    var visibility: ObservableInt,
    var text: ObservableField<String>,
    val array: Array<String>
)