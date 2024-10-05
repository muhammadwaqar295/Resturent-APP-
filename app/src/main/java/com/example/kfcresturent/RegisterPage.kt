package com.example.kfcresturent

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

class RegisterPage : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var etEmail: EditText
    lateinit var etAge: EditText
    lateinit var registerbtn: Button
    lateinit var haveanacc: TextView
    lateinit var checkdatabase: TextView
    lateinit var registerimage: ImageView

    private lateinit var auth: FirebaseAuth;
    private var firebasestore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private lateinit var selectedImage: Uri


    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)


        //var db=MyDb(this,null)
        auth = FirebaseAuth.getInstance()
        var database = Firebase.database
        var myRef = database.getReference("KFC")


        firebasestore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference



        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
        etAge = findViewById(R.id.etAge)
        registerbtn = findViewById(R.id.registerbtn)
        haveanacc = findViewById(R.id.haveanacc)
        checkdatabase = findViewById(R.id.checkdatabase)
        registerimage = findViewById(R.id.registerimage)


        haveanacc.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        checkdatabase.setOnClickListener {
            startActivity(Intent(this, ListUser::class.java))
        }

        registerbtn.setOnClickListener {
            var uname = etUsername.text.toString()
            var umail = etEmail.text.toString()
            var upass = etPassword.text.toString()
            var uage = etAge.text.toString()

            if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(upass) && !TextUtils.isEmpty(umail) && !TextUtils.isEmpty(
                    uage
                )
            ) {
                //////////////////////////// Firebase Registration //////////////////////////////////

                auth.createUserWithEmailAndPassword(umail, upass).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        var obj1 = UserModel(uname, umail, upass, uage)
                        //myRef.child("Accounts").push().setValue(obj1)
                        var uid = auth.currentUser?.uid
                        if (uid != null) {

                            var imageName = getActualName()

                            var ref =
                                storageReference!!.child("User/$imageName").putFile(selectedImage)

                            ref.addOnSuccessListener {
                                storageReference!!.child("User/$imageName").downloadUrl.addOnSuccessListener {
                                    println("Uploaded Image URI is -------------$it")

                                    var obj1 = UserModel(uname, umail, upass, uage, it.toString())
                                    myRef.child("Account").push().setValue(obj1)
                                }
                            }

                            //  myRef.child("Accounts").child(uid).setValue(obj1)
                        }
                        Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                    }
                }

                /////////////////////////////////////////////////////////////////////////////////////
//                val imgstring = encode(selectedImage)
//                var count = db.checkuser(umail)
//                if (count>0){
//                    Toast.makeText(this, "already exist", Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    db.addusers(uname,upass,umail,uage,imgstring)
//                }


            } else {
                Toast.makeText(this, "fields are empty", Toast.LENGTH_SHORT).show()
            }

            //db.listusers()
        }

        registerimage.setOnClickListener {
            val galintent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galintent, 555)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 555) {

            selectedImage = data?.data!!
            registerimage.setImageURI(selectedImage)

        }
    }

    private fun encode(myimage: Uri): String {
        val input = this.contentResolver.openInputStream(myimage)
        val image = BitmapFactory.decodeStream(input, null, null)

        val mybytes = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, mybytes)
        val imageBytes = mybytes.toByteArray()
        /*val imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT)*/

        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    fun getActualName(): String {
        val returnCursor: Cursor =
            this.contentResolver.query(selectedImage, null, null, null, null)!!
        val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        returnCursor.close()

        println("Selected Image using Normal Method----------------------------------------------------------- $name")
        return name
    }


}