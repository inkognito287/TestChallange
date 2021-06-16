package com.example.visual

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.contains
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieAnimationView
import com.example.visual.databinding.ActivityOrderBinding


class OrderActivity : AppCompatActivity() {
    lateinit var dad: ConstraintLayout
    lateinit var linearLayout:LinearLayout
    var List:ListView?=null
    lateinit var imgs:LinearLayout
    var scrollview: ScrollView? =null
    var index = 0

    lateinit var context:Context
    val OTDEL = arrayOf(
        "TCP", "TDP", "UDP", "AMD", "UFC"
    )
    var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT / 2
    )
    var paramsForTimePicker:LinearLayout.LayoutParams= LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    var params2: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    var paramsForImage: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    val dela = arrayOf("Согласование", "Разрешение", "Управление")
    val employers = arrayOf(
        "Коротаев Александр",
        "Барбороскин Николай",
        "Куликовская Анастасия ",
        "Чурило Оксана",
        "Маршалов Пётр",
        "Касьян Кристина"
    )
    var textArr = arrayOf("1", "2", "3", "4", "5", "6", "7")
    var titleArr = arrayOf(
        "Контроль",
        "Отдел исполнитель",
        "Сотрудник отдела исполнителя",
        "Подключенный отдел",
        "Сотрудники...",
        "Требуются на",
        "Контакт в ЦО"
    )
    lateinit var Binding: ActivityOrderBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        Binding.information2 = Information2(
            titleArr,
            textArr
        )
        dad = findViewById<ConstraintLayout>(R.id.dad)
        var save=findViewById<Button>(R.id.button2)
        var comment=findViewById<EditText>(R.id.comment)
        save.setOnClickListener(){
//            Log.d("MyLog",comment.text.toString()+imgs[0])
            var progress=LottieAnimationView(this)
            progress.setAnimation(R.raw.loader)
            progress.setId(1121)
            progress.setPadding(0,0,0,0)
            progress.layoutParams=paramsForImage
            dad.addView(progress)
            var constraintSet=ConstraintSet()
            constraintSet.clone(dad)
            constraintSet.connect(progress.id,ConstraintSet.TOP,dad.id,ConstraintSet.TOP)
            constraintSet.connect(progress.id,ConstraintSet.BOTTOM,dad.id,ConstraintSet.BOTTOM)
            constraintSet.connect(progress.id,ConstraintSet.RIGHT,dad.id,ConstraintSet.RIGHT)
            constraintSet.connect(progress.id,ConstraintSet.LEFT,dad.id,ConstraintSet.LEFT)
            constraintSet.applyTo(dad)
            var isCheckedDone=true
            if(isCheckedDone) {
                progress.setSpeed(-1.0f)
                progress.repeatCount=5
                progress.playAnimation()
               Thread(Runnable {
                   Thread.sleep(4000)
                   runOnUiThread { dad.removeView(progress) }
               }).start()




            }


            //val progressAnim=AnimationUtils.loadAnimation(this,R.anim.progress)
            //progress.startAnimation(progressAnim)



        }
        var view = findViewById<View>(R.id.createList)
        var view2 = findViewById<View>(R.id.createList2)
        var view3 = findViewById<View>(R.id.createList3)
        var view4 = findViewById<View>(R.id.createList4)
        var view5 = findViewById<View>(R.id.createList5)
        var view6 = findViewById<View>(R.id.createList6)
        var calendar = findViewById<View>(R.id.createCalendar)
        calendar.setOnClickListener {
            createCalendar()
            // createTimePicker()
        }
        view.setOnClickListener {
            createList(OTDEL, 1)
        }
        view2.setOnClickListener {
            createList(employers, 2)
        }
        view3.setOnClickListener {
            createList(OTDEL, 3)
        }
        view4.setOnClickListener {
            createList(employers, 4)
        }
        view5.setOnClickListener {
            createList(OTDEL, 5)
        }
        view6.setOnClickListener {
            createList(employers, 6)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun createCalendar() {
        var pickDate=""
        var pickTime=""

        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = params2

        var calendarView = CalendarView(this)
        //dad.addView(calendarView)
        calendarView.setBackgroundResource(R.color.white)
        calendarView.alpha = 1.0F
        calendarView.layoutParams = paramsForTimePicker
        calendarView.setOnDateChangeListener (CalendarView.OnDateChangeListener(){
            view: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
               pickDate= dayOfMonth.toString()+"/"+month+"/"+year

        })


        var timePicker = TimePicker(this)

        //timePicker.addc
        // dad.addView(timePicker)
        timePicker.layoutParams = paramsForTimePicker
        timePicker.setBackgroundResource(R.color.white)
        timePicker.setOnTimeChangedListener(TimePicker.OnTimeChangedListener(){
           view, hourOfDay, minute ->
           pickTime=hourOfDay.toString()+":"+minute
        })
        var submit=Button(this)
        submit.layoutParams=paramsForTimePicker
        submit.text="Подтвердить"
        linearLayout.addView(
            calendarView, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
        linearLayout.addView(
            timePicker, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
        linearLayout.addView(
            submit, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
         scrollview=ScrollView(this)
        scrollview!!.setBackgroundResource(R.color.white)
        scrollview!!.layoutParams=params2
        scrollview!!.addView(linearLayout)
        submit.setOnClickListener{
            dad.removeView(scrollview)

            textArr[0] =pickDate+" "+pickTime
            Binding.information2 = Information2(titleArr, textArr)

        }
        // dad.addView(linearLayout)
        dad.addView(scrollview)
        val calendarAnim=AnimationUtils.loadAnimation(this,R.anim.calendar_view)
        scrollview!!.startAnimation(calendarAnim)
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

    override fun onBackPressed() {

        if (scrollview!=null && dad.contains(scrollview!!))
        dad.removeView(scrollview)
        else  if (List!=null &&dad.contains(List!!))
            dad.removeView(List)
        else
        super.onBackPressed()

    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if(data!=null){
            // var bmp=data?.extras?.get("data") as Bitmap
             imgs = findViewById<LinearLayout>(R.id.imagesGallery)
            var img = ImageView(this)
            var divider = View(this)
            divider.setBackgroundResource(R.color.black)

            img.setImageURI(data?.data)
            img.scaleType = ImageView.ScaleType.CENTER_CROP
            imgs.addView(img, 350, 350)
            imgs.addView(divider, 5, 350)

            index++
            Log.d("MyLog", "click")
            }
        }

    }

    fun createList(array: Array<String>, int: Int) {
         List = ListView(this)

        var params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        List!!.layoutParams = params

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, array
        )
        List!!.adapter = adapter
        List!!.background = resources.getDrawable(R.drawable.notification)
        List!!.setOnItemClickListener { parent, view, position, id ->
            Log.d("MyLog", adapter.getItem(position).toString())
            // val Binding: ActivityOrderBinding? = DataBindingUtil.setContentView(this, R.layout.activity_order)
            textArr[int] = adapter.getItem(position).toString()
            Binding.information2 = Information2(titleArr, textArr)
            dad.removeView(List)

        }
        dad.addView(List)
    }
}






