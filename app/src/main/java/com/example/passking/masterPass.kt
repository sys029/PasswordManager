package com.example.passking

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class masterPass : Activity() {
    var cur: EditText? = null
    var new1: EditText? = null
    var rep: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.masterpass)
        val actionBar = actionBar
        actionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        cur = findViewById<View>(R.id.etCurrent) as EditText
        new1 = findViewById<View>(R.id.etNew) as EditText
        rep = findViewById<View>(R.id.etRepeat) as EditText
        cur!!.requestFocus()
        cur!!.isFocusableInTouchMode = true
        val save: Button
        save = findViewById<View>(R.id.bSave) as Button
        save.setOnClickListener {
            val Scur: String
            val Snew1: String
            val Srep: String
            Scur = cur!!.text.toString()
            Snew1 = new1!!.text.toString()
            Srep = rep!!.text.toString()
            if (Scur == "" || Snew1 == "" || Srep == "") {
                Toast.makeText(
                    baseContext, "Enter all the Fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                // Get Value from Share preference
                val sp1 = getSharedPreferences("Login", 0)
                val pass = sp1.getString("Psw", null)

                // Update the Share Preference values:
                if (pass == Scur) {
                    if (Snew1 == Srep) {
                        val Ed = sp1.edit()
                        Ed.putString("Psw", Snew1)
                        Ed.commit()
                        Toast.makeText(
                            baseContext,
                            "Master Password Changed",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext,
                            "New Pass != Repeat Pass",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        baseContext,
                        "Wrong Current Password!", Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}