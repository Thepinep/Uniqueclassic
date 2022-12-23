package com.example.uniqueclassic.reservations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.RentalAdapter
import com.example.uniqueclassic.Adapter.ReservationAdapter
import com.example.uniqueclassic.Model.Rent
import com.example.uniqueclassic.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RentalActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview4 : RecyclerView
    private lateinit var userArrayList4 : ArrayList<Rent>
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental)

        Recyclerview4 = findViewById(R.id.RentalRecycler)
        Recyclerview4.layoutManager = LinearLayoutManager(this)
        Recyclerview4.setHasFixedSize(true)

        userArrayList4 = arrayListOf<Rent>()
        getCarData()
    }

    private fun getCarData() {

            dbref = FirebaseDatabase.getInstance().getReference("Reservations").child(uid)

            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val Reservation = userSnapshot.getValue(Rent::class.java)
                            userArrayList4.add(Reservation!!)
                        }
                        val mAdapter = RentalAdapter(userArrayList4)
                        Recyclerview4.adapter = mAdapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}
