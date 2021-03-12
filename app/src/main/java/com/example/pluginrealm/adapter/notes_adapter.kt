package com.example.pluginrealm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pluginrealm.R
import com.example.pluginrealm.model.Notes

class notes_adapter(private val notesList: MutableList<Notes>,private val listener: notes_adapter.onAdapterClick)
    : RecyclerView.Adapter<notes_adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout,parent,false))

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notes_adapter.ViewHolder, position: Int) {
        holder.bind(notesList[position])
        holder.itemView.setOnClickListener{
            listener.onClick(notesList[position])
        }
    }

    fun setNotes(data:List<Notes>){
        notesList.clear()
        notesList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(v:View):RecyclerView.ViewHolder(v){

        val tvId: TextView = v.findViewById(R.id.tv_id)
        val tvJudul: TextView = v.findViewById(R.id.tv_judul)
        val tvDeskripsi: TextView = v.findViewById(R.id.tv_deskripsi)

        fun bind(n:Notes){
            tvId.text = n.getID().toString()
            tvJudul.text = n.getJudul()
            tvDeskripsi.text = n.getDeskripsi()
        }
    }
    interface onAdapterClick{
        fun onClick(note:Notes)
    }
}