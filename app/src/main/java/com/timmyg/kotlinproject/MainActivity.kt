package com.timmyg.kotlinproject

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val MESSAGE_TEXT = "I just wanna to say hello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_toast.setOnClickListener{
            Toast.makeText(this, MESSAGE_TEXT, Toast.LENGTH_LONG).show()
        }
    }
}
