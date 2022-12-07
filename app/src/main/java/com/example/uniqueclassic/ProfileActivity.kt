package com.example.uniqueclassic


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.uniqueclassic.Model.User
import com.example.uniqueclassic.databinding.ActivityProfilBinding
import com.example.uniqueclassic.fragments.ProfilFragment
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import java.security.AccessController.getContext


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private  lateinit var  uid : String
    private lateinit var  user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        if (uid.isNotEmpty()){
            getUserData()
            editButton()
            back()

        }

    }

    private fun getUserData() {

        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                user = snapshot.getValue(User::class.java)!!
                binding.textUsername.setText(user.etUsername)
                binding.textEmail.setText(user.etEmail)
                binding.textPhone.setText(user.etPhone)
                binding.textPosition.setText(user.etLocation)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun editButton() {
        binding.ButtonEdit.setOnClickListener {
            val myIntent = Intent(this, EditProfileActivity::class.java)
            startActivity(myIntent)
        }
    }
    private fun back() {
        binding.ButtonClose.setOnClickListener {
           /* val intent = Intent(getContext(), ProfilFragment::class.java)
            startActivity(intent)*/


        }
    }
}
