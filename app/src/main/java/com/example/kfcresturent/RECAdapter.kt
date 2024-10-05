/*
package com.example.kfcresturent

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RECAdapter(var context: Context, var testList: ArrayList<MenuModel>): RecyclerView.Adapter<RECAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener

    }


    class ViewHolder(itemView: View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var unam = itemView.findViewById<TextView>(R.id.categ_name)
        var uimag = itemView.findViewById<ImageView>(R.id.categ_image)


        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.my_categ_view, parent, false)

        return ViewHolder(view,mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var usernam = testList.get(position).itemname

        var userimag = testList.get(position).uItemimage

        holder.unam.text = usernam
        //holder.uimag.setImageResource(userimag)
        holder.uimag.setImageBitmap(decode(userimag))

    }

    override fun getItemCount(): Int {
        return testList.size
    }

    private fun decode(imageString: String): Bitmap? {
        var imageBytes = Base64.decode(imageString, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}*/
