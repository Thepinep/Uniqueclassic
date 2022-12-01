package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.uniqueclassic.databinding.ActivityFiltersBinding
import com.example.uniqueclassic.databinding.ActivityLoginBinding

class FiltersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltersBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
    //        val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val vehicle = resources.getStringArray(R.array.Vehicle)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, vehicle)
        binding.AutoCompleteTextview.setAdapter(arrayAdapter)
    }
}