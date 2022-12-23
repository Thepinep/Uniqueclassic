
package com.example.uniqueclassic.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Model.Rent
import com.example.uniqueclassic.R

class RentalAdapter(private val CarRecycler4: List<Rent>?):
    RecyclerView.Adapter<RentalAdapter.MyViewHolder4>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder4 {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.rental_adapter_item, parent, false)
        return MyViewHolder4(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder4, position: Int) {
        val currentitem = CarRecycler4?.get(position)
        currentitem?.let {
            holder.Title3.text = currentitem.title
            holder.Tenant.text = currentitem.buyName
            holder.StartDate.text = currentitem.etStartDate
            holder.EndDate.text = currentitem.etEndDate
            holder.Cost.text = currentitem.etCost
            holder.Phone.text = currentitem.buyPhone
        }
    }

    override fun getItemCount(): Int = CarRecycler4?.size ?: 0

    class MyViewHolder4(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Title3 : TextView = itemView.findViewById(R.id.title3_text)
        val Tenant : TextView = itemView.findViewById(R.id.user_buy_text)
        val StartDate : TextView = itemView.findViewById(R.id.start2_text)
        val EndDate : TextView = itemView.findViewById(R.id.end2_text)
        val Cost : TextView = itemView.findViewById(R.id.cost2_text)
        val Phone : TextView = itemView.findViewById(R.id.phone_buy_text)
    }
}
