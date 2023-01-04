package com.example.uniqueclassic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class EditAddActivity : AppCompatActivity() {

    private  lateinit var tvTitle: TextView
    private  lateinit var tvLocation: TextView
    private  lateinit var tvPrice: TextView
    private  lateinit var tvDescription: TextView
    private lateinit var tvPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_add)
        initView()
        setValuesToViews()

    }
    private fun initView() {
        tvTitle = findViewById(R.id.textInputEditTitle2)
        tvLocation = findViewById(R.id.textInputEditLocation2)
        tvPrice = findViewById(R.id.textInputEditPrice2)
        tvPhone = findViewById(R.id.textInputEditPhone2)
        tvDescription  = findViewById(R.id.textInputEditDescription2)

    }

    private fun setValuesToViews(){
        tvTitle.text = intent.getStringExtra("etTitle")
        tvLocation.text = intent.getStringExtra("etLocation")
        tvPrice.text = intent.getStringExtra("etPrice")
        tvDescription.text = intent.getStringExtra("etDescription")
        tvPhone.text = intent.getStringExtra("etPhone")



    }
}
