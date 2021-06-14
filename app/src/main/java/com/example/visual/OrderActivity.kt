package com.example.visual

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class OrderActivity : AppCompatActivity() {
    var index = 0
    val OTDEL = arrayOf(
        "TCP", "TDP", "UDP", "AMD", "UFC"
    )
    val employers = arrayOf(
        "Коротаев Александр", "Барбороскин Николай", "Куликовская Анастасия ", "Чурило Оксана", "Маршалов Пётр",
        "Касьян Кристина"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        var view = findViewById<View>(R.id.createList)
        var view2 = findViewById<View>(R.id.createList2)
        var view3 = findViewById<View>(R.id.createList3)
        view.setOnClickListener {
            createList(OTDEL)
        }
        view2.setOnClickListener {
            createList(employers)
        }
        view3.setOnClickListener {
            createList(OTDEL)
        }
    }
    fun bbw(v: View) {
//
//        var imgs=findViewById<LinearLayout>(R.id.imagesGallery)
//        var img=ImageView(this,set)
//        imgs.addView()
//        index++
//        Log.d("MyLog","click")
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 123)

    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            // var bmp=data?.extras?.get("data") as Bitmap
            var imgs = findViewById<LinearLayout>(R.id.imagesGallery)
            var img = ImageView(this)
            var divider = View(this)
            divider.setBackgroundColor(R.color.white)

            img.setImageURI(data?.data)
            img.scaleType = ImageView.ScaleType.CENTER_CROP
            imgs.addView(img, 350, 350)
            imgs.addView(divider, 5, 350)

            index++
            Log.d("MyLog", "click")

        }

    }

    fun createList(array:Array<String>) {
        var List = ListView(this)

        var params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        List.layoutParams = params
        var dad = findViewById<ConstraintLayout>(R.id.dad)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, array
        )
        List.adapter=adapter
        List.background = resources.getDrawable(R.drawable.notification)
        dad.addView(List)
    }
}


