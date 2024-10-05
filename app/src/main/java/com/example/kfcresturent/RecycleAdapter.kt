package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(var context: Context, var mylist:ArrayList<MenuModel>): RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    var mydb = MyDb(context,null)

    class ViewHolder(myview: View):RecyclerView.ViewHolder(myview) {
        var unam = myview.findViewById<TextView>(R.id.categ_name)
        var uimag = myview.findViewById<ImageView>(R.id.categ_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.my_categ_view,parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var usernam = mylist.get(position).itemname

        var userimag = mylist.get(position).uItemimage

        holder.unam.text = usernam
        holder.uimag.setImageBitmap(decode(userimag))

        holder.itemView.setOnClickListener {
            //println("Click Listener is Working on Whole Item")

            var mid = mydb.getmid(usernam)

            val intent= Intent(context,ListSubMenus::class.java)
            intent.putExtra("mid",mid)
            context.startActivity(intent)
        }

    }

    private fun decode(imageString: String): Bitmap? {
        var imageBytes = Base64.decode(imageString, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}