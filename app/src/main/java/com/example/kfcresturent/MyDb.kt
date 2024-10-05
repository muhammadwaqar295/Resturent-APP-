package com.example.kfcresturent

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDb(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, dbname,factory,1) {

    companion object {
        var dbname = "Record"
        var tblid = "itemid"
        var tblname = "users"


        var tblusername = "Username"
        var tbluserpass = "Password"
        var tbluseremail = "Email"
        var tbluserage = "Age"
        var tbluserimage = "image"




        //table names
        var tblmenu = "menu"
        var tblsubmenu = "submenu"
        var tblcart = "cart"

        //cart table
        var tblcartsid = "subid"
        var tblcartqty = "qty"
        var tblcarttotal = "total"

        //main menu
        var tblitemname = "itemaname"
        var tblitemimage = "itemstring"

        //submenu
        var tblsubname = "subname"
        var tblsubprice = "subprice"
        var tblsubsize = "subsize"
        var tblsubimage = "substring"
        var tblsubmid = "submid"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query =
            "create table $tblname($tblid INTEGER PRIMARY KEY AUTOINCREMENT, $tblusername TEXT, $tbluserpass TEXT, $tbluseremail TEXT, $tbluserage TEXT,$tbluserimage TEXT)"
        //var query =
        //"create table $tblname($tblid INTEGER PRIMARY KEY AUTOINCREMENT, $tblusername TEXT, $tbluserpass TEXT, $tbluseremail TEXT, $tbluserage TEXT, image TEXT)"
        db?.execSQL(query)



        val query_menu =
            "create table $tblmenu ($tblid INTEGER PRIMARY KEY AUTOINCREMENT, $tblitemname TEXT, $tblitemimage TEXT)"
        db?.execSQL(query_menu)

        val query_submenu =
            "create table $tblsubmenu ($tblid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " $tblsubname TEXT, $tblsubprice TEXT, $tblsubsize TEXT, $tblsubimage TEXT, $tblsubmid INTEGER)"
        db?.execSQL(query_submenu)

        val query_cart =
            "create table $tblcart ($tblid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " $tblcartsid INTEGER, $tblcartqty INTEGER, $tblcarttotal INTEGER, uid INTEGER)"
        db?.execSQL(query_cart)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")

        db?.execSQL("DROP TABLE IF EXISTS $tblmenu")
        db?.execSQL("DROP TABLE IF EXISTS $tblsubmenu")
        db?.execSQL("DROP TABLE IF EXISTS $tblcart")
        onCreate(db)
    }

    //MenuMain
    fun addmenu(name: String, img: String) {
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(tblitemname, name)
        value.put(tblitemimage, img)

        db.insert(tblmenu, null, value)
        db.close()
    }


    //SubMenu
    fun getallmenus(): ArrayList<String> {
        val menus = arrayListOf<String>()

        val db = this.readableDatabase
        val allmenus = "select * from $tblmenu"
        val cursor = db.rawQuery(allmenus, null)

        if (cursor.moveToFirst()) {
            do {
                val menuname = cursor.getString(1).toString()

                menus.add(menuname)
            } while (cursor.moveToNext())

        }

        return menus
    }


    //SubMenu
    fun addsubmenu(subname: String, mid: Int, subprice: String, subsize: String, subimg: String) {
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(tblsubname, subname)
        value.put(tblsubprice, subprice)
        value.put(tblsubsize, subsize)
        value.put(tblsubimage, subimg)
        value.put(tblsubmid, mid)

        db.insert(tblsubmenu, null, value)
        db.close()
    }




    //ListMenus
    fun allmenus(): ArrayList<MenuModel> {

        val mylist = arrayListOf<MenuModel>()
        val db = this.readableDatabase
        val allmenus = "select * from $tblmenu"
        val cursor = db.rawQuery(allmenus, null)

        if (cursor.moveToFirst()) {
            do {
                val menuname = cursor.getString(1).toString()
                val menuimage = cursor.getString(2).toString()

                mylist.add(MenuModel(menuname, menuimage))
            } while (cursor.moveToNext())

        }
        return mylist
    }
    //ListMenu & SubMenu
    fun getmid(menuname: String): Int {
        var mid = 0

        val db = this.readableDatabase

        val query = "select * from $tblmenu where $tblitemname='$menuname'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                mid = cursor.getInt(0)
            } while (cursor.moveToNext())
        }

        return mid
    }


    fun getimg(uname: String): String {
        var userimage = ""

        val db = this.readableDatabase

        val query = "select * from $tblname where $tblusername='$uname'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                userimage = cursor.getString(5)
            } while (cursor.moveToNext())
        }

        return userimage
    }


    fun getname(name: String): String {
        var username = ""

        val db = this.readableDatabase

        val query = "select * from $tblname where $tblusername='$name'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(1)
            } while (cursor.moveToNext())
        }

        return username
    }

    fun getuid(name: String): Int {
        var uid = 0

        val db = this.readableDatabase

        val query = "select * from $tblname where $tblusername='$name'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                uid = cursor.getInt(0)
            } while (cursor.moveToNext())
        }

        return uid
    }



    //ListSubMenu
    fun requiresubmenus(mid: Int): ArrayList<SubmenuModel> {
        val mylist = arrayListOf<SubmenuModel>()
        val db = this.readableDatabase
        val allmenus = "select * from $tblsubmenu where $tblsubmid='$mid'"
        val cursor = db.rawQuery(allmenus, null)

        if (cursor.moveToFirst()) {
            do {
                val submenuname = cursor.getString(1).toString()
                val submenuprice = cursor.getString(2).toString().toInt()
                val submenusize = cursor.getString(3).toString()
                val submenuimage = cursor.getString(4).toString()
                val mainid = cursor.getInt(5)

                mylist.add(
                    SubmenuModel(
                        submenuname, submenuprice, submenusize, submenuimage, mainid
                    )
                )
            } while (cursor.moveToNext())

        }
        return mylist
    }


    //ListSubMenu
    fun getsid(subname: String): Int {
        var sid = 0

        val db = this.readableDatabase

        val query = "select * from $tblsubmenu where $tblsubname='$subname'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                sid = cursor.getInt(0)
            } while (cursor.moveToNext())
        }

        return sid
    }

    fun getsubrow(sid: Int): ArrayList<SubmenuModel> {
        val mylist = arrayListOf<SubmenuModel>()

        val db = this.readableDatabase
        val query = "select * from $tblsubmenu where $tblid=$sid"

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val sname = cursor.getString(1)
            val sprice = cursor.getString(2).toInt()
            val ssize = cursor.getString(3)
            val simage = cursor.getString(4)
            val smid = cursor.getString(5).toInt()

            mylist.add(SubmenuModel(sname, sprice, ssize, simage, smid))
        }
        return mylist
    }


    //AddItem
    fun addcartitem(sid: Int, total: Int, qty: Int, uid: Int) {
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(tblcartsid, sid)
        value.put(tblcarttotal, total)
        value.put(tblcartqty, qty)
        value.put("uid", uid)

        db.insert(tblcart, null, value)
        db.close()
    }




    fun addusers(name: String, pass: String, mail: String, age: String,img: String) {
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(tblusername, name)
        value.put(tbluserpass, pass)
        value.put(tbluseremail, mail)
        value.put(tbluserage, age)
        value.put(tbluserimage, img)


        //try var tbluser
        db.insert(tblname, null, value)
        db.close()
    }

    fun loginusers(username: String, password: String): Int {

        var query =
            "select * from $tblname where $tblusername='$username' AND $tbluserpass='$password'"
        var db = this.readableDatabase
        var cursor = db.rawQuery(query, null)
        return cursor.count
    }

    fun listusers(): ArrayList<UserModel> {

        var mylist = arrayListOf<UserModel>()
        var db = this.readableDatabase
        var allrecords = "select * from $tblname"
        var cursor = db.rawQuery(allrecords, null)

        if (cursor.moveToFirst()) {
            do {
                var users = cursor.getString(1).toString()
                var mail = cursor.getString(3).toString()
                var age = cursor.getString(4).toString()
                mylist.add(UserModel(users, mail, age,"123"))
            } while (cursor.moveToNext())
        }

        return mylist
    }

    fun checkuser(umail: String): Int {
        var db = this.readableDatabase
        var checkquery = "select * from $tblname where Email = '$umail'"
        var cursor = db.rawQuery(checkquery, null)
        var totrecord = cursor.count

        return totrecord
    }

    fun getcartitems():ArrayList<CartModel>
    {
        var allitems = arrayListOf<CartModel>()

        var db = this.readableDatabase
        var tolitems = "select * from cart"
        var cursor = db.rawQuery(tolitems, null)

        if(cursor.moveToFirst())
        {
            do{
                var sid = cursor.getInt(1)
                var qty = cursor.getInt(2)
                var tot = cursor.getInt(3)
                var uid = cursor.getInt(4)

                allitems.add(CartModel(sid,qty,tot,uid))
            }while(cursor.moveToNext())
        }

        return allitems
    }

    fun getmyitems(uid:Int):ArrayList<CartModel>
    {
        var allitems = arrayListOf<CartModel>()

        var db = this.readableDatabase
        var tolitems = "select * from cart where uid='$uid'"
        var cursor = db.rawQuery(tolitems, null)

        if(cursor.moveToFirst())
        {
            do{
                var sid = cursor.getInt(1)
                var qty = cursor.getInt(2)
                var tot = cursor.getInt(3)
                var uid = cursor.getInt(4)

                allitems.add(CartModel(sid,qty,tot,uid))
            }while(cursor.moveToNext())
        }

        return allitems
    }
}
