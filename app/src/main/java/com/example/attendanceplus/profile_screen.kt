package com.example.attendanceplus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.attendanceplus.auth.Teacher_signup_screen
import kotlinx.android.synthetic.main.activity_profile_screen.*

class profile_screen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        myaccount.setOnClickListener {
            startActivity(Intent(this@profile_screen, my_account_screen::class.java))
        }
        imageView14.setOnClickListener {
            Toast.makeText(applicationContext,"Coming Soon!",Toast.LENGTH_SHORT).show()
        }
        sign_out.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this@profile_screen, Launch_screen::class.java))
        }
       namefield.text = sharedPreferences.getString("name","")
        std.text = "Class of Study: ${sharedPreferences.getString("Class","")}"


    }
}