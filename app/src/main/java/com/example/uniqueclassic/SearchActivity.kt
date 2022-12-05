package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.SearchAdapter
import com.example.uniqueclassic.Model.AddModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SearchActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview : RecyclerView
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
        getCarData()


    }
    private fun getCarData() {

        dbref = FirebaseDatabase.getInstance().getReference("Directory")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {

                        val car = userSnapshot.getValue(AddModel::class.java)
                        CarRecycler.add(car!!)
                    }

                    val storageReference = Firebase.storage.reference

                    val mAdapter = SearchAdapter(CarRecycler , storageReference)
                    Recyclerview.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object :SearchAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@SearchActivity, DetailActivity::class.java)


                            intent.putExtra("etTitle", CarRecycler[position].etTitle)
                            intent.putExtra("etLocation", CarRecycler[position].etLocation)
                            intent.putExtra("etPrice", CarRecycler[position].etPrice)
                            intent.putExtra("etPower", CarRecycler[position].etPower)
                            intent.putExtra("etTransmission", CarRecycler[position].etTransmission)
                            intent.putExtra("etFuel", CarRecycler[position].etFuel)
                            intent.putExtra("etSeller", CarRecycler[position].etSeller)
                            intent.putExtra("etVin", CarRecycler[position].etVin)
                            intent.putExtra("etYear", CarRecycler[position].etYear)
                            intent.putExtra("etCubic", CarRecycler[position].etCubic)
                            intent.putExtra("etBody", CarRecycler[position].etBody)
                            intent.putExtra("etKilometre", CarRecycler[position].etKilometre)
                            intent.putExtra("etColor", CarRecycler[position].etColor)
                            intent.putExtra("etCountry", CarRecycler[position].etCountry)
                            intent.putExtra("etWheel", CarRecycler[position].etWheel)
                            intent.putExtra("etUsername", CarRecycler[position].etUsername)
                            intent.putExtra("etDescription", CarRecycler[position].etDescription)

                            startActivity(intent)
                        }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
