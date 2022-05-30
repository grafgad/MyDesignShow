package com.example.mydesignshow.ui.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.SweepGradient
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RoundRectShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.view.doOnNextLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesignshow.App
import com.example.mydesignshow.R
import com.example.mydesignshow.model.DayClass
import com.example.mydesignshow.ui.view.ColorShadow
import com.example.mydesignshow.ui.view.dp
import com.google.android.material.card.MaterialCardView

class DayClassAdapter : RecyclerView.Adapter<DayClassAdapter.DayClassViewHolder>() {

    private var dayClassList = emptyList<DayClass>()

    fun updateDayClass(newList: List<DayClass>) {
        dayClassList = newList
    }

    class DayClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayClassName = itemView.findViewById<TextView>(R.id.day_class_name)
        private val dayTime = itemView.findViewById<TextView>(R.id.day_time)
        private val dayImage = itemView.findViewById<ImageView>(R.id.day_class_image)
        private val dayTeacher = itemView.findViewById<TextView>(R.id.day_teacher)
        private val dayVideo = itemView.findViewById<Button>(R.id.day_class_videochat)
        private val dayFirstCircle = itemView.findViewById<ImageView>(R.id.day_firs_point)
        private val customBackground = itemView.findViewById<ImageView>(R.id.day_class_card_background)


        fun onBind(myDayClass: DayClass) {
            val context: Context = App.instance

            dayClassName.text = myDayClass.className
            dayTime.text = myDayClass.time
            dayTeacher.text = myDayClass.teacher
            if (myDayClass.classVideo) {
                dayVideo.visibility = View.VISIBLE
            } else {
                dayVideo.visibility = View.GONE
            }
            if (myDayClass.id == 0) {
                dayFirstCircle.setImageResource(R.drawable.ic_baseline_circle_24)           //solidColor.getResources().getColor(R.color.gray)
                dayFirstCircle.visibility = View.VISIBLE
            } else {
                dayFirstCircle.visibility = View.INVISIBLE
            }

            dayVideo.setOnClickListener {
                Toast.makeText(context, "opening Zoom", Toast.LENGTH_SHORT).show()
            }


            customBackground.doOnNextLayout {
                val colors = intArrayOf(
                    Color.WHITE,
                    Color.RED,
                    Color.WHITE
                )
                val cornerRadius = 20f.dp
                val padding = 15.dp
                val centerX = it.width.toFloat() / 2 - padding
                val centerY = it.height.toFloat() / 2 - padding

                val shadowDrawable = ColorShadow().createShadowDrawable(
                    colors = colors,
                    cornerRadius = cornerRadius,
                    elevation = padding / 2f,
                    centerX = centerX,
                    centerY = centerY
                )

                val colorDrawable = ColorShadow().createColorDrawable(
                    backgroundColor = Color.BLACK,
                    cornerRadius = cornerRadius
                )

                fun View.setColorShadowBackground(
                    shadowDrawable: ShapeDrawable,
                    colorDrawable: Drawable,
                    padding: Int
                ) {
                    val drawable = LayerDrawable(arrayOf(shadowDrawable, colorDrawable))
                    drawable.setLayerInset(0, padding, padding, padding, padding)
                    drawable.setLayerInset(1, padding, padding, padding, padding)
                    setPadding(padding, padding, padding, padding)
                    background = drawable
                }

                it.setColorShadowBackground(
                    shadowDrawable = shadowDrawable,
                    colorDrawable = colorDrawable,
                    padding = 15.dp
                )

                // Второй массив с цветами. Размер массивов должен быть одинаковый.
                val endColors = intArrayOf(
                    Color.RED,
                    Color.WHITE,
                    Color.RED
                )
                ColorShadow().animateShadow(
                    shapeDrawable = shadowDrawable,
                    startColors = colors,
                    endColors = endColors,
                    duration = 2000,
                    centerX = centerX,
                    centerY = centerY
                )

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayClassViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_day_classes, parent, false)
        return DayClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayClassViewHolder, position: Int) {
        holder.onBind(dayClassList[position])
    }

    override fun getItemCount() = dayClassList.size

}