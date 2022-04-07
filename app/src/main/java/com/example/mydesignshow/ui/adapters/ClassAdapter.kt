package com.example.mydesignshow.ui.adapters

import android.content.Context
import android.view.Gravity
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
import com.example.mydesignshow.model.MyClass

class ClassAdapter: RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    fun updateClass (newList: List<MyClass>) {
        classList = newList
    }

    private var classList = emptyList<MyClass>()

    class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val classImage = itemView.findViewById<ImageView>(R.id.class_image)
        private val className = itemView.findViewById<TextView>(R.id.class_name)
        private val classTime = itemView.findViewById<TextView>(R.id.class_time)
        private val classVideochat = itemView.findViewById<Button>(R.id.class_videochat)

        fun onBind(myClass: MyClass){
            val context: Context = App.instance

            className.text = myClass.name
            classTime.text = myClass.time
            if (myClass.zoom){
            classVideochat.visibility = View.VISIBLE
            } else {
                classVideochat.visibility = View.GONE
            }
            classVideochat.setOnClickListener {
                Toast.makeText(context, "opening zoom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.onBind(classList[position])
    }

    override fun getItemCount() = classList.size

}