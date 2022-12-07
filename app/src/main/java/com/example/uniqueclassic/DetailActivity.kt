package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Gallery

import android.widget.ImageView
import android.widget.TextView
import com.example.uniqueclassic.Adapter.CustomizedGalleryAdapter
import com.example.uniqueclassic.Adapter.loadImg
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DetailActivity : AppCompatActivity() {

    private  lateinit var tvTitle: TextView
    private  lateinit var tvLocation: TextView
    private  lateinit var tvPrice: TextView
    private  lateinit var tvPower: TextView
    private  lateinit var tvTransmission: TextView
    private  lateinit var tvFuel: TextView
    private  lateinit var tvSeller: TextView
    private  lateinit var tvVin: TextView
    private  lateinit var tvYear: TextView
    private  lateinit var tvCubic: TextView
    private  lateinit var tvBody: TextView
    private  lateinit var tvKilometre: TextView
    private  lateinit var tvColor: TextView
    private  lateinit var tvCountry: TextView
    private  lateinit var tvWheel: TextView
    private  lateinit var tvUsername: TextView
    private  lateinit var tvDescription: TextView

    private lateinit var simpleGallery: Gallery

    // CustomizedGalleryAdapter is a java class which extends BaseAdapter
    // and implement the override methods.
    private lateinit var customGalleryAdapter: CustomizedGalleryAdapter
    private lateinit var selectedImageView: ImageView

    // To show the selected language, we need this
    // array of images, here taken 10 different kind of
    // most popular programming languages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val buttonClick = findViewById<ImageView>(R.id.ButtonBack1)
        buttonClick.setOnClickListener {
            finish()
        }

        initView()
        setValuesToViews()




        simpleGallery = findViewById<View>(R.id.languagesGallery) as Gallery

        // get the reference of ImageView
        selectedImageView = findViewById<View>(R.id.Imageoff) as ImageView


        val storageReference = Firebase.storage.reference
        val list_gallery =intent.getStringArrayExtra("etgalery")?.asList() ?: emptyList()



        // initialize the adapter
        customGalleryAdapter = CustomizedGalleryAdapter(applicationContext,storageReference, list_gallery)

        // set the adapter for gallery
        simpleGallery.adapter = customGalleryAdapter

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener { parent, view, position, id ->
            // Whichever image is clicked, that is set in the  selectedImageView
            // position will indicate the location of image
            selectedImageView.loadImg(this, storageReference ,list_gallery[position])
        }
    }

    private fun initView() {
        tvTitle = findViewById(R.id.text_title)
        tvLocation = findViewById(R.id.text_Location)
        tvPrice = findViewById(R.id.pln_text)
        tvPower = findViewById(R.id.powerdeatal_text)
        tvTransmission = findViewById(R.id.transmission_text)
        tvFuel = findViewById(R.id.fueldetail_text)
        tvSeller = findViewById(R.id.seller_text)
        tvVin = findViewById(R.id.vin_text)
        tvYear = findViewById(R.id.yearproduction_text)
        tvCubic = findViewById(R.id.cubic_text)
        tvBody = findViewById(R.id.body_text)
        tvKilometre = findViewById(R.id.kilometre_text)
        tvColor = findViewById(R.id.color_text)
        tvCountry = findViewById(R.id.home_text)
        tvWheel = findViewById(R.id.wheel_text)
        tvUsername  = findViewById(R.id.user_text)
        tvDescription  = findViewById(R.id.description_text)

    }

    private fun setValuesToViews(){
        tvTitle.text = intent.getStringExtra("etTitle")
        tvLocation.text = intent.getStringExtra("etLocation")
        tvPrice.text = intent.getStringExtra("etPrice")
        tvPower.text = intent.getStringExtra("etPower")
        tvTransmission.text = intent.getStringExtra("etTransmission")
        tvFuel.text = intent.getStringExtra("etFuel")
        tvSeller.text = intent.getStringExtra("etSeller")
        tvVin.text = intent.getStringExtra("etVin")
        tvYear.text = intent.getStringExtra("etYear")
        tvCubic.text = intent.getStringExtra("etCubic")
        tvBody.text = intent.getStringExtra("etBody")
        tvKilometre.text = intent.getStringExtra("etKilometre")
        tvColor.text = intent.getStringExtra("etColor")
        tvCountry.text = intent.getStringExtra("etCountry")
        tvWheel.text = intent.getStringExtra("etWheel")
        tvUsername.text = intent.getStringExtra("etUsername")
        tvDescription.text = intent.getStringExtra("etDescription")

    }
}
