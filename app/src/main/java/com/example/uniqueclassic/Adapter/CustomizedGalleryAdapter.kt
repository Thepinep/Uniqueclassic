package com.example.uniqueclassic.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.uniqueclassic.R
import com.google.firebase.storage.StorageReference

class CustomizedGalleryAdapter(
    private val context: Context,
    private  val storageReference: StorageReference,
    private val images: List<String>
) : BaseAdapter() {

    // returns the number of images, in our example it is 10
    override fun getCount(): Int {
        return images.size
    }

    // returns the Item  of an item, i.e. for our example we can get the image
    override fun getItem(position: Int): String {
        return images[position]
    }

    // returns the ID of an item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // returns an ImageView view

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val newItem = inflater.inflate(R.layout.car_detail_item, parent, false)
            newItem.findViewById<ImageView>(R.id.car_detail_image)
                .loadImg(context, storageReference ,images[position])
            newItem
        }
        else {
            convertView.findViewById<ImageView>(R.id.car_detail_image)
                .loadImg(context, storageReference ,images[position])
            convertView
        }
        return imageView
    }

}
