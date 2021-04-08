package com.example.attendanceplus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_account_screen.*

class my_account_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        name1.setText(sharedPreferences.getString("name",""))
        email1.setText(sharedPreferences.getString("email",""))
        dob1.setText(sharedPreferences.getString("admin-no",""))
        grade1.setText(sharedPreferences.getString("Class",""))
        institution1.setText(sharedPreferences.getString("division",""))
    }
}