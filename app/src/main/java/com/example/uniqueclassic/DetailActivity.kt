package com.example.uniqueclassic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.ImageView

class DetailActivity : AppCompatActivity() {

//    private lateinit var simpleGallery: Gallery
//
//    // CustomizedGalleryAdapter is a java class which extends BaseAdapter
//    // and implement the override methods.
//    private lateinit var customGalleryAdapter: CustomizedGalleryAdapter
//    private lateinit var selectedImageView: ImageView
//
//    // To show the selected language, we need this
//    // array of images, here taken 10 different kind of
//    // most popular programming languages
//    private var images = intArrayOf(
//        R.drawable.search_image,
//        R.drawable.add_button
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       /* simpleGallery = findViewById<View>(R.id.languagesGallery) as Gallery

        // get the reference of ImageView
        selectedImageView = findViewById<View>(R.id.imageView) as ImageView

        // initialize the adapter
        customGalleryAdapter = CustomizedGalleryAdapter(applicationContext, images)

        // set the adapter for gallery
        simpleGallery.adapter = customGalleryAdapter

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener { parent, view, position, id ->
            // Whichever image is clicked, that is set in the  selectedImageView
            // position will indicate the location of image
            selectedImageView.setImageResource(images[position])
        }*/
    }
}