package com.example.uniqueclassic.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.storage.StorageReference


class SearchAdapter(
    private val CarRecycler: ArrayList<AddModel>,
    private val storageReference: StorageReference?
) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    companion object {
        const val IMAGES_PATH = "images/"
    }

    private lateinit var mListener: onItemClickListener
    private lateinit var button: Button


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){

        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.search_adapter_item, parent, false)
        return MyViewHolder(itemView,mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = CarRecycler[position]

        holder.title.text = currentitem.etTitle
        holder.power.text = currentitem.etPower
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice
        holder.image.loadImg(holder.itemView.context,storageReference, currentitem.etgalery[0])

    }


    override fun getItemCount(): Int {

        return CarRecycler.size
    }


    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.title_text)
        val power : TextView = itemView.findViewById(R.id.power_text)
        val year : TextView = itemView.findViewById(R.id.year_text)
        val fuel : TextView = itemView.findViewById(R.id.fuel_text)
        val body : TextView = itemView.findViewById(R.id.body_text)
        val zl : TextView = itemView.findViewById(R.id.zl_text)
        val image : ImageView = itemView.findViewById(R.id.image_car)


        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)



            }
        }
    }
}
fun ImageView.loadImg(context: Context, storageReference: StorageReference?, imgName :String ){
    storageReference?.let {
        Glide.with(context)
            .load(it.child(("${SearchAdapter.IMAGES_PATH}$imgName")))
            .into(this)
    }
}