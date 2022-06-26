package com.example.mydesignshow.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesignshow.App
import com.example.mydesignshow.R
import com.example.mydesignshow.model.MyDayClass

class DayClassAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    var myDayClassList = listOf<MyDayClass>()


    fun updateDayClass(newList: List<MyDayClass>) {
        myDayClassList = newList
    }

    class DayClassViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayClassName = itemView.findViewById<TextView>(R.id.class_name)
        private val dayTime = itemView.findViewById<TextView>(R.id.day_time)
        private val dayImage = itemView.findViewById<ImageView>(R.id.day_class_image)
        private val dayTeacher = itemView.findViewById<TextView>(R.id.day_teacher)
        private val dayVideo = itemView.findViewById<Button>(R.id.day_class_videochat)
        private val dayFirstCircle = itemView.findViewById<ImageView>(R.id.day_first_point)
        private val dayFirstCircle1 = itemView.findViewById<ImageView>(R.id.day_point_1)


        fun onBind(myDayClass: MyDayClass) {
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
                dayFirstCircle1.visibility = View.VISIBLE
//                dayFirstCircle.setImageResource(R.drawable.ic_baseline_circle_24)           //solidColor.getResources().getColor(R.color.gray)
                dayFirstCircle.visibility = View.VISIBLE
            } else {
                dayFirstCircle.visibility = View.INVISIBLE
                dayFirstCircle1.visibility = View.GONE
            }

            dayVideo.setOnClickListener {
                Toast.makeText(context, "opening Zoom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class DayClassViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayClassName = itemView.findViewById<TextView>(R.id.day_class_name2)
        private val dayTime = itemView.findViewById<TextView>(R.id.day_time2)
        private val dayImage = itemView.findViewById<ImageView>(R.id.day_class_image2)
        private val dayTeacher = itemView.findViewById<TextView>(R.id.day_teacher2)
        private val dayFirstCircle = itemView.findViewById<ImageView>(R.id.day_firs_point2)
        private val backgroundImageView =
            itemView.findViewById<ImageView>(R.id.day_class_card_background2)

        private val description = itemView.findViewById<TextView>(R.id.day_class_card_description2)
        fun onBind(myDayClass: MyDayClass) {

            dayClassName.text = myDayClass.className
            dayTime.text = myDayClass.time
            dayTeacher.text = myDayClass.teacher
            description.text = myDayClass.description
            if (myDayClass.id == 0) {
                dayFirstCircle.setImageResource(R.drawable.ic_baseline_circle_24)           //solidColor.getResources().getColor(R.color.gray)
                dayFirstCircle.visibility = View.VISIBLE
            } else {
                dayFirstCircle.visibility = View.INVISIBLE
            }

            backgroundImageView.setAnimatedShadow(
                startColors = intArrayOf(
                    Color.WHITE,
                    Color.GREEN,
                    Color.WHITE
                ),
                endColors = intArrayOf(
                    Color.GREEN,
                    Color.WHITE,
                    Color.GREEN
                ),
                cornerRadius = 20f.dp,
                padding = 30.dp,
                backgroundColor = R.drawable.background_gradient,
                shadowColor = Color.BLACK, // не вижу разницы в изменении
                duration = 2000
            )
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            DayClassViewHolder1(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_day_classes1, parent, false)
            )
        } else {
            DayClassViewHolder2(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_day_classes2, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (myDayClassList[position].viewType == VIEW_TYPE_ONE) {
            (holder as DayClassViewHolder1).onBind(myDayClassList[position])
        } else {
            (holder as DayClassViewHolder2).onBind(myDayClassList[position])
        }
    }

    override fun getItemCount() = myDayClassList.size


    override fun getItemViewType(position: Int): Int {
        return myDayClassList[position].viewType
    }


}