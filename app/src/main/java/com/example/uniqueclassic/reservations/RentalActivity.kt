package com.example.uniqueclassic.reservations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        back3()
    }

    private fun back3() {
        findViewById<View>(R.id.ButtonClose3).setOnClickListener {
            finish()
        }
    }
    private fun getCarData() {
        dbref = FirebaseDatabase.getInstance().getReference("Reservations")
        val t: GenericTypeIndicator<Map<String, Map<String, Rent>>> = object : GenericTypeIndicator<Map<String, Map<String, Rent>>>() {}
        dbref.get().addOnCompleteListener {
            if(it.isSuccessful){
                val snap: DataSnapshot = it.result
                val zmapowane: Map<String, Map<String, Rent>>? = snap.getValue(t)
                val rentyCurrentUsera: List<Rent>? = zmapowane
                    ?.values
                    ?.map { it.values }
                    ?.flatten()
                    ?.filter {
                        it.TenantUid == uid
                    }


                val mAdapter = RentalAdapter(rentyCurrentUsera)
                Recyclerview4.adapter = mAdapter
            }
        }
    }
}
