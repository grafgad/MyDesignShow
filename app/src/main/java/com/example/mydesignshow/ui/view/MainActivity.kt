package com.example.mydesignshow.ui.view

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.green
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydesignshow.R
import com.example.mydesignshow.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance())
                .commitNow()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val bottomMenu =
            findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FirstFragment.newInstance())
                        .commit()
                }
                R.id.secondFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SecondFragment.newInstance())
                        .commit()
                }
            }
            true
        }



//        val appBar = findViewById<BottomAppBar>(R.id.appBar)
//        appBar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.appbar_search -> Toast.makeText(this, "search clicked", Toast.LENGTH_SHORT)
//                    .show()
////                R.id.appbar_hello -> Toast.makeText(this, "hello clicked", Toast.LENGTH_SHORT).show()
//                R.id.appbar_student_icon -> Toast.makeText(
//                    this,
//                    "avatar clicked",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            true
//        }
//        appBar.title = "Hello"
//        appBar.setTitleTextColor(AppCompatResources.getColorStateList(this, R.color.white))
//        appBar[R.id.appbar_hello].tit
    }
}