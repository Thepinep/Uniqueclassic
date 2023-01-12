package com.example.uniqueclassic.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.uniqueclassic.Adapter.AdsAdapter
import com.example.uniqueclassic.Adapter.FavouriteAdapter
import com.example.uniqueclassic.Adapter.SearchAdapter
import com.example.uniqueclassic.DetailActivity
import com.example.uniqueclassic.Model.AddModel
import com.example.uniqueclassic.R
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.InputStream


class FavoriteFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var Recyclerview5 : RecyclerView
    private lateinit var userArrayList5 : ArrayList<AddModel>
    var uid = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_favorite, container, false)

        Recyclerview5 =rootView.findViewById(R.id.Carfavorite7)
        Recyclerview5.layoutManager = LinearLayoutManager(context)
        Recyclerview5.setHasFixedSize(true)

        userArrayList5 = arrayListOf<AddModel>()
        getCarData()

        return rootView
    }
    private fun getCarData() {


        dbref = FirebaseDatabase.getInstance().getReference("Favourites").child(uid)

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {
                        val car: AddModel? = userSnapshot.getValue(AddModel::class.java)
                        userArrayList5.add(car!!)
                    }
                    val storageReference = Firebase.storage.reference

                    val mAdapter = FavouriteAdapter(userArrayList5, storageReference)
                    Recyclerview5.adapter = mAdapter

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



