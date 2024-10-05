package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.Transliterator.Position
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Dashboard_Activity : AppCompatActivity() {

    lateinit var sp:SharedPreferences

    lateinit var recview: RecyclerView

    lateinit var info: androidx.cardview.widget.CardView
    lateinit var itemclick: androidx.cardview.widget.CardView
    lateinit var logout:androidx.cardview.widget.CardView
    lateinit var admindash:androidx.cardview.widget.CardView
    lateinit var listitems:androidx.cardview.widget.CardView

    lateinit var userprofile:de.hdodenhof.circleimageview.CircleImageView
    lateinit var usernme:TextView


    private lateinit var selectedImage: Uri

    private lateinit var auth: FirebaseAuth


    var mylist = arrayListOf<MenuModel>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()


        sp=getSharedPreferences("myapp", MODE_PRIVATE)
        var getuname = sp.getString("token",null);
        println("Username is: ----------------------------------------------------- $getuname")

        var mydb = MyDb(this,null)

        var img = mydb.getimg(getuname.toString());
        println("Userimage is: ------------------------------------------------------$img")
        var imgBitmap = decode(img)



        var name = mydb.getname(getuname.toString())
        var uid = mydb.getuid(getuname.toString())







        logout=findViewById(R.id.LogOut)
        admindash=findViewById(R.id.admindash)
        listitems=findViewById(R.id.listcart)
        //itemclick=findViewById(R.id.dash_item_card)
        userprofile=findViewById(R.id.user_profile)
        usernme=findViewById(R.id.user_name)

        userprofile.setImageBitmap(imgBitmap)



        usernme.text = name




        admindash.setOnClickListener {
            startActivity(Intent(this, admin_main::class.java))
        }

        listitems.setOnClickListener {
            var intent = Intent(this, ListCart::class.java)
            //intent.putExtra("userid",uid)
            startActivity(intent)
        }

        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))

            val sp = getSharedPreferences("myapp", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.clear()
            editor.apply()

        }

        userprofile.setOnClickListener {

            startActivity(Intent(this,UserProfile::class.java))

        }

        info = findViewById(R.id.info)

        info.setOnClickListener {
            startActivity(Intent(this, Infopage::class.java))
            Toast.makeText(this, "about us", Toast.LENGTH_SHORT).show()

        }

        recview = findViewById(R.id.recview)
        recview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mylist.addAll(mydb.allmenus())






//        mylistview.add(Cmodel(R.drawable.img_categ, "Burger"))
//        mylistview.add(Cmodel(R.drawable.pepperoni, "pepperoni"))
//        mylistview.add(Cmodel(R.drawable.fajita, "Fajita"))
//        mylistview.add(Cmodel(R.drawable.sand, "sandwich"))
//        mylistview.add(Cmodel(R.drawable.supreme, "Supreme"))



        var adapter = RecycleAdapter(this, mylist)
        recview.adapter = adapter
       /* adapter.setOnItemClickListener(object :RECAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@Dashboard_Activity,"You Clicked on item no $position",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@Dashboard_Activity,ListMenu::class.java)
                startActivity(intent)
            }


        })
*/


    }

    private fun decode(imageString: String): Bitmap? {
        var imageBytes = Base64.decode(imageString, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

    }
}