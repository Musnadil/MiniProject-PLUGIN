package com.example.pluginrealm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pluginrealm.R
import com.example.pluginrealm.model.user

class user_adapter(val context: Context):RecyclerView.Adapter<user_adapter.userViewHolder>() {
    private var users : MutableList<user> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): user_adapter.userViewHolder {
        return userViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: user_adapter.userViewHolder, position: Int) {
        holder.bindModel(users[position])
    }
    fun setUser(data:List<user>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class userViewHolder(i: View) : RecyclerView.ViewHolder(i) {

        val tvid: TextView = i.findViewById(R.id.tv_id)
        val tvbarang: TextView = i.findViewById(R.id.tv_nama_barang)
        val tvharga: TextView = i.findViewById(R.id.tv_harga)

        fun bindModel(u:user){
            tvid.text = u.getId().toString()
            tvbarang.text = u.getNama_barang()
            tvharga.text = u.getHarga().toString()
        }
    }
}