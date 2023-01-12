package com.example.uniqueclassic.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.google.firebase.storage.StorageReference


class FavouriteAdapter (
    private val CarRecycler4: ArrayList<AddModel>,
    private val storageReference: StorageReference?,
)
    : RecyclerView.Adapter<FavouriteAdapter.MyViewHolder5>() {

    companion object {
        const val IMAGES_PATH = "images/"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder5 {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_adapter_item, parent, false)
        return MyViewHolder5(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder5, position: Int) {
        val currentitem = CarRecycler4[position]

        holder.title.text = currentitem.etTitle
        holder.power.text = currentitem.etPower
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice
        holder.image.loadImg3(holder.itemView.context, storageReference, currentitem.etgalery[0])
    }

    override fun getItemCount(): Int {
        return CarRecycler4.size
    }

    class MyViewHolder5(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title_text)
        val power: TextView = itemView.findViewById(R.id.power_text)
        val year: TextView = itemView.findViewById(R.id.year_text)
        val fuel: TextView = itemView.findViewById(R.id.fuel_text)
        val body: TextView = itemView.findViewById(R.id.body_text)
        val zl: TextView = itemView.findViewById(R.id.zl_text)
        val image: ImageView = itemView.findViewById(R.id.image_car)
    //    val bookBtn: TextView = itemView.findViewById(R.id.book_button)
   //     val serce: CheckBox = itemView.findViewById(R.id.serce)

    }
}

fun ImageView.loadImg3(context: Context, storageReference: StorageReference?, imgName :String ) {
    storageReference?.let {
        Glide.with(context)
            .load(it.child(("${SearchAdapter.IMAGES_PATH}$imgName")))
            .into(this)
    }
}