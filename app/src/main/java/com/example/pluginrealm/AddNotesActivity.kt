package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pluginrealm.model.Notes
import io.realm.Realm
import java.lang.Exception

class AddNotesActivity : AppCompatActivity() {

    private lateinit var judulET:EditText
    private lateinit var deskripsiET:EditText
    private lateinit var saveBTN:Button
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        //init views
        realm = Realm.getDefaultInstance()
        judulET=findViewById(R.id.et_judul)
        deskripsiET=findViewById(R.id.et_deskripsi)
        saveBTN=findViewById(R.id.btn_save)

        // onClicklistener

        saveBTN.setOnClickListener {
            addNotesToDB()
        }

    }

    private fun addNotesToDB() {

        try {
            // Auto increment ID
            realm.beginTransaction()
            val currentIdNumber : Number? = realm.where(Notes::class.java).max("id")
            val nextID : Int

            nextID=if(currentIdNumber == null){
                1
            }else{
                currentIdNumber.toInt()+1
            }


            val notes = Notes()

            notes.judul = judulET.text.toString()
            notes.deskripsi = deskripsiET.text.toString()
            notes.id = nextID


            //copy to realm
            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()


            Toast.makeText(this,"Notes telah ditambahkan",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,MainActivity::class.java))
            finish()


        }catch (e:Exception){
            Toast.makeText(this,"Failed $e",Toast.LENGTH_SHORT).show()
        }


    }
}