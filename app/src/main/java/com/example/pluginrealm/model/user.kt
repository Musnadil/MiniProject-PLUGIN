package com.example.pluginrealm.model

import io.realm.RealmObject

open class user: RealmObject() {
    private var id:Int=0
    private var nama_barang:String=""
    private var harga:Int = 0


    fun setId(idz:Int){
        this.id = id
    }
    fun getId():Int{
        return id
    }

    fun setNama_barang(nama_barang:String){
        this.nama_barang = nama_barang
    }

    fun getNama_barang():String{
        return nama_barang
    }

    fun setHarga(harga:Int){
        this.harga = harga
    }
    fun getHarga():Int{
        return harga
    }
}