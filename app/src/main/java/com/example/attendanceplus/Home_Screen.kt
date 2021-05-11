package com.example.attendanceplus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home__screen.*

class Home_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("login",true).apply()
        textView4.text = sharedPreferences.getString("name","")
        year.text = sharedPreferences.getString("Class","")
        "Welcome ${sharedPreferences.getString("name", "")}".also { textView.text = it }
        when{sharedPreferences.getString("type","")=="teacher" -> {
            lbutton1.visibility =View.GONE
            lbutton.visibility = View.VISIBLE
        }
            sharedPreferences.getString("type","")=="student" -> {
                lbutton1.visibility =View.VISIBLE
                lbutton.visibility = View.GONE
            }
        }

        lbutton.setOnClickListener {
            startActivity(Intent(this@Home_Screen, permission::class.java))
        }
        lbutton1.setOnClickListener {
            startActivity(Intent(this@Home_Screen, permission::class.java))
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