package com.example.attendanceplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home__screen.*

class Home_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__screen)
        lbutton1.setOnClickListener {
            startActivity(Intent(this@Home_Screen, perm::class.java))
        }

        bottom_navigation_view.selectedItemId = R.id.home
        bottom_navigation_view.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.profile -> {
                    val intent = Intent(applicationContext, profile_screen::class.java)
                    this.startActivity(intent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}