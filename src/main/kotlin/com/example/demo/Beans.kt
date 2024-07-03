package com.example.demo

data class StudentBean(var name: String = "", var note : Int = 0)
data class PlaneBean(val name: String, val id: String)
data class UserBean(var login:String = "", var password : String = "", var sessionId :String? = null)