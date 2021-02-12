package com.example.pluginrealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pluginrealm.adapter.user_adapter
import com.example.pluginrealm.model.user
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_view_data.*

class view_data : AppCompatActivity() {
    lateinit var useradapter: user_adapter
    lateinit var data: Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        showdata()
    }
    private fun showdata(){
        rycle.layoutManager = lm
        useradapter = user_adapter(this)
        rycle.adapter = useradapter
        Realm.init(applicationContext)
        data= Realm.getDefaultInstance()
        getuser()
    }
    private fun getuser(){
        data.where(user::class.java).findAll().let {
            useradapter.setUser(it)
        }
    }
}