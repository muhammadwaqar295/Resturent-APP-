package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class admin_main : AppCompatActivity() {


    lateinit var menumain:Button
    lateinit var submenu:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)


        menumain=findViewById(R.id.menumain)
        submenu=findViewById(R.id.submenu)

        menumain.setOnClickListener {
            startActivity(Intent(this, MenuMain::class.java))
        }


        submenu.setOnClickListener {

            startActivity(Intent(this,SubMenu::class.java))
        }





    }
}