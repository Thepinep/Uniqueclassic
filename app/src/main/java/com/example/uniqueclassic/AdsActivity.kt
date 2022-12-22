package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.example.uniqueclassic.Model.AddModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.AdsAdapter
import com.google.firebase.auth.FirebaseAuth


import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdsActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview2 : RecyclerView
    private lateinit var userArrayList2 : ArrayList<AddModel>
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)

        /*val buttonClick = findViewById<ImageButton>(R.id.delete_click)
        buttonClick.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }*/


        Recyclerview2 = findViewById(R.id.AdsRecycler)
        Recyclerview2.layoutManager = LinearLayoutManager(this)
        Recyclerview2.setHasFixedSize(true)

        userArrayList2 = arrayListOf<AddModel>()
        getCarData()
    }
    private fun getCarData() {


        dbref = FirebaseDatabase.getInstance().getReference("Directory").child(uid)

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {
                        val car = userSnapshot.getValue(AddModel::class.java)
                        userArrayList2.add(car!!)
                    }
                    val storageReference = Firebase.storage.reference

                    val mAdapter = AdsAdapter(userArrayList2, storageReference)
                    Recyclerview2.adapter = mAdapter

                    /*val onlyThisUsrItems = snapshot.children.filter {
                        (it.getValue(AddModel::class.java) as AddModel).etId == uid
                    }
                    val mAdapter = AdsAdapter(onlyThisUsrItems , storageReference)*/
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}