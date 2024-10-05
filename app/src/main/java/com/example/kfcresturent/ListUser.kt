package com.example.kfcresturent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListUser : AppCompatActivity() {


    lateinit var liSTview: ListView

    var mylist= arrayListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)


       // var db=MyDb(this,null)

        liSTview=findViewById(R.id.listview)

        var database = Firebase.database
        var myRef = database.getReference("KFC")


        myRef.child("Accounts").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(items in snapshot.children)
                {
                    var umodel = items.getValue(UserModel::class.java)
                    mylist.add(umodel!!)
                    //println("=================================================== ${umodel?.uname}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        var adapter=UserAdapter(this,mylist)
        liSTview.adapter=adapter
    }
}