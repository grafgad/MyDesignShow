package com.example.mydesignshow.ui.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mydesignshow.App
import com.example.mydesignshow.R
import com.example.mydesignshow.model.MyHomework

class HomeworkAdapter : RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {


    fun updateHomework(newList: List<MyHomework>) {
        homeworkList = newList
    }

    private var homeworkList = emptyList<MyHomework>()

    class HomeworkViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        private val context: Context = itemVIew.context
        private val resources = context.resources

        private val name = itemVIew.findViewById<TextView>(R.id.homework_name)
        private val timer = itemVIew.findViewById<TextView>(R.id.homework_timer)
        private val description = itemVIew.findViewById<TextView>(R.id.homework_description)
        private val image = itemVIew.findViewById<ImageView>(R.id.homework_image)
        fun onBind(myHomework: MyHomework) {

            timer.text = resources.getQuantityString(R.plurals.days_left, myHomework.daysLeft,myHomework.daysLeft)
            name.text = myHomework.name
            if (myHomework.daysLeft <= 3) {
                timer.setTextColor(AppCompatResources.getColorStateList(context, R.color.red))
                TextViewCompat.setCompoundDrawableTintList(timer, ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
            description.text = myHomework.description
            image.load(R.drawable.ic_literature)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeworkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
        return HomeworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.onBind(homeworkList[position])
    }

    override fun getItemCount() = homeworkList.size
}
