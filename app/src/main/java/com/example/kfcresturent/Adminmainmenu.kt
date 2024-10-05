package com.example.kfcresturent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Adminmainmenu : AppCompatActivity() {


    lateinit var mainmenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminmainmenu)

        mainmenu = findViewById(R.id.mainmenu)


       /* mainmenu.setOnClickListener {
            startActivity(Intent(this, MenuMain::class.java))
        }*/
    }
}