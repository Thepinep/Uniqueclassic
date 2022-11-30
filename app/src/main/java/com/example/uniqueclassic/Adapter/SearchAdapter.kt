package com.example.uniqueclassic.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R

class SearchAdapter (private val CarRecycler : ArrayList<AddModel>) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.search_adapter_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = CarRecycler[position]

        holder.title.text = currentitem.etTitle
        holder.power.text = currentitem.etPower
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice

    }

    override fun getItemCount(): Int {

        return CarRecycler.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.title_text)
        val power : TextView = itemView.findViewById(R.id.power_text)
        val year : TextView = itemView.findViewById(R.id.year_text)
        val fuel : TextView = itemView.findViewById(R.id.fuel_text)
        val body : TextView = itemView.findViewById(R.id.body_text)
        val zl : TextView = itemView.findViewById(R.id.zl_text)


    }
}