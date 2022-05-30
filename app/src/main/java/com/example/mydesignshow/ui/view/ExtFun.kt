package com.example.mydesignshow.ui.view

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.ceil

val Int.dp : Int
get() = ceil(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)).toInt()

val Float.dp: Float
get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)