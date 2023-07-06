package com.example.mobileuas

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WisataAdapter(private val wisataList: ArrayList<Wisata>) : RecyclerView.Adapter<WisataAdapter.    MyViewHolder> {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return wisataList.size

    }
}