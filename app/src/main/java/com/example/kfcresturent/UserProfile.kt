package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView

class UserProfile : AppCompatActivity() {

    lateinit var profile_user:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)



        profile_user=findViewById(R.id.profile_img)









    }


    private fun decode(imageString: String): Bitmap? {
        var imageBytes = Base64.decode(imageString, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}