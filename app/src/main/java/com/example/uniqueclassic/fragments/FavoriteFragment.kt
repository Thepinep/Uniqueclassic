package com.example.uniqueclassic.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.SearchAdapter
import com.example.uniqueclassic.DetailActivity
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.google.firebase.database.*


class FavoriteFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview : RecyclerView
    private lateinit var CarRecycler : ArrayList<AddModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        Recyclerview = rootView.findViewById(R.id.CarRecycler)
        Recyclerview.layoutManager = LinearLayoutManager(requireContext())
        Recyclerview.setHasFixedSize(true)

        CarRecycler = arrayListOf<AddModel>()
        getCarData()

        return rootView


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

                    val mAdapter = SearchAdapter(CarRecycler)
                    Recyclerview.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : SearchAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FavoriteFragment.context, DetailActivity::class.java)


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