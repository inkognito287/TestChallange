package com.example.visual.activity

import android.R.attr.maxHeight
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.contains
import androidx.databinding.DataBindingUtil
import com.example.visual.R
import com.example.visual.controllers.FieldsOfOrderActivity
import com.example.visual.data.OrderActivityData
import com.example.visual.databinding.ActivityOrderBinding
import com.example.visual.databinding.ListBinding
import com.example.visual.databinding.PickDateTimeBinding
import com.example.visual.model.OrderItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.makeramen.roundedimageview.RoundedImageView
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import java.text.SimpleDateFormat
import java.util.*


class OrderActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var images: LinearLayout
    private lateinit var controller: FieldsOfOrderActivity

    private var listView: ListView? = null
    private var scrollview: ScrollView? = null

    private var bottomSheetIsExist:Boolean = false

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy")

    @SuppressLint("SimpleDateFormat")
    private val timeFormat = SimpleDateFormat("hh:mm")

    lateinit var binding: ActivityOrderBinding
    lateinit var context: Context

    private lateinit var bottomSheetDialog: BottomSheetDialog


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType", "SimpleDateFormat", "NewApi", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = FieldsOfOrderActivity()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        setContentView(binding.root)
        KeyboardVisibilityEvent.setEventListener(this) {

            if (!KeyboardVisibilityEvent.isKeyboardVisible(this))
                binding.commentary?.comment?.clearFocus()

        }
        binding.activity = this

        binding.addPhoto.root.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            openSomeActivityForResult()
        }
        val items = OrderActivityData().getItems()
        binding.orderItems = items

        items.admission.text.set(dateFormat.format(Date()) + " " + timeFormat.format(Date()))


        binding.buttonSave.root.setOnClickListener {
            val lottie = binding.lottie
            lottie?.visibility = View.VISIBLE
            binding.commentary?.comment?.clearFocus()
            lottie?.setAnimation(R.raw.loader)
            lottie?.loop(true)
            lottie?.playAnimation()
        }


        binding.createCalendar.root.setOnClickListener {
            binding.createCalendar.root.isClickable = false

            bottomSheetDialog = BottomSheetDialog(
                this@OrderActivity, R.style.BottomSheetDialog
            )
            val bottomSheetView =
                LayoutInflater.from(applicationContext).inflate(R.layout.pick_date_time, null)
            bottomSheetDialog.setContentView(bottomSheetView)

            val mBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
            mBehavior.peekHeight = maxHeight
            bottomSheetDialog.show()

            val pickDateTimeBinding = PickDateTimeBinding.inflate(layoutInflater)

            var pickDate: String = dateFormat.format(Date())
            var pickTime: String = timeFormat.format(Date())
            var text: String

            pickDateTimeBinding.calendar.setOnDateChangeListener { _, year: Int, month: Int, dayOfMonth: Int ->
                pickDate = "$dayOfMonth.$month.$year"
                text = "$pickDate $pickTime"
            }

            pickDateTimeBinding.timePicker1.setOnTimeChangedListener { _, hourOfDay, minute ->
                pickTime = "$hourOfDay:$minute"
                text = "$pickDate $pickTime"
            }
            text = "$pickDate $pickTime"
            bottomSheetDialog.setOnDismissListener {
                binding.createCalendar.root.isClickable = true
            }

            pickDateTimeBinding.submit.setOnClickListener {
                bottomSheetDialog.dismiss()
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                items.control.visibility.set(View.INVISIBLE)
                items.control.image.set(R.drawable.order_item_clicked)
                items.control.text.set(text)
            }
            bottomSheetDialog.setContentView(pickDateTimeBinding.root)
        }
    }


    override fun onBackPressed() {
        if (scrollview != null && binding.dad.contains(scrollview!!)) {
            binding.dad.removeView(scrollview)
        } else if (listView != null && binding.dad.contains(linearLayout))
            binding.dad.removeView(linearLayout)
        else
            super.onBackPressed()
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                images = binding.imagesGallery
                val img = RoundedImageView(this)
                img.setImageURI(data?.data)

                img.scaleType = ImageView.ScaleType.CENTER_CROP
                img.cornerRadius = 30F
                images.addView(img, 350, 350)

                val imgDelete = ImageView(this)
                images.addView(imgDelete, 40, 40)
                imgDelete.setImageResource(R.mipmap.ic_clear)

                val divider = View(this)
                divider.layoutParams = ConstraintLayout.LayoutParams(30, 1)
                images.addView(divider)

                imgDelete.setOnClickListener() {
                    images.removeView(img)
                    images.removeView(imgDelete)
                    images.removeView(divider)
                }
            }
        }

    private fun openSomeActivityForResult() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun createList(array: Array<String>, name: String, orderItem: OrderItem, v: View) {

        val bottomSheetDialog = BottomSheetDialog(
            this@OrderActivity, R.style.BottomSheetDialog
        )
        val bottomSheetView = LayoutInflater.from(applicationContext).inflate(R.layout.list, null)
        bottomSheetDialog.setContentView(bottomSheetView)
        val listBinding = ListBinding.inflate(layoutInflater)
        val behavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        behavior.peekHeight = maxHeight
        bottomSheetDialog.show()


        listBinding.textoffields.text = name
        listBinding.list.setSelector(R.drawable.list_item_select)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            array
        )
        listBinding.list.adapter = adapter
        listBinding.list.setOnItemClickListener { _, _, position, _ ->

            orderItem.text.set(orderItem.array[position])
            orderItem.visibility.set(View.INVISIBLE)
            orderItem.image.set(R.drawable.order_item_clicked)
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(listBinding.root)
        bottomSheetDialog.setOnDismissListener {
           bottomSheetIsExist = false
        }
    }

    fun back(v: View) {
        finish()
    }

    fun orderFieldClick(orderItem: OrderItem, v: View) {

        Log.d("MyLog", v.toString())
        if (!bottomSheetIsExist) {
            createList(orderItem.array, orderItem.title, orderItem, v)
            bottomSheetIsExist = true
        }

    }
    fun notImplemented(v:View){
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
    }
}

