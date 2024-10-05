package com.example.kfcresturent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CategAdapter (var context: Context, var mylistview:ArrayList<Cmodel>): BaseAdapter(){
    override fun getCount(): Int {
        return mylistview.size
    }

    override fun getItem(position: Int): Any {
        return mylistview.get(position)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()

    }



    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
// binding main activity to my view
        var view= LayoutInflater.from(context).inflate(R.layout.my_categ_view,parent,false)
        var usrimage =view.findViewById<ImageView>(R.id.categ_image)
        var txtname =view.findViewById<TextView>(R.id.categ_name)

        var imguser = mylistview.get(position).img
        var  txtuser= mylistview.get(position).name

        txtname.text=txtuser
        usrimage.setImageResource(imguser)

        return view



    }

}