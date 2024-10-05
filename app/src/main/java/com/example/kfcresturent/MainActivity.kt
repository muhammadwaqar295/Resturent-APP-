package com.example.kfcresturent

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var logUsername: EditText
    lateinit var logPassword: EditText
    lateinit var loginBtn: Button
    lateinit var donthaveanacc: TextView

    lateinit var sp:SharedPreferences

    lateinit var auth: FirebaseAuth

    var db=MyDb(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        //sharedprefrence mean user ones login only after that go to dashboard
        sp=getSharedPreferences("myapp", MODE_PRIVATE)

//        if (sp.contains("token")){
//            startActivity(Intent(this,Dashboard_Activity::class.java))
//        }

          //code For lottie animation
//        Handler().postDelayed(Runnable {
//            startActivity(Intent(this@MainActivity,MainActivity::class.java))
//            finish()
//        },20000)


        logUsername=findViewById(R.id.logUsername)
        logPassword=findViewById(R.id.logPassword)
        loginBtn=findViewById(R.id.loginBtn)
        donthaveanacc=findViewById(R.id.donthaveanacc)

        donthaveanacc.setOnClickListener{
            startActivity(Intent(this, RegisterPage::class.java))
        }

        loginBtn.setOnClickListener {
            var user= logUsername.text.toString()
            var pass= logPassword.text.toString()

            if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(pass))
            {
                //////////////////////// Firebase Authentication /////////////////////////

                auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        startActivity(Intent(this,Dashboard_Activity::class.java))
                    } else
                        Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
                }

                //////////////////////////////////////////////////////////////////////////

//                var status= db.loginusers(user,pass)
//
//                if(status>0){
//
//                    var editor=sp.edit()
//                    editor.putString("token",user)
//                    editor.apply()
//                    startActivity(Intent(this,Dashboard_Activity::class.java))
//                    Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()}
//                else
//                    Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
            }
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser!=null)
            startActivity(Intent(this,Dashboard_Activity::class.java))
    }
}