package com.example.passking

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class viewPass : Activity() {
    var recyclerView: RecyclerView? = null
    var myDB: DatabaseHelper? = null
    var _site: ArrayList<String>? = null
    var _username: ArrayList<String>? = null
    var _password: ArrayList<String>? = null
    var _note: ArrayList<String>? = null
    var customAdapter: CustomAdapter? = null

    // for inflating the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val actionBar = actionBar
        actionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)
        return true
    }

    // on selection of the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.add_pass -> {
                val intent = Intent(this, PasswordManagerActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.mp -> {
                val intent2 = Intent(this, masterPass::class.java)
                startActivity(intent2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewpass)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        myDB = DatabaseHelper(this@viewPass)
        _site = ArrayList()
        _username = ArrayList()
        _note = ArrayList()
        _password = ArrayList()
        storeDataInArray()
        customAdapter = CustomAdapter(this@viewPass, _site!!, _username!!, _password!!, _note!!)
        recyclerView!!.adapter = customAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(this@viewPass)
    }

    fun storeDataInArray() {
        val cursor = myDB!!.readAllData()
        if (cursor!!.count == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                _site!!.add(cursor.getString(0))
                _username!!.add(cursor.getString(1))
                _password!!.add(cursor.getString(2))
                _note!!.add(cursor.getString(3))
            }
        }
    }
}