package com.example.pluginrealm.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Notes: RealmObject(){

    private var id : Int? = null
    private var judul : String? = null
    private var deskripsi : String? = null


    // getter setter //
    // id
    fun setId(id:Int) {
        this.id =  id
    }
    fun getID():Int?{
        return id
    }


    // judul //
    fun setJudul(judul:String){
        this.judul=judul
    }
    fun getJudul():String?{
        return judul
    }


    // deskripsi //
    fun setDeskripsi(deskripsi:String){
        this.deskripsi=deskripsi
    }
    fun getDeskripsi():String?{
        return deskripsi
    }
}
