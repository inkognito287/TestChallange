package com.example.visual

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.view.contains
import androidx.core.view.marginRight
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.lottie.LottieAnimationView
import com.example.visual.Controllers.FieldsOfOrderActivityController
import com.example.visual.dataClasses.Information2
import com.example.visual.databinding.ActivityOrderBinding

import com.makeramen.roundedimageview.RoundedImageView


class OrderActivity : AppCompatActivity() {
    private lateinit var dadConstraintLayout: ConstraintLayout
    private lateinit var linearLayout:LinearLayout
    private var listView:ListView?=null
    private lateinit var images:LinearLayout
    private var scrollview: ScrollView? =null
    private var index = 0
    lateinit var context:Context
    private lateinit var controller:FieldsOfOrderActivityController
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


    private var textArr = arrayOf("", "", "", "", "", "", "")

    lateinit var Binding: ActivityOrderBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller= FieldsOfOrderActivityController(this)
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        Binding.information2 = Information2(
            controller.getTitleOfOrderFields(),
            textArr
        )

        dadConstraintLayout = findViewById<ConstraintLayout>(R.id.dad)
        var save=findViewById<Button>(R.id.button2)
        var comment=findViewById<EditText>(R.id.comment)
        save.setOnClickListener(){
//            Log.d("MyLog",comment.text.toString()+imgs[0])
            var progress=LottieAnimationView(this)
            progress.setAnimation(R.raw.loader)
            progress.setId(1121)
            progress.setPadding(0,0,0,0)
            progress.layoutParams=paramsForImage
            dadConstraintLayout.addView(progress)
            var constraintSet=ConstraintSet()
            constraintSet.clone(dadConstraintLayout)
            constraintSet.connect(progress.id,ConstraintSet.TOP,dadConstraintLayout.id,ConstraintSet.TOP)
            constraintSet.connect(progress.id,ConstraintSet.BOTTOM,dadConstraintLayout.id,ConstraintSet.BOTTOM)
            constraintSet.connect(progress.id,ConstraintSet.RIGHT,dadConstraintLayout.id,ConstraintSet.RIGHT)
            constraintSet.connect(progress.id,ConstraintSet.LEFT,dadConstraintLayout.id,ConstraintSet.LEFT)
            constraintSet.applyTo(dadConstraintLayout)
            var isCheckedDone=true
            if(isCheckedDone) {
                progress.setSpeed(-1.0f)
                progress.repeatCount=5
                progress.playAnimation()
               Thread(Runnable {
                   Thread.sleep(4000)
                   runOnUiThread { dadConstraintLayout.removeView(progress) }
               }).start()
            }
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
            createList(controller.getDepartment(), 1)
        }
        view2.setOnClickListener {
            createList(controller.getEmployers(), 2)
        }
        view3.setOnClickListener {
            createList(controller.getDepartment(), 3)
        }
        view4.setOnClickListener {
            createList(controller.getEmployers(), 4)
        }
        view5.setOnClickListener {
            createList(controller.getActions(), 5)
        }
        view6.setOnClickListener {
            createList(controller.getEmployers(), 6)
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
            dadConstraintLayout.removeView(scrollview)

            textArr[0] =pickDate+" "+pickTime
            Binding.information2 = Information2(controller.getTitleOfOrderFields(), textArr)

        }
        // dad.addView(linearLayout)
        dadConstraintLayout.addView(scrollview)
        val calendarAnim=AnimationUtils.loadAnimation(this,R.anim.calendar_view)
        scrollview!!.startAnimation(calendarAnim)
    }

    fun bbw(v: View) {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 123)

    }

    override fun onBackPressed() {

        if (scrollview!=null && dadConstraintLayout.contains(scrollview!!))
        dadConstraintLayout.removeView(scrollview)
        else  if (listView!=null &&dadConstraintLayout.contains(listView!!))
            dadConstraintLayout.removeView(listView)
        else
        super.onBackPressed()

    }
    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if(data!=null){
            // var bmp=data?.extras?.get("data") as Bitmap
             images = findViewById<LinearLayout>(R.id.imagesGallery)
            var img = RoundedImageView(this)
            //img.setBackgroundResource(R.drawable.button2)
           // img.scaleType = ImageView.ScaleType.CENTER_CROP
            img.setImageURI(data?.data)
            img.scaleType=ImageView.ScaleType.CENTER_CROP
            img.cornerRadius=30F
            images.addView(img, 350, 350)
            index++
            Log.d("MyLog", "click")
            }
        }

    }

    fun createList(array: Array<String>, int: Int) {
//         listView = ListView(this)
//        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        listView!!.layoutParams = params
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1, array
//        )
//        listView!!.adapter = adapter
//        listView!!.background = resources.getDrawable(R.drawable.notification)
//        listView!!.setOnItemClickListener { parent, view, position, id ->
//            Log.d("MyLog", adapter.getItem(position).toString())
//            textArr[int] = adapter.getItem(position).toString()
//            Binding.information2 = Information2(controller.getTitleOfOrderFields(), textArr)
//            dadConstraintLayout.removeView(listView)
//        }
        var m=controller.createList(array,textArr)
        m.setOnItemClickListener(){parent, view, position, id ->
            textArr[int]=array[position]
            Binding.information2 = Information2(controller.getTitleOfOrderFields(), textArr)
            dadConstraintLayout.removeView(m)
        }
        dadConstraintLayout.addView(m)

    }
}






