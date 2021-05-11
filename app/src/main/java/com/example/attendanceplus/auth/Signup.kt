package com.example.attendanceplus.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.attendanceplus.Home_Screen
import com.example.attendanceplus.R
import com.example.attendanceplus.maina
import kotlinx.android.synthetic.main.activity_signup_screen.*
import org.json.JSONException
import org.json.JSONObject

class Signup : AppCompatActivity() {
    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val jsonObject = JSONObject()
        try {
            jsonObject.put("2018HE0269", "755")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        submit_userinfo.setOnClickListener{
            if ((sname.text.toString().isNotEmpty()) && (semail.text.toString().isNotEmpty()) &&
                (idno.text.toString().isNotEmpty()) && (iname.text.toString().isNotEmpty()) &&
                (division1.text.toString().isNotEmpty()) && (seco1.text.toString().isNotEmpty()) &&
                (pass1.text.toString().isNotEmpty()) && (repass1.text.toString().isNotEmpty())
            ) {   Log.e("json get",jsonObject.getString(idno.text.toString()))
                    if (pass1.text.toString() == repass1.text.toString()){
                        val editor: SharedPreferences.Editor= sharedPreferences.edit()
                        editor.putString("name", sname.text.toString())
                        editor.putString("email", semail.text.toString())
                        editor.putString("admin-no",idno.text.toString())
                        editor.putString("Class",iname.text.toString())
                        editor.putString("division",division1.text.toString())
                        editor.putString("secret-code",seco1.text.toString())
                        editor.putString("pass",pass1.text.toString())
                        editor.putString("type", "student")
                        editor.commit()
                        startActivity(Intent(this@Signup,Home_Screen::class.java))
                    }
                else{
                    Toast.makeText(applicationContext,"Password does not match!",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(applicationContext,"Please fill All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}