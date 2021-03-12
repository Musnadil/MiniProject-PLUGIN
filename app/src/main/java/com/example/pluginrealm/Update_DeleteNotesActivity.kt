package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pluginrealm.model.Notes
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.activity_add_notes.et_deskripsi
import kotlinx.android.synthetic.main.activity_add_notes.et_judul
import kotlinx.android.synthetic.main.activity_update__delete_notes.*

class Update_DeleteNotesActivity : AppCompatActivity() {
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__delete_notes)
        getDataIntent()
        setUpRealm()
        delete()
        update()
    }
    private fun getDataIntent(){
        et_judul.setText((intent.getStringExtra("judul")))
        et_deskripsi.setText((intent.getStringExtra("deskripsi")))
    }

    private fun setUpRealm(){
        realm = Realm.getDefaultInstance()
    }

    private fun delete(){
        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(Notes::class.java).equalTo("id",intent.getIntExtra("id",1)).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    
    private fun update(){
        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(Notes::class.java).equalTo("id",intent.getIntExtra("id",1)).findFirst().let{
                it!!.setJudul(et_judul.text.toString())
                it!!.setDeskripsi(et_deskripsi.text.toString())
            }
            realm.commitTransaction()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}