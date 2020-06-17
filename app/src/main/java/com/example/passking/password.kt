package com.example.passking

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class password : Activity() {
    var passw: EditText? = null
    var passButton: Button? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password)
        passw = findViewById<View>(R.id.passwordText) as EditText
        passButton = findViewById<View>(R.id.pass_bt) as Button
        val sp =
            getSharedPreferences("Login", Context.MODE_PRIVATE)
        val Ed = sp.edit()
        val fir =
            getSharedPreferences("Fir", Context.MODE_PRIVATE)
        val firEd = fir.edit()
        if (fir.getBoolean("my_first_time", true)) {
            Ed.putString("Psw", "8989") //if yes.Then password is Login password is set to 8989
            Ed.commit() // write the value..
            Toast.makeText(baseContext, "Initial Password is 8989", Toast.LENGTH_SHORT).show()
            fir.edit().putBoolean("my_first_time", false).commit()
        }
        passButton!!.setOnClickListener {
            if (passw!!.text.toString().trim { it <= ' ' }
                == getSharedPreferences(
                    "Login",
                    Context.MODE_PRIVATE
                ).getString("Psw", null)!!.trim { it <= ' ' }
            ) {

                // if password is correct, we login to the main screen.
                val intent = Intent(this@password, PasswordManagerActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(baseContext, "Wrong Password", Toast.LENGTH_LONG).show()
            }
        }
    }
}