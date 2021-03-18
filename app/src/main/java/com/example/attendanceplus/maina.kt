package com.example.attendanceplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class maina : AppCompatActivity() {
    var b1: Button? = null
    var b2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mania)
        val student = findViewById<Button>(R.id.student)
        student.setOnClickListener(View.OnClickListener {
            val myi = Intent(this@maina, perm::class.java)
            startActivity(myi)
        })
        val teacher = findViewById<Button>(R.id.teacher)
        teacher.setOnClickListener(View.OnClickListener {
            val myi = Intent(this@maina, perm1::class.java)
            this@maina.startActivity(myi)
        })
    }
}