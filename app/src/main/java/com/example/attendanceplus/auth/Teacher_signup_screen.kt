package com.example.attendanceplus.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.attendanceplus.Home_Screen
import com.example.attendanceplus.R
import com.example.attendanceplus.maina
import kotlinx.android.synthetic.main.activity_teacher_signup_screen.*

class Teacher_signup_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_signup_screen)

        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        submit_userinfo.setOnClickListener{
            if ((sname.text.toString().isNotEmpty()) && (semail.text.toString().isNotEmpty()) &&
                    (department.text.toString().isNotEmpty()) && (year.text.toString().isNotEmpty()) &&
                    (division1.text.toString().isNotEmpty()) && (seco1.text.toString().isNotEmpty()) &&
                    (pass1.text.toString().isNotEmpty()) && (repass1.text.toString().isNotEmpty())
            ) {
                if (pass1.text.toString() == repass1.text.toString()){
                    val editor: SharedPreferences.Editor= sharedPreferences.edit()
                    editor.putString("name", sname.text.toString())
                    editor.putString("email", semail.text.toString())
                    editor.putString("department",department.text.toString())
                    editor.putString("year",year.text.toString())
                    editor.putString("division",division1.text.toString())
                    editor.putString("secret-code",seco1.text.toString())
                    editor.putString("pass",pass1.text.toString())
                    editor.putString("type", "teacher")
                    editor.commit()
                    startActivity(Intent(this@Teacher_signup_screen, Home_Screen::class.java))
                }
                else{
                    Toast.makeText(applicationContext,"Password does not match!", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(applicationContext,"Please fill All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}