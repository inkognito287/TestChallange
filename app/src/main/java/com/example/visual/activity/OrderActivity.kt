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
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.airbnb.lottie.LottieAnimationView
import com.example.visual.R
import com.example.visual.controllers.FieldsOfOrderActivityController
import com.example.visual.databinding.ActivityOrderBinding
import com.example.visual.databinding.ListBinding
import com.example.visual.databinding.PickDateTimeBinding
import com.example.visual.model.OrderItem
import com.example.visual.model.OrderItems
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.makeramen.roundedimageview.RoundedImageView
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import java.text.SimpleDateFormat
import java.util.*


class OrderActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var images: LinearLayout
    private lateinit var controller: FieldsOfOrderActivityController
    private var listView: ListView? = null
    private var scrollview: ScrollView? = null
    private var paramsForImage: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    lateinit var binding: ActivityOrderBinding
    lateinit var context: Context
    lateinit var bindingVariableOfOrderItems: OrderItems

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType", "SimpleDateFormat", "NewApi", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = FieldsOfOrderActivityController()
        //binding = ActivityOrderBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        setContentView(binding.root)
        KeyboardVisibilityEvent.setEventListener(this) {

            if (!KeyboardVisibilityEvent.isKeyboardVisible(this))
                binding.commentary?.comment?.clearFocus()

        }
        binding.activity = this
        val admission = OrderItem(
            "Поступила:",
            ObservableInt(R.drawable.order_item_clicked),
            ObservableInt(View.INVISIBLE),
            ObservableField(),
            emptyArray()
        )
        val control = OrderItem(
            "Контроль",
            ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE),
            ObservableField(),
            emptyArray()
        )
        val executiveDepartment = OrderItem(
            "Отдел исполнитель:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getDepartment()
        )
        val employeeOfTheContractorsDepartment = OrderItem(
            "Сотрудник отдела исполнителя:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
        )
        val connectedDepartment = OrderItem(
            "Подключаемый отдел:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getDepartment()
        )
        val employeeOfTheConnectedDepartment = OrderItem(
            "Сотрудник подключаемого отдела:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
        )
        val requiredFor = OrderItem(
            "Требуется на:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getActions()
        )
        val contactInTheCO = OrderItem(
            "Контакт в ЦО:", ObservableInt(R.drawable.notification),
            ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
        )

        bindingVariableOfOrderItems = OrderItems(
            admission,
            control,
            executiveDepartment,
            employeeOfTheContractorsDepartment,
            connectedDepartment,
            employeeOfTheConnectedDepartment,
            requiredFor,
            contactInTheCO
        )
        binding.addPhoto?.root?.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            openSomeActivityForResult()
        }
        binding.orderItems = bindingVariableOfOrderItems
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        admission.text.set(dateFormat.format(Date()))
        binding.button2.setOnClickListener {
            binding.commentary?.comment?.clearFocus()
            val progress = LottieAnimationView(this)
            progress.setAnimation(R.raw.loader)
            progress.id = 1121
            progress.setPadding(0, 0, 0, 0)
            progress.layoutParams = paramsForImage
            binding.dad.addView(progress)
            ConstraintSet().apply {
                clone(binding.dad)
                connect(progress.id, ConstraintSet.TOP, binding.dad.id, ConstraintSet.TOP)
                connect(
                    progress.id,
                    ConstraintSet.BOTTOM,
                    binding.dad.id,
                    ConstraintSet.BOTTOM
                )
                connect(
                    progress.id,
                    ConstraintSet.RIGHT,
                    binding.dad.id,
                    ConstraintSet.RIGHT
                )
                connect(progress.id, ConstraintSet.LEFT, binding.dad.id, ConstraintSet.LEFT)
                applyTo(binding.dad)
            }
            progress.speed = 1.0f
            progress.repeatCount = 5
            progress.playAnimation()
            Thread {
                Thread.sleep(4000)
                runOnUiThread { binding.dad.removeView(progress) }
            }.start()
        }
        binding.createCalendar.root.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                this@OrderActivity, R.style.BottomSheetDialog
            )
            val bottomSheetView =
                LayoutInflater.from(applicationContext).inflate(R.layout.pick_date_time, null)
            bottomSheetDialog.setContentView(bottomSheetView)

            val mBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
            mBehavior.peekHeight = maxHeight
            bottomSheetDialog.show()
            val bi1 = PickDateTimeBinding.inflate(layoutInflater)
            //val calendarView = bottomSheetView.findViewById<CalendarView>(R.id.calendar)
           // val timePicker = bottomSheetView.findViewById<TimePicker>(R.id.timePicker1)
            val format = SimpleDateFormat("dd.MM.yyyy")
            val format2 = SimpleDateFormat("hh:mm")
            var pickDate: String = format.format(Date())
            var pickTime: String = format2.format(Date())
            var text: String
            bi1.calendar.setOnDateChangeListener { _, year: Int, month: Int, dayOfMonth: Int ->
                pickDate = "$dayOfMonth.$month.$year"
                text = "$pickDate $pickTime"
            }
            bi1.timePicker1.setOnTimeChangedListener { _, hourOfDay, minute ->
                pickTime = "$hourOfDay:$minute"
                text = "$pickDate $pickTime"
            }
            text = "$pickDate $pickTime"

            //val submit = bottomSheetView.findViewById<Button>(R.id.submit)
            bi1.submit.setOnClickListener {
                bottomSheetDialog.dismiss()
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                control.visibility.set(View.INVISIBLE)
                control.image.set(R.drawable.order_item_clicked)
                control.text.set(text)
            }
            bottomSheetDialog.setContentView(bi1.root)
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
                images=binding.imagesGallery
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
    private fun createList(array: Array<String>, name: String, orderItem: OrderItem) {
        val bottomSheetDialog = BottomSheetDialog(
            this@OrderActivity, R.style.BottomSheetDialog
        )
        val bottomSheetView = LayoutInflater.from(applicationContext).inflate(R.layout.list, null)
         bottomSheetDialog.setContentView(bottomSheetView)
        val bi = ListBinding.inflate(layoutInflater)
        val behavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        behavior.peekHeight = maxHeight
        bottomSheetDialog.show()

        /**        val listOfOrder = bottomSheetView.findViewById<ListView>(R.id.list)
         *
         */
        //val text = bottomSheetView.findViewById<TextView>(R.id.textoffields)
        bi.textoffields.text = name
        bi.list.setSelector(R.drawable.list_item_select)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            array
        )
        bi.list.adapter = adapter
        bi.list.setOnItemClickListener { _, _, position, _ ->
            orderItem.text.set(orderItem.array[position])
            orderItem.visibility.set(View.INVISIBLE)
            orderItem.image.set(R.drawable.order_item_clicked)
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(bi.root)
    }

    fun back(v: View) {
        finish()
    }

    fun orderFieldClick(orderItem: OrderItem) {

        createList(orderItem.array, orderItem.title, orderItem)

    }

}

