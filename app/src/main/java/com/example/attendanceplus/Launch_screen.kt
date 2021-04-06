package com.example.attendanceplus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launch_screen.*
import kotlinx.android.synthetic.main.activity_signup_screen.*

class Launch_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor= sharedPreferences.edit()
        teacher.setOnClickListener {
            editor.putString("type", "teacher")
            startActivity(Intent(this@Launch_screen, perm1::class.java))
        }
        student.setOnClickListener {
            editor.putString("type", "student")
            startActivity(Intent(this@Launch_screen, perm::class.java))
        }
    }
}