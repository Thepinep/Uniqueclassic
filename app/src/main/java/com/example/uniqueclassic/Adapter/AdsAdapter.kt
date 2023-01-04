package com.example.uniqueclassic.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.google.firebase.storage.StorageReference


class AdsAdapter(
    private val CarRecycler2: ArrayList<AddModel>,
    private val storageReference: StorageReference?,
  /*  private val deleteButtonListener: DeleteButtonListener,
    private val editButtonListener: EditButtonListener,*/
) : RecyclerView.Adapter<AdsAdapter.MyViewHolder2>() {

    companion object {
        const val IMAGES_PATH = "images/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {

        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.user_adapter_item, parent, false)
        return MyViewHolder2(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {

        val currentitem = CarRecycler2[position]

        holder.Title.text = currentitem.etTitle
        holder.power.text = currentitem.etPower
        holder.year.text = currentitem.etYear
        holder.fuel.text = currentitem.etFuel
        holder.body.text = currentitem.etBody
        holder.zl.text = currentitem.etPrice
        holder.image.loadImg2(holder.itemView.context,storageReference, currentitem.etgalery[0])
        /*holder.deleteRes.setOnClickListener {
            deleteButtonListener.onClick(currentitem)
        }
        holder.editRes.setOnClickListener {
            editButtonListener.onClick(currentitem)
        }*/
    }

    override fun getItemCount(): Int {

        return CarRecycler2.size
    }


    class MyViewHolder2(itemView : View) : RecyclerView.ViewHolder(itemView){

        val Title : TextView = itemView.findViewById(R.id.title_text)
        val power : TextView = itemView.findViewById(R.id.power_text)
        val year : TextView = itemView.findViewById(R.id.year_text)
        val fuel : TextView = itemView.findViewById(R.id.fuel_text)
        val body : TextView = itemView.findViewById(R.id.body_text)
        val zl : TextView = itemView.findViewById(R.id.zl_text)
        val image : ImageView = itemView.findViewById(R.id.image_car)
//        val editRes: TextView = itemView.findViewById(R.id.edit_click)
 //       val deleteRes: TextView = itemView.findViewById(R.id.delete_click)



    }
}
fun ImageView.loadImg2(context: Context, storageReference: StorageReference?, imgName :String ){
    storageReference?.let {
        Glide.with(context)
            .load(it.child(("${AdsAdapter.IMAGES_PATH}$imgName")))
            .into(this)
    }
}

