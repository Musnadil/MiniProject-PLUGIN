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


class MainActivity : AppCompatActivity() {
    private lateinit var btn_createNotes: FloatingActionButton
    private lateinit var rv_notes: RecyclerView
    private lateinit var notesList: ArrayList<Notes>
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init views
        realm = Realm.getDefaultInstance()
        btn_createNotes = findViewById(R.id.btn_createNotes)
        rv_notes = findViewById(R.id.rv_notes)

        //on click btn_createNotes
         btn_createNotes.setOnClickListener {
             startActivity(Intent(this,AddNotesActivity::class.java))
             finish()
         }

        rv_notes.layoutManager=LinearLayoutManager(this)
        getAllNotes()

    }

    private fun getAllNotes() {

        notesList = ArrayList()
        val result:RealmResults<Notes> = realm.where<Notes>(Notes::class.java).findAll()
        rv_notes.adapter=notes_adapter(this,result)
        rv_notes.adapter!!.notifyDataSetChanged()


    }
}
