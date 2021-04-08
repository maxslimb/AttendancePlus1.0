package com.example.attendanceplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.perm.*

class perm1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perm)
// required location

        per.visibility = View.VISIBLE
        te1.visibility = View.VISIBLE
        per.setOnClickListener {
            val myi = Intent(this@perm1, main::class.java)
            this@perm1.startActivity(myi)
        }
    }
}