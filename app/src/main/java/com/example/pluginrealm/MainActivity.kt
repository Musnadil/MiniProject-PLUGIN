package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pluginrealm.adapter.user_adapter
import com.example.pluginrealm.model.user
import com.example.pluginrealm.view_data
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_data.*

class MainActivity : AppCompatActivity() {
    lateinit var useradapter: user_adapter
    lateinit var data:Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambah()
//        initview()

        btn_show.setOnClickListener {
            startActivity(Intent(this,view_data::class.java))
        }
        }
    private fun getAllUser(){
        data.where(user::class.java).findAll().let {
            useradapter.setUser(it)
        }
    }
//    private fun initview(){
//        show.layoutManager = lm
//        useradapter = user_adapter(this)
//        show.adapter = useradapter
//        Realm.init(applicationContext)
//        data= Realm.getDefaultInstance()
//        getAllUser()
//    }
    fun tambah(){
        btn_add.setOnClickListener {
            data.beginTransaction()
            var count = 0
            data.where(user::class.java).findAll().let{
                for(i in it){
                    count++
                }
            }
            try{
                var User = data.createObject(user::class.java)
                User.setId(count+1)
                User.setNama_barang(et_barang.text.toString())
                User.setHarga(et_harga.text.toString().toInt())
                et_barang.setText("")
                et_harga.setText("")
                data.commitTransaction()
                getAllUser()
            }catch (e:RealmException){
            }
        }
    }
}
