package com.example.pluginrealm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Notes(
    @PrimaryKey
    var id:Int?=null,
    var judul:String?=null,
    var deskripsi:String?=null
): RealmObject()