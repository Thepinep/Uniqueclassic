package com.example.uniqueclassic.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uniqueclassic.BookButtonListener
import com.example.uniqueclassic.HeartButtonListener
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.google.firebase.storage.StorageReference

class SearchAdapter(
    private val CarRecycler: ArrayList<AddModel>,
    private val storageReference: StorageReference?,
    private val bookButtonListener: BookButtonListener,
    private val heartButtonListener: HeartButtonListener,
) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    companion object {
        const val IMAGES_PATH = "images/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.search_adapter_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem: AddModel = CarRecycler[position]

        holder.title.text = currentitem.etTitle
        holder.power.text = currentitem.etPower
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice
        holder.image.loadImg(holder.itemView.context,storageReference, currentitem.etgalery[0])
        holder.bookBtn.setOnClickListener {
            bookButtonListener.onClick(currentitem)
        }
        holder.serce.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                heartButtonListener.onChanged(currentitem, p1)
            }
        })
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
        val image : ImageView = itemView.findViewById(R.id.image_car)
        val bookBtn: TextView = itemView.findViewById(R.id.book_button)
        val serce: CheckBox = itemView.findViewById(R.id.serce)
    }
}
fun ImageView.loadImg(context: Context, storageReference: StorageReference?, imgName :String ){
    storageReference?.let {
        Glide.with(context)
            .load(it.child(("${SearchAdapter.IMAGES_PATH}$imgName")))
            .into(this)
    }
}