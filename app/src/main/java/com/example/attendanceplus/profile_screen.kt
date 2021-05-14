package com.example.attendanceplus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.attendanceplus.auth.Login
import com.example.attendanceplus.auth.Teacher_signup_screen
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home__screen.*
import kotlinx.android.synthetic.main.activity_profile_screen.*

class profile_screen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        myaccount.setOnClickListener {
            when{
               sharedPreferences.getString("type","")=="teacher" ->{
                startActivity(Intent(this@profile_screen, my_account_screen_teacher::class.java))
               }
                sharedPreferences.getString("type","")=="student" ->{
                    startActivity(Intent(this@profile_screen, my_account_screen::class.java))
                }
            }
        }
        imageView14.setOnClickListener {
            Toast.makeText(applicationContext,"Coming Soon!",Toast.LENGTH_SHORT).show()
        }
        sign_out.setOnClickListener {
           // sharedPreferences.edit().clear().apply()  -- delete acc
            sharedPreferences.edit().putBoolean("login",false).apply()
            sharedPreferences.edit().putBoolean("save-acc",true).apply()
            val intent = Intent(this@profile_screen, Login::class.java)  //Login
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


        namefield.text = sharedPreferences.getString("name","")
        std.text = "Class of Study: ${sharedPreferences.getString("Class","")}"

        bottom_navigation_view1.selectedItemId = R.id.profile
        bottom_navigation_view1.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    val intent = Intent(applicationContext, Home_Screen::class.java)
                    this.startActivity(intent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

    }
}