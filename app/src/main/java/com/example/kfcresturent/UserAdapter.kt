package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class UserAdapter(var context: Context, var mylist:ArrayList<UserModel>): BaseAdapter() {
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
        var view= LayoutInflater.from(context).inflate(R.layout.userview,p2,false)

        var txtusername: TextView =view.findViewById(R.id.viewUname)
        var txtusermail: TextView =view.findViewById(R.id.viewEmail)
        var txtuserage: TextView =view.findViewById(R.id.viewAge)

        var userimage:ImageView=view.findViewById(R.id.userimage)

        var username=mylist.get(p0).uname
        var usermail=mylist.get(p0).umail
        var userage=mylist.get(p0).uage
        var userimg=mylist.get(p0).uimage


        txtusername.text=username
        txtusermail.text=usermail
        txtuserage.text=userage

        // for getting image from firebase
        Glide.with(context)
            .load(userimg)
            .into(userimage)


        return view
    }
}