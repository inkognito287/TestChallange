package com.example.visual.data

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.visual.R
import com.example.visual.controllers.FieldsOfOrderActivityController
import com.example.visual.model.OrderItem
import com.example.visual.model.OrderItems

class OrderActivityData {
    private  val controller = FieldsOfOrderActivityController()
    private val admission = OrderItem(
        "Поступила:", ObservableInt(R.drawable.order_item_clicked),
        ObservableInt(View.INVISIBLE), ObservableField(), emptyArray()
    )
    private val control = OrderItem(
        "Контроль", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), emptyArray()
    )
    private val executiveDepartment = OrderItem(
        "Отдел исполнитель:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getDepartment()
    )
    private val employeeOfTheContractorsDepartment = OrderItem(
        "Сотрудник отдела исполнителя:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
    )
    private val connectedDepartment = OrderItem(
        "Подключаемый отдел:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getDepartment()
    )
    private val employeeOfTheConnectedDepartment = OrderItem(
        "Сотрудник подключаемого отдела:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
    )
    private val requiredFor = OrderItem(
        "Требуется на:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getActions()
    )
   private val contactInTheCO = OrderItem(
        "Контакт в ЦО:", ObservableInt(R.drawable.notification),
        ObservableInt(View.VISIBLE), ObservableField(), controller.getEmployers()
    )

   var bindingVariableOfOrderItems = OrderItems(
    admission,
    control,
    executiveDepartment,
    employeeOfTheContractorsDepartment,
    connectedDepartment,
    employeeOfTheConnectedDepartment,
    requiredFor,
    contactInTheCO
    )


    fun getItems(): OrderItems {

        return bindingVariableOfOrderItems
    }

}