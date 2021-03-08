package com.example.pluginrealm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pluginrealm.R
import com.example.pluginrealm.model.Notes
import io.realm.RealmResults
import kotlinx.android.synthetic.main.notes_rv_layout.view.*

class notes_adapter(private val context: Context?, private val notesList: RealmResults<Notes>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout,parent,false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.tv_judul.text = notesList[position]!!.judul
        holder.itemView.tv_deskripsi.text = notesList[position]!!.deskripsi
        holder.itemView.tv_id.text = notesList[position]!!.id.toString()

    }

    class ViewHolder(v:View?):RecyclerView.ViewHolder(v!!){
        val judul = itemView.findViewById<TextView>(R.id.tv_judul)
        val deskripsi = itemView.findViewById<TextView>(R.id.tv_deskripsi)
        val id = itemView.findViewById<TextView>(R.id.tv_id)
    }

}