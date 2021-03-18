package com.example.attendanceplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.perm.*

class perm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perm)

        per.visibility = View.VISIBLE
        te2.visibility = View.VISIBLE
        per.setOnClickListener{
            val myi = Intent(this@perm, mainb::class.java)
            this@perm.startActivity(myi)
        }
    }
}