package com.example.passking

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PasswordManagerActivity : Activity() {
    var _btnInsert: Button? = null
    var _btnUpdate: Button? = null
    var _txtSite: EditText? = null
    var _txtUser: EditText? = null
    var _txtPass: EditText? = null
    var _txtNote: EditText? = null
    var openHelper: SQLiteOpenHelper? = null
    var db: SQLiteDatabase? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.view_passwords -> {
                val intent = Intent(this, viewPass::class.java)
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

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        _btnInsert = findViewById<View>(R.id.buttonSave) as Button
        _btnUpdate = findViewById<View>(R.id.buttonUpdate) as Button
        _txtSite = findViewById<View>(R.id.editTextSite) as EditText
        _txtUser = findViewById<View>(R.id.editTextUsername) as EditText
        _txtPass = findViewById<View>(R.id.editTextPassword) as EditText
        _txtNote = findViewById<View>(R.id.editTextAdditional) as EditText
        openHelper = DatabaseHelper(this)
        val actionBar = actionBar
        actionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        _txtSite!!.requestFocus()
        _txtSite!!.isFocusableInTouchMode = true

        _btnInsert!!.setOnClickListener {
            val site = _txtSite!!.text.toString()
            val username = _txtUser!!.text.toString()
            val password = _txtPass!!.text.toString()
            val note = _txtNote!!.text.toString()
            db = (openHelper as DatabaseHelper).getWritableDatabase()
            insertData(site, username, password, note)
            _txtSite!!.setText("")
            _txtUser!!.setText("")
            _txtPass!!.setText("")
            _txtNote!!.setText("")
            Toast.makeText(applicationContext, "SAVED", Toast.LENGTH_SHORT).show()
        }
        _btnUpdate!!.setOnClickListener {
            val site = _txtSite!!.text.toString()
            val username = _txtUser!!.text.toString()
            val password = _txtPass!!.text.toString()
            val note = _txtNote!!.text.toString()
            db = (openHelper as DatabaseHelper).getWritableDatabase()
            updateData(site, username, password, note)
            _txtSite!!.setText("")
            _txtUser!!.setText("")
            _txtPass!!.setText("")
            _txtNote!!.setText("")
            Toast.makeText(applicationContext, "UPDATED", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertData(
        site: String?,
        username: String?,
        password: String?,
        note: String?
    ) {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.COL_1, site)
        contentValues.put(DatabaseHelper.COL_2, username)
        contentValues.put(DatabaseHelper.COL_3, password)
        contentValues.put(DatabaseHelper.COL_4, note)
        val id = db!!.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
    }

    fun updateData(
        site: String?,
        username: String?,
        password: String?,
        note: String?
    ): Boolean {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.COL_1, site)
        contentValues.put(DatabaseHelper.COL_2, username)
        contentValues.put(DatabaseHelper.COL_3, password)
        contentValues.put(DatabaseHelper.COL_4, note)
        val id = _txtSite!!.text.toString()
        return db!!.update(
            DatabaseHelper.TABLE_NAME,
            contentValues,
            DatabaseHelper.COL_1 + "=?",
            arrayOf(id)
        ) > 0
    }
}