package com.example.passking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CustomAdapter internal constructor(
    private val context: Context,
    private val _site: ArrayList<*>,
    private val _username: ArrayList<*>,
    private val _password: ArrayList<*>,
    private val _note: ArrayList<*>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder._site_.text = _site[position].toString()
        holder._username_.text = "User : " + _username[position].toString()
        holder._password_.text = "Password : " + _password[position].toString()
        holder._note_.text = "Note : " + _note[position].toString()
    }

    override fun getItemCount(): Int {
        return _site.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _site_: TextView
        var _username_: TextView
        var _password_: TextView
        var _note_: TextView

        init {
            _site_ = itemView.findViewById(R.id._site_)
            _username_ = itemView.findViewById(R.id._username_)
            _password_ = itemView.findViewById(R.id._password_)
            _note_ = itemView.findViewById(R.id._note_)
        }
    }

}