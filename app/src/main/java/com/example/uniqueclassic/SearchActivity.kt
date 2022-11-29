package com.example.uniqueclassic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniqueclassic.Adapter.SearchAdapter
import com.example.uniqueclassic.Model.AddModel
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<AddModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Recyclerview = findViewById(R.id.CarRecycler)
        Recyclerview.layoutManager = LinearLayoutManager(this)
        Recyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<AddModel>()
        getCarData()


    }
    private fun getCarData() {

        dbref = FirebaseDatabase.getInstance().getReference("Directory")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {


                        val car = userSnapshot.getValue(AddModel::class.java)
                        userArrayList.add(car!!)

                    }

                    Recyclerview.adapter = SearchAdapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
