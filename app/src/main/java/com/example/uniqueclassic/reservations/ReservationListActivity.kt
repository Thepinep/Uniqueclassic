package com.example.uniqueclassic.reservations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.AdsAdapter
import com.example.uniqueclassic.Adapter.ReservationAdapter
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.Model.Rent
import com.example.uniqueclassic.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ReservationListActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview3 : RecyclerView
    private lateinit var userArrayList3 : ArrayList<Rent>
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)


        Recyclerview3 = findViewById(R.id.listRecycler)
        Recyclerview3.layoutManager = LinearLayoutManager(this)
        Recyclerview3.setHasFixedSize(true)

        userArrayList3 = arrayListOf<Rent>()
        getCarData()


    }

    private fun getCarData() {
        dbref = FirebaseDatabase.getInstance().getReference("Reservations").child(uid)

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val Reservation = userSnapshot.getValue(Rent::class.java)
                        userArrayList3.add(Reservation!!)
                    }
                    val mAdapter = ReservationAdapter(userArrayList3, )
                    Recyclerview3.adapter = mAdapter
                    mAdapter.setOnItemClickListener(object :ReservationAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ReservationListActivity, ReservationsActivity::class.java)

                            intent.putExtra("etTenant",userArrayList3[position].etTenant)
                            intent.putExtra("invoice",userArrayList3[position].invoice)
                            intent.putExtra("etStartDate",userArrayList3[position].etStartDate)
                            intent.putExtra("etEndDate",userArrayList3[position].etEndDate)
                            intent.putExtra("etPhone",userArrayList3[position].etPhone)
                            intent.putExtra("location",userArrayList3[position].location)
                            intent.putExtra("etCost",userArrayList3[position].etCost)
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