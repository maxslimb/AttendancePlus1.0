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
        nameteacher.setText(sharedPreferences.getString("name",""))
        emailteacher.setText(sharedPreferences.getString("email",""))
        deptteacher.setText(sharedPreferences.getString("admin-no",""))
        yearteacher.setText(sharedPreferences.getString("Class",""))
        divteacher.setText(sharedPreferences.getString("division",""))
    }
}