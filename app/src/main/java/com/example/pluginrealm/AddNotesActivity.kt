package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pluginrealm.model.Notes
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotesActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        setUpRealm()
        addNotesToDB()
        getDataIntent()
        update()
        delete()

    }

    private fun addNotesToDB() {
        btn_save.setOnClickListener {
            realm.beginTransaction()
            var currentId = realm.where(Notes::class.java).max("id")
            var nextId = if (currentId == null) 1 else currentId.toInt() + 1
            var notes = realm.createObject(Notes::class.java)
            notes.setId(nextId)
            notes.setJudul(et_judul.text.toString())
            notes.setDeskripsi(et_deskripsi.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            realm.commitTransaction()
        }
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

    private fun setUpRealm(){
        realm = Realm.getDefaultInstance()
    }
    private fun getDataIntent(){
        et_judul.setText((intent.getStringExtra("judul")))
        et_deskripsi.setText((intent.getStringExtra("deskripsi")))

    }

}
