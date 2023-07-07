package com.example.mobileuas

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {
    private lateinit var databaseHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        val id = intent.getLongExtra("wisata_id", 0)
        val namaWisata = findViewById<TextView>(R.id.detailnama)
        val kotaWisata = findViewById<TextView>(R.id.detailkota)
        val deskripsiWisata = findViewById<TextView>(R.id.detaildeskripsi)


        databaseHandler = DatabaseHandler(this)

        // Call the SQLite function
        val result = databaseHandler.getWisataById(id)

        if (result != null) {
            namaWisata.text = result.namaWisata
            kotaWisata.text = result.kotaWisata
            deskripsiWisata.text = result.deskripsiWisata
        } else {
            Log.d("Error", "SQLite Error")
        }

        // Close the DatabaseHandler
        databaseHandler.close()
    }
}