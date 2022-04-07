package com.example.mydesignshow.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydesignshow.R
import com.example.mydesignshow.databinding.FragmentFirstBinding
import com.example.mydesignshow.model.MyClassList
import com.example.mydesignshow.model.MyHomeworkList
import com.example.mydesignshow.ui.adapters.ClassAdapter
import com.example.mydesignshow.ui.adapters.HomeworkAdapter


class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding(CreateMethod.BIND)
    private val classAdapter = ClassAdapter()
    private lateinit var classRecyclerView: RecyclerView
    private val homeworkAdapter = HomeworkAdapter()
    private lateinit var homeworkRecycler: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        classRecyclerView = binding.classRecycler
        classRecyclerView.adapter = classAdapter
        classRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        classAdapter.updateClass(MyClassList.classList)
        homeworkRecycler = binding.homeworkRecycler
        homeworkRecycler.adapter = homeworkAdapter
        homeworkRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeworkAdapter.updateHomework(MyHomeworkList.homeworkList)

        val appBar = binding.appBar
        appBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.appbar_search -> Toast.makeText(
                    requireContext(),
                    "search clicked",
                    Toast.LENGTH_SHORT
                )
                    .show()
                R.id.appbar_student_icon -> Toast.makeText(
                    requireContext(),
                    "avatar clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }
        someFun()
    }

    private fun someFun() {
        binding.appBar.title = "Hi, Gevorg"

    }

    companion object {
        fun newInstance() = FirstFragment()
    }
}