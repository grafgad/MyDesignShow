package com.example.mydesignshow.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydesignshow.R
import com.example.mydesignshow.databinding.FragmentSecondBinding
import com.example.mydesignshow.model.MyDayClassList
import com.example.mydesignshow.ui.adapters.DayClassAdapter
import java.text.SimpleDateFormat
import java.util.*

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding: FragmentSecondBinding by viewBinding(CreateMethod.BIND)
    private val dayClassAdapter = DayClassAdapter()
    private lateinit var dayRecyclerView: RecyclerView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
        val myDate = dateFormat.format(calendar.time)
        binding.appBar.subtitle = resources.getString(R.string.today) + myDate

        dayRecyclerView = binding.dayRecycler
        dayRecyclerView.adapter = dayClassAdapter
//        dayRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        dayClassAdapter.updateDayClass(MyDayClassList.myDayClasses)
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}