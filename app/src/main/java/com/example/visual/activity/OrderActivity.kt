package com.example.visual.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.contains
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieAnimationView
import com.example.visual.R
import com.example.visual.controllers.FieldsOfOrderActivityController
import com.example.visual.dataClasses.Information2
import com.example.visual.databinding.ActivityOrderBinding
import com.makeramen.roundedimageview.RoundedImageView
import java.text.SimpleDateFormat
import java.util.*


class OrderActivity : AppCompatActivity() {
    private var imageOfOrderFields = arrayOf(
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.notification,
        R.drawable.order_item_clicked
    )
    private var visibilityOfIcon = arrayOf(
        View.VISIBLE,
        View.VISIBLE,
        View.VISIBLE,
        View.VISIBLE,
        View.VISIBLE,
        View.VISIBLE,
        View.VISIBLE,
        View.INVISIBLE
    )
    private lateinit var dadConstraintLayout: ConstraintLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var timePicker: TimePicker
    private lateinit var images: LinearLayout
    private lateinit var controller: FieldsOfOrderActivityController
    private var listView: ListView? = null
    private var scrollview: ScrollView? = null
    lateinit var context: Context
    private var paramsForTimePicker: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    private var paramsForLinearLayout: ConstraintLayout.LayoutParams =
        ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    private var paramsForImage: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    private var textArr = arrayOfNulls<String>(8)
    lateinit var binding: ActivityOrderBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = FieldsOfOrderActivityController(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        binding.information2 = Information2(
            controller.getTitleOfOrderFields(),
            textArr,
            imageOfOrderFields,
            visibilityOfIcon
        )
        dadConstraintLayout = findViewById(R.id.dad)
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        textArr[7]= dateFormat.format(Date())
        val save = findViewById<Button>(R.id.button2)
        save.setOnClickListener {
            val progress = LottieAnimationView(this)
            progress.setAnimation(R.raw.loader)
            progress.id = 1121
            progress.setPadding(0, 0, 0, 0)
            progress.layoutParams = paramsForImage
            dadConstraintLayout.addView(progress)
            ConstraintSet().apply {
                clone(dadConstraintLayout)
                connect(progress.id, ConstraintSet.TOP, dadConstraintLayout.id, ConstraintSet.TOP)
                connect(
                    progress.id,
                    ConstraintSet.BOTTOM,
                    dadConstraintLayout.id,
                    ConstraintSet.BOTTOM
                )
                connect(
                    progress.id,
                    ConstraintSet.RIGHT,
                    dadConstraintLayout.id,
                    ConstraintSet.RIGHT
                )
                connect(progress.id, ConstraintSet.LEFT, dadConstraintLayout.id, ConstraintSet.LEFT)
                applyTo(dadConstraintLayout)
            }

            progress.speed = 1.0f
            progress.repeatCount = 5
            progress.playAnimation()
            Thread {
                Thread.sleep(4000)
                runOnUiThread { dadConstraintLayout.removeView(progress) }
            }.start()
        }

        val view = findViewById<View>(R.id.createList)
        val view2 = findViewById<View>(R.id.createList2)
        val view3 = findViewById<View>(R.id.createList3)
        val view4 = findViewById<View>(R.id.createList4)
        val view5 = findViewById<View>(R.id.createList5)
        val view6 = findViewById<View>(R.id.createList6)
        val calendar = findViewById<View>(R.id.createCalendar)
        val calendarNow=findViewById<View>(R.id.now)

        calendar.setOnClickListener {
            createCalendar()
            imageOfOrderFields[0] = R.drawable.order_item_clicked
            visibilityOfIcon[0] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        view.setOnClickListener {
            createList(controller.getDepartment(), 1)
            imageOfOrderFields[1] = R.drawable.order_item_clicked
            visibilityOfIcon[1] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        view2.setOnClickListener {
            createList(controller.getEmployers(), 2)
            imageOfOrderFields[2] = R.drawable.order_item_clicked
            visibilityOfIcon[2] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        view3.setOnClickListener {
            createList(controller.getDepartment(), 3)
            imageOfOrderFields[3] = R.drawable.order_item_clicked
            visibilityOfIcon[3] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        view4.setOnClickListener {
            createList(controller.getEmployers(), 4)
            imageOfOrderFields[4] = R.drawable.order_item_clicked
            visibilityOfIcon[4] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }

        view5.setOnClickListener {
            createList(controller.getActions(), 5)
            imageOfOrderFields[5] = R.drawable.order_item_clicked
            visibilityOfIcon[5] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        view6.setOnClickListener {
            createList(controller.getEmployers(), 6)
            imageOfOrderFields[6] = R.drawable.order_item_clicked
            visibilityOfIcon[6] = View.INVISIBLE
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor", "SimpleDateFormat")
    fun createCalendar() {
        var pickDate: String
        var pickTime: String
        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = paramsForLinearLayout
        val calendarView = CalendarView(this)
        calendarView.setBackgroundResource(R.color.white)
        calendarView.alpha = 1.0F
        calendarView.layoutParams = paramsForTimePicker
        val format = SimpleDateFormat("dd.MM.yyyy")
        pickDate = format.format(calendarView.date).toString()

        calendarView.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            pickDate = "$dayOfMonth.$month.$year"
        }
        timePicker = TimePicker(this)
        pickTime = timePicker.hour.toString() + ":" + timePicker.minute.toString()
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            pickTime = "$hourOfDay:$minute"
        }
        timePicker.visibility = View.VISIBLE
        timePicker.layoutParams = paramsForTimePicker
        timePicker.setBackgroundResource(R.color.white)
        val submit = Button(this)
        submit.layoutParams = paramsForTimePicker
        submit.text = "Подтвердить"
        linearLayout.addView(
            calendarView,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
        linearLayout.addView(
            timePicker,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
        linearLayout.addView(
            submit,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
        )
        scrollview = ScrollView(this)
        scrollview!!.setBackgroundResource(R.color.white)
        scrollview!!.layoutParams = paramsForLinearLayout
        scrollview!!.addView(linearLayout)
        submit.setOnClickListener {
            dadConstraintLayout.removeView(scrollview)
            textArr[0] = "$pickDate $pickTime"
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
        }
        dadConstraintLayout.addView(scrollview)
        val calendarAnim = AnimationUtils.loadAnimation(this, R.anim.calendar_view)
        scrollview!!.startAnimation(calendarAnim)
    }

    fun addPhoto(v: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 123)
    }

    override fun onBackPressed() {
        if (scrollview != null && dadConstraintLayout.contains(scrollview!!)) {
            dadConstraintLayout.removeView(scrollview)
        } else if (listView != null && dadConstraintLayout.contains(linearLayout))
            dadConstraintLayout.removeView(linearLayout)
        else
            super.onBackPressed()
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (data != null) {
                images = findViewById<LinearLayout>(R.id.imagesGallery)
                val img = RoundedImageView(this)
                img.setImageURI(data.data)
                img.scaleType = ImageView.ScaleType.CENTER_CROP
                img.cornerRadius = 30F
                images.addView(img, 350, 350)
                val divider = View(this)
                divider.layoutParams = ConstraintLayout.LayoutParams(30, 1)
                images.addView(divider)
            }
        }
    }

    private fun createList(array: Array<String>, int: Int) {
        listView = controller.createList(array, textArr)
        listView!!.setOnItemClickListener { _, _, position, _ ->
            textArr[int] = array[position]
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                imageOfOrderFields,
                visibilityOfIcon
            )
            dadConstraintLayout.removeView(linearLayout)
        }
        val view = View(this)
        view.setBackgroundResource(R.color.black)
        view.alpha = 0.7f
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)
        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = paramsForLinearLayout
        linearLayout.addView(view)
        linearLayout.addView(listView)
        dadConstraintLayout.addView(linearLayout)
        val calendarAnim = AnimationUtils.loadAnimation(this, R.anim.calendar_view)
        listView!!.startAnimation(calendarAnim)
        val blackViewAnim = AnimationUtils.loadAnimation(this, R.anim.black_view)
        view.startAnimation(blackViewAnim)
    }
}