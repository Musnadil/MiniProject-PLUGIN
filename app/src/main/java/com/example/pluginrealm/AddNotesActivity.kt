package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    }
    private fun addNotesToDB() {
        btn_save.setOnClickListener {
            if(et_judul.text.toString().isEmpty()){
                Toast.makeText(this,"Judul harus diisi",Toast.LENGTH_SHORT).show()
            }else if(et_deskripsi.text.toString().isEmpty()){
                Toast.makeText(this,"Deskripsi harus diisi",Toast.LENGTH_SHORT).show()
            }else{
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
                Toast.makeText(this,"Catatan telah ditambahkan",Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun setUpRealm(){
        realm = Realm.getDefaultInstance()
    }
}
