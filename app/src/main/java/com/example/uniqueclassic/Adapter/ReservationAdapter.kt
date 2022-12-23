package com.example.uniqueclassic.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Model.Rent
import com.example.uniqueclassic.R


class ReservationAdapter (private val CarRecycler3:ArrayList<Rent>):
RecyclerView.Adapter<ReservationAdapter.MyViewHolder3>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {

        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.reservations_adapter_item, parent, false)
        return MyViewHolder3(itemView,mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {

        val currentitem = CarRecycler3[position]

        holder.Title2.text = currentitem.title
        holder.Tenant.text = currentitem.etTenant
        holder.StartDate.text = currentitem.etStartDate
        holder.EndDate.text = currentitem.etEndDate
        holder.Cost.text = currentitem.etCost
       // holder.Phone.text = currentitem.etPhone


    }

    override fun getItemCount(): Int {

        return CarRecycler3.size
    }


    class MyViewHolder3(itemView : View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val Title2 : TextView = itemView.findViewById(R.id.title_text)
        val Tenant : TextView = itemView.findViewById(R.id.tenant_text)
        val StartDate : TextView = itemView.findViewById(R.id.start_text)
        val EndDate : TextView = itemView.findViewById(R.id.end_text)
        val Cost : TextView = itemView.findViewById(R.id.cost_text)
      //  val Phone : TextView = itemView.findViewById(R.id.phone_tenant_text)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}
