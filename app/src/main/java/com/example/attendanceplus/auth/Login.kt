package com.example.attendanceplus.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.attendanceplus.Home_Screen
import com.example.attendanceplus.R
import kotlinx.android.synthetic.main.activity_login_screen.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        lbutton.setOnClickListener {
            if ((emailteacher.text.toString().isNotEmpty()) && (pass1.text.toString().isNotEmpty()))
            {
                if((emailteacher.text.toString() == sharedPreferences.getString("email",""))
                        && (pass1.text.toString() == sharedPreferences.getString("pass",""))){
                    startActivity(Intent(applicationContext,Home_Screen::class.java))
                }
                else
                {
                    Toast.makeText(applicationContext,"Wrong Email or Password!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext,"Please fill All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}