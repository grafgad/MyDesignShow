package com.example.mydesignshow.ui.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesignshow.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

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
    }
}