package com.example.mobileuas

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WisataAdapter(private val wisataList: ArrayList<Wisata>) :
    RecyclerView.Adapter<WisataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_wisata,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val wisata = wisataList[position]
        holder.namaCard.text = wisataList[position].namaWisata
        holder.kotaCard.text = wisataList[position].kotaWisata
        holder.deskripsiCard.text = wisataList[position].deskripsiWisata
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("wisata_id", wisata.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaCard: TextView = itemView.findViewById(R.id.nama_card)
        var kotaCard: TextView = itemView.findViewById(R.id.kota_card)
        var deskripsiCard: TextView = itemView.findViewById(R.id.deskripsi_card)
    }
}