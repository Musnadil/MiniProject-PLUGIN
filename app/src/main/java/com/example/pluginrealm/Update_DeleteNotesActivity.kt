package com.example.pluginrealm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.apply {
                setTitle("Konfirmasi")
                setMessage("Anda yakin ingin menghapusnya?")
                setNegativeButton("Tidak"){dialog, which ->
                    dialog.dismiss()
                }
                setPositiveButton("Ya"){ dialog, which ->
                    dialog.dismiss()
                    realm.beginTransaction()
                    realm.where(Notes::class.java).equalTo("id",intent.getIntExtra("id",1)).findFirst().let {
                        it!!.deleteFromRealm()
                    }
                    realm.commitTransaction()
                    startActivity(Intent(this@Update_DeleteNotesActivity,MainActivity::class.java))
                }
            }
            alertDialog.show()
        }

    }
    
    private fun update(){
        btn_update.setOnClickListener {
            if (et_judul.text.toString().isEmpty()){
                Toast.makeText(this,"Judul harus diisi",Toast.LENGTH_SHORT).show()
            }else if (et_deskripsi.text.toString().isEmpty()){
                Toast.makeText(this,"Deskripsi harus diisi",Toast.LENGTH_SHORT).show()
            }else {
                realm.beginTransaction()
                realm.where(Notes::class.java).equalTo("id", intent.getIntExtra("id", 1))
                    .findFirst().let {
                    it!!.setJudul(et_judul.text.toString())
                    it!!.setDeskripsi(et_deskripsi.text.toString())
                }
                realm.commitTransaction()
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this,"Anda telah mengubah catatan",Toast.LENGTH_SHORT).show()
            }
        }
    }
}