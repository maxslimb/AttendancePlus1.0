package com.example.attendanceplus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.attendanceplus.auth.Login
import com.example.attendanceplus.auth.Signup
import com.example.attendanceplus.auth.Teacher_signup_screen
import kotlinx.android.synthetic.main.activity_launch_screen.*
import kotlinx.android.synthetic.main.activity_signup_screen.*

class Launch_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
       val a = sharedPreferences.getBoolean("save-acc",false)
        val b =sharedPreferences.getBoolean("login",false)
        titleapp.postDelayed({ titleapp.visibility = View.GONE

            when {
                !a&&!b -> {
                    Log.d("log","a==null")
                    teacher.visibility = View.VISIBLE
                    student.visibility = View.VISIBLE
                    teacher.setOnClickListener {
                        startActivity(Intent(this@Launch_screen, Teacher_signup_screen::class.java))
                    }
                    student.setOnClickListener {
                        startActivity(Intent(this@Launch_screen, Signup::class.java))
                    }
                }
                a&&!b -> {
                    teacher.visibility = View.VISIBLE
                    student.visibility = View.VISIBLE
                    teacher.setOnClickListener {
                        startActivity(Intent(this@Launch_screen, Login::class.java))
                    }
                    student.setOnClickListener {
                        startActivity(Intent(this@Launch_screen, Login::class.java))
                    }
                }
                sharedPreferences.getString("type","")=="teacher" -> {
                    Log.d("log","a==teacher")
                    startActivity(Intent(this@Launch_screen, Home_Screen::class.java))
                }
                sharedPreferences.getString("type","")=="student" -> {
                    Log.d("log","a==student")
                    startActivity(Intent(this@Launch_screen, Home_Screen::class.java))
                }
            }
        }, 1000)

    }
}