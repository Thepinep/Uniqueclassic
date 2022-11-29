package com.example.uniqueclassic.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R

class SearchAdapter (private val CarRecycler : ArrayList<AddModel>) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.search_adapter_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = CarRecycler[position]

        holder.Title.text = currentitem.etTitle
        holder.kilometer.text = currentitem.etKilometre
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice

    }

    override fun getItemCount(): Int {

        return CarRecycler.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val Title : TextView = itemView.findViewById(R.id.title_text)
        val kilometer : TextView = itemView.findViewById(R.id.kilometre_text)
        val year : TextView = itemView.findViewById(R.id.year_text)
        val fuel : TextView = itemView.findViewById(R.id.fuel_text)
        val body : TextView = itemView.findViewById(R.id.body_text)
        val zl : TextView = itemView.findViewById(R.id.zl_text)


    }
}