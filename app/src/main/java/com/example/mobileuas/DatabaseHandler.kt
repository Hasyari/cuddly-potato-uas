package com.example.mobileuas

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) :
    // sqlite connect
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // create data
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY AUTOINCREMENT, $NAMA_WISATA TEXT,$KOTA_WISATA TEXT,$DESKRIPSI_WISATA TEXT)"
        db?.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    // Inserting / Create data
    fun addWisata(wisata: Wisata): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAMA_WISATA, wisata.namaWisata)
        values.put(KOTA_WISATA, wisata.kotaWisata)
        values.put(DESKRIPSI_WISATA, wisata.deskripsiWisata)
        // memasukan data ke SQLite pada tabel users
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }
    @SuppressLint("Range")
    fun getAllWisata(): List<Wisata> {
        val wisataList = mutableListOf<Wisata>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(ID))
                val namaWisata = cursor.getString(cursor.getColumnIndex(NAMA_WISATA))
                val kotaWisata = cursor.getString(cursor.getColumnIndex(KOTA_WISATA))
                val deskripsiWisata = cursor.getString(cursor.getColumnIndex(DESKRIPSI_WISATA))

                val wisata = Wisata(id, namaWisata, kotaWisata, deskripsiWisata)
                wisataList.add(wisata)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return wisataList
    }
    // Variabel Mapping
    companion object {
        private val DB_NAME = "WisataDB"
        private val DB_VERSION = 1;
        private val TABLE_NAME = "wisata"
        private val ID = "id"
        private val NAMA_WISATA = "NamaWisata"
        private val KOTA_WISATA = "KotaWisata"
        private val DESKRIPSI_WISATA = "DeskripsiWisata"
    }
}