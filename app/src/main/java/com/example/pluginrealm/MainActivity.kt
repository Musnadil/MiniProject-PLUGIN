package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pluginrealm.adapter.notes_adapter
import com.example.pluginrealm.model.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapternotes:notes_adapter
    private lateinit var realm : Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotes()
        initView()
        getAllnote()
    }
    fun createNotes(){
        btn_createNotes.setOnClickListener {
            startActivity(Intent(this,AddNotesActivity::class.java))
        }
    }


    private fun getAllnote() {
        realm.where(Notes::class.java).findAll().let {
            adapternotes.setNotes(it)
        }
    }

    private fun initView(){
        rv_notes.layoutManager = lm
        adapternotes = notes_adapter(mutableListOf(),object: notes_adapter.onAdapterClick{
            override fun onClick(note: Notes) {
                startActivity(Intent(this@MainActivity,Update_DeleteNotesActivity::class.java)
                    .putExtra("id",note.getID())
                    .putExtra("judul",note.getJudul())
                    .putExtra("deskripsi",note.getDeskripsi()))
            }
        })
        rv_notes.adapter = adapternotes
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllnote()
    }
}
