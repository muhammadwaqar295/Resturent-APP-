package com.example.kfcresturent

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ListCart : AppCompatActivity() {


    lateinit var cartlist: ListView

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cart)

        var db = MyDb(this,null)

        sp=getSharedPreferences("myapp", MODE_PRIVATE)
        var getuname = sp.getString("token",null)

        var uid = db.getuid(getuname.toString())


        cartlist=findViewById(R.id.cartlist)

        var mylist = arrayListOf<CartModel>()


        mylist.addAll(db.getmyitems(uid))

        var adapter = CartAdapter(this,mylist)
        cartlist.adapter = adapter




    }
}