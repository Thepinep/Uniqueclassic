package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.SearchAdapter
import com.example.uniqueclassic.Model.AddModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SearchActivity : AppCompatActivity(), BookButtonListener, HeartButtonListener {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview : RecyclerView
    private var mAdapter: SearchAdapter? = null
    private lateinit var CarRecycler : ArrayList<AddModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val button = findViewById(R.id.back1) as ImageView
        button.setOnClickListener {
            val intent = Intent(this, FiltersActivity::class.java)
            startActivity(intent)
        }

        Recyclerview = findViewById(R.id.CarRecycler)
        Recyclerview.layoutManager = LinearLayoutManager(this)
        Recyclerview.setHasFixedSize(true)


        CarRecycler = arrayListOf<AddModel>()
        getCarData(
            intent.getStringExtra("marka"),
            intent.getStringExtra("priceMin"),
            intent.getStringExtra("priceMax"),
            intent.getStringExtra("yearMin"),
            intent.getStringExtra("yearMax")
        )

        (findViewById(R.id.searchButton) as ImageView).setOnClickListener {
            val klucz: String = (findViewById(R.id.searchText) as EditText).text.toString()
            mAdapter?.filter(klucz)
        }
    }
    private fun getCarData(
        marka: String?,
        priceMin: String?,
        priceMax: String?,
        yearMin: String?,
        yearMax: String?
    ) {
        dbref = FirebaseDatabase.getInstance().getReference("Directory")
        val t: GenericTypeIndicator<Map<String, Map<String, AddModel>>> = object : GenericTypeIndicator<Map<String, Map<String, AddModel>>>() {}
        dbref.get().addOnCompleteListener {
            if(it.isSuccessful){
                val snap: DataSnapshot = it.result
                val zmapowane: Map<String, Map<String, AddModel>>? = snap.getValue(t)
                val wszystkieOgl: List<AddModel>? = zmapowane
                    ?.values
                    ?.map { it.values }
                    ?.flatten()
                    ?.filter { addModel ->
                        val markaPasuje = marka?.let { addModel.etVehicle == marka } ?: true
                        val priceMinPasuje = priceMin?.let { (addModel.etPrice?.toInt() ?: 0) >= priceMin.toInt() } ?: true
                        val priceMaxPasuje = priceMax?.let { (addModel.etPrice?.toInt() ?: 0) <= priceMax.toInt() } ?: true
                        val yearMinPasuje = yearMin?.let { (addModel.etYear?.toInt() ?: 0) >= yearMin.toInt() } ?: true
                        val yearMaxPasuje = yearMax?.let { (addModel.etYear?.toInt() ?: 0) <= yearMax.toInt() } ?: true

                        markaPasuje && priceMinPasuje && priceMaxPasuje && yearMinPasuje && yearMaxPasuje
                    }
                CarRecycler.addAll(wszystkieOgl?.toList() ?: emptyList())

                val storageReference = Firebase.storage.reference

                mAdapter = SearchAdapter(
                    CarRecycler = CarRecycler,
                    storageReference = storageReference,
                    bookButtonListener = this@SearchActivity,
                    heartButtonListener = this@SearchActivity,
                )
                Recyclerview.adapter = mAdapter
            }
        }
    }

    override fun onClick(item: AddModel) {
        val intent = Intent(this@SearchActivity, DetailActivity::class.java)
        intent.putExtra("etTitle", item.etTitle)
        intent.putExtra("etLocation", item.etLocation)
        intent.putExtra("etPrice", item.etPrice)
        intent.putExtra("etPower", item.etPower)
        intent.putExtra("etTransmission", item.etTransmission)
        intent.putExtra("etFuel", item.etFuel)
        intent.putExtra("etSeller", item.etSeller)
        intent.putExtra("etVin", item.etVin)
        intent.putExtra("etYear", item.etYear)
        intent.putExtra("etCubic", item.etCubic)
        intent.putExtra("etBody", item.etBody)
        intent.putExtra("etKilometre", item.etKilometre)
        intent.putExtra("etColor", item.etColor)
        intent.putExtra("etCountry", item.etCountry)
        intent.putExtra("etWheel", item.etWheel)
        intent.putExtra("etUsername", item.etUsername)
        intent.putExtra("etDescription", item.etDescription)
        intent.putExtra("etgalery", (item.etgalery).toTypedArray())
        intent.putExtra("etPhone", item.etPhone)
        intent.putExtra("uid", item.uid)
        intent.putExtra("ogloszenie", item.etId)
        intent.putExtra("istniejaceDaty", (item.etDates).toTypedArray())
        startActivity(intent)
    }

    override fun onChanged(item: AddModel, state: Boolean) {
       /* if (clickCount == 1) {
            Toast.makeText(this, "pierwszy" , Toast.LENGTH_SHORT).show()
        } else if (clickCount == 2) {
            Toast.makeText(this, "drugi" , Toast.LENGTH_SHORT).show()
        }*/
    }
}

interface BookButtonListener{
    fun onClick(item: AddModel)
}

interface HeartButtonListener{
    fun onChanged(item: AddModel, state: Boolean)
}