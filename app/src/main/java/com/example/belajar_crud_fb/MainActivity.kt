package com.example.belajar_crud_fb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        val BtnSave = findViewById<Button>(R.id.btnsave)

        BtnSave.setOnClickListener {
            saveData()
        }

    }

    private fun saveData() {
        val inputNama = findViewById<EditText>(R.id.nama)
        val inputStatus = findViewById<EditText>(R.id.status)

        val nama = inputNama.text.toString()
        val status= ref.push().key.toString()

        val user = Users(nama,status)
        val userId = ref.push().key.toString()

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputStatus.setText("")
        }
    }
}