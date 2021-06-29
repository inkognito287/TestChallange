package com.example.visual.activity

import android.R.attr.maxHeight
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.contains
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieAnimationView
import com.example.visual.R
import com.example.visual.controllers.FieldsOfOrderActivityController
import com.example.visual.databinding.ActivityOrderBinding
import com.example.visual.model.Information2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.makeramen.roundedimageview.RoundedImageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class OrderActivity : AppCompatActivity() {
    private lateinit var dadConstraintLayout: ConstraintLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var images: LinearLayout
    private lateinit var controller: FieldsOfOrderActivityController
    private var listView: ListView? = null
    private var scrollview: ScrollView? = null
    lateinit var context: Context
    private var paramsForImage: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    private var textArr = arrayOfNulls<String>(8)
    lateinit var binding: ActivityOrderBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType", "SimpleDateFormat", "NewApi", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = FieldsOfOrderActivityController(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        binding.information2 = Information2(
            controller.getTitleOfOrderFields(),
            textArr,
            controller.getImageOfOrderFields(),
            controller.getVisibilityOfIcon()
        )
        dadConstraintLayout = findViewById(R.id.dad)
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        textArr[7] = dateFormat.format(Date())
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

        val view1 = findViewById<View>(R.id.createList)
        val view2 = findViewById<View>(R.id.createList2)
        val view3 = findViewById<View>(R.id.createList3)
        val view4 = findViewById<View>(R.id.createList4)
        val view5 = findViewById<View>(R.id.createList5)
        val view6 = findViewById<View>(R.id.createList6)
        val calendar = findViewById<View>(R.id.createCalendar)
        val listView=ArrayList<android.view.View>()
        with(listView){
            add(view1)
            add(view2)
            add(view3)
            add(view4)
            add(view5)
            add(view6)
        }
            calendar.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                this@OrderActivity, R.style.BottomSheetDialog
            )
            val bottomSheetView =
                LayoutInflater.from(applicationContext).inflate(R.layout.pick_date_time, null)
            bottomSheetDialog.setContentView(bottomSheetView)
            val mBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
            mBehavior.peekHeight = maxHeight
            bottomSheetDialog.show()
            val calendarView = bottomSheetView.findViewById<CalendarView>(R.id.calendar)
            val timePicker = bottomSheetView.findViewById<TimePicker>(R.id.timePicker1)
            val format = SimpleDateFormat("dd.MM.yyyy")
            val format2 = SimpleDateFormat("hh:mm")
            var pickDate: String = format.format(Date())
            var pickTime: String = format2.format(Date())
            var text: String
            calendarView.setOnDateChangeListener { _, year: Int, month: Int, dayOfMonth: Int ->
                pickDate = "$dayOfMonth.$month.$year"
                text = "$pickDate $pickTime"
            }
            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                pickTime = "$hourOfDay:$minute"
                text = "$pickDate $pickTime"
            }
            text = "$pickDate $pickTime"

            val submit = bottomSheetView.findViewById<Button>(R.id.submit)
            submit.setOnClickListener {
                bottomSheetDialog.dismiss()
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                controller.setImageofOrderFields(R.drawable.order_item_clicked,0)
                controller.setVisibilityOfIcon(0)

                textArr[0] = text
                binding.information2 = Information2(
                    controller.getTitleOfOrderFields(),
                    textArr,
                    controller.getImageOfOrderFields(),
                   controller.getVisibilityOfIcon()
                )
            }
        }
        for((posit,View) in listView.withIndex()){
            View.setOnClickListener {
                var position=posit+1
                createList(controller.chooseArray(posit), position,controller.getTitleOfOrderFields()[position])
                controller.setVisibilityOfIcon(position)
                controller.setImageofOrderFields(R.drawable.order_item_clicked,position)
                binding.information2 = Information2(
                    controller.getTitleOfOrderFields(),
                    textArr,
                    controller.getImageOfOrderFields(),
                    controller.getVisibilityOfIcon()
                )
            }
        }
    }

    fun addPhoto(v: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        openSomeActivityForResult()
    }

    override fun onBackPressed() {
        if (scrollview != null && dadConstraintLayout.contains(scrollview!!)) {
            dadConstraintLayout.removeView(scrollview)
        } else if (listView != null && dadConstraintLayout.contains(linearLayout))
            dadConstraintLayout.removeView(linearLayout)
        else
            super.onBackPressed()
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                images = findViewById(R.id.imagesGallery)
                val img = RoundedImageView(this)
                img.setImageURI(data?.data)
                img.scaleType = ImageView.ScaleType.CENTER_CROP
                img.cornerRadius = 30F
                images.addView(img, 350, 350)
                val divider = View(this)
                divider.layoutParams = ConstraintLayout.LayoutParams(30, 1)
                images.addView(divider)
            }
        }

    private fun openSomeActivityForResult() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }
    @SuppressLint("InflateParams")
    private fun createList(array: Array<String>, int: Int, name:String) {
        val bottomSheetDialog = BottomSheetDialog(
            this@OrderActivity, R.style.BottomSheetDialog
        )
        val bottomSheetView = LayoutInflater.from(applicationContext).inflate(R.layout.list, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        val behavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        behavior.peekHeight = maxHeight
        bottomSheetDialog.show()
        val listOfOrder = bottomSheetView.findViewById<ListView>(R.id.list)
        val text=bottomSheetView.findViewById<TextView>(R.id.textoffields)
        text.text= "$name:"
        listOfOrder.setSelector(R.drawable.list_item_select)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            array
        )
        listOfOrder.adapter = adapter
        listOfOrder.setOnItemClickListener { _, _, position, _ ->
            textArr[int] = array[position]
            binding.information2 = Information2(
                controller.getTitleOfOrderFields(),
                textArr,
                controller.getImageOfOrderFields(),
                controller.getVisibilityOfIcon()
            )
            bottomSheetDialog.dismiss()
        }
    }
    fun back(v:View){
        finish()
    }
}

