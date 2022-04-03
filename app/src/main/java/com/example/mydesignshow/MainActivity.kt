package com.example.mydesignshow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                .add(R.id.container, SecondFragment.newInstance())
                .replace(R.id.container, FirstFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomMenu) // почеиу не работает с  binding???
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstFragment -> {
                    Toast.makeText(this," works?", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FirstFragment.newInstance())
//                        .hide(SecondFragment.newInstance())
//                        .show(FirstFragment.newInstance())
                        .commit()
                }
                R.id.secondFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SecondFragment.newInstance())
//                        .hide(FirstFragment.newInstance())
//                        .show(SecondFragment.newInstance())
                        .commit()
                }
            }
            true
        }
    }

}