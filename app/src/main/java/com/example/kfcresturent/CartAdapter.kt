package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CartAdapter(var context: Context, var mylist:ArrayList<CartModel> ): BaseAdapter() {
    override fun getCount(): Int {
        return mylist.size
    }

    override fun getItem(p0: Int): Any {
        return mylist.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view= LayoutInflater.from(context).inflate(R.layout.cartlayout,p2,false)

        //Cartlayout

        var ctprice=view.findViewById<TextView>(R.id.cartprice)
        var ctotal=view.findViewById<TextView>(R.id.carttotal)

        //CartModel

        var txtprice=mylist.get(p0).cprice
        var txttotal=mylist.get(p0).cimage




        ctprice.text= txtprice.toString()
        ctotal.text= txttotal.toString()





        return view
    }

}