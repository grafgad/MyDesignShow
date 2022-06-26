package com.example.mydesignshow.model

data class MyDayClass(
    val id: Int,
    var viewType: Int,
    val firsPoint: Boolean,
    val time: String,
    val className: String,
    val teacher: String,
    val description: String,
    val classVideo: Boolean,
)