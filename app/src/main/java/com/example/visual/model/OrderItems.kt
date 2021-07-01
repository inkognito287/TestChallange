package com.example.visual.model

class OrderItems(
    val admission: OrderItem,
    val control: OrderItem,
    val executiveDepartment: OrderItem,
    val employeeOfTheContractorsDepartment: OrderItem,
    val connectedDepartment: OrderItem,
    val employeeOfTheConnectedDepartment: OrderItem,
    val requiredFor: OrderItem,
    val contactInTheCO: OrderItem
) {
   // var observableObj: ObservableField<OrderItems> = ObservableField()

}