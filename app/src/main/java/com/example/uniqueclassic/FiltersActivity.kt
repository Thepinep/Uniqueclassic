package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.uniqueclassic.databinding.ActivityFiltersBinding
import com.example.uniqueclassic.databinding.ActivityLoginBinding

class FiltersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltersBinding

    val vehicles by lazy { resources.getStringArray(R.array.Vehicle) }

    var selected: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            val priceMin = binding.priceMin.text.toString()
            val priceMax = binding.priceMax.text.toString()
            val yearMin = binding.yearMin.text.toString()
            val yearMax = binding.yearMax.text.toString()
            intent.putExtra("marka", selected)
            intent.putExtra("priceMin",priceMin.ifEmpty { null })
            intent.putExtra("priceMax", priceMax.ifEmpty { null })
            intent.putExtra("yearMin", yearMin.ifEmpty { null })
            intent.putExtra("yearMax", yearMax.ifEmpty { null })
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, vehicles)
        binding.AutoCompleteTextview2.setAdapter(arrayAdapter)
        binding.AutoCompleteTextview2.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected = vehicles[p2]
            }
        }
    }
}