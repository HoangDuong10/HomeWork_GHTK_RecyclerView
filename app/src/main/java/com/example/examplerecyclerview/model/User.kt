package com.example.examplerecyclerview.model

class User {
    var name: String? = null
    var image: Int? = null
    var phone : String?=null
    var statusOrder: String?=null
    var isStatusUser : Boolean = false
    var isStatusProduct : Boolean = false
    var address:String? = null
    var note: String? =null
    var money : String?=null
    constructor()
    constructor(
        name: String?,
        image: Int?,
        phone: String?,
        statusOrder: String?,
        isStatusUser: Boolean,
        isStatusProduct: Boolean,
        address: String?,
        note: String?,
        money: String?
    ) {
        this.name = name
        this.image = image
        this.phone = phone
        this.statusOrder = statusOrder
        this.isStatusUser = isStatusUser
        this.isStatusProduct = isStatusProduct
        this.address = address
        this.note = note
        this.money = money
    }


}