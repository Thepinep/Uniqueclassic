package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.example.uniqueclassic.Model.User
import com.example.uniqueclassic.databinding.ActivityEditProfileBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private  lateinit var  uid : String
    private lateinit var  user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
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
                binding.textInputEditUsernameEdit.setText(user.etUsername)
                binding.textInputEditEmailEdit.setText(user.etEmail)
                binding.textInputEditPhoneEdit.setText(user.etPhone)
                binding.textInputEditLocationEdit.setText(user.etLocation)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun back() {
        binding.ButtonClose.setOnClickListener {
            finish()
            /*val myIntent = Intent(this, ProfileActivity::class.java)
            startActivity(myIntent)*/
        }
    }
    private fun editButton() {
        binding.ButtonSave.setOnClickListener {

            val username = binding.textInputEditUsernameEdit.text.toString()
            val email = binding.textInputEditEmailEdit.text.toString()
            val phone = binding.textInputEditPhoneEdit.text.toString()
            val location = binding.textInputEditLocationEdit.text.toString()

            updateData(username,email,phone,location)
        }
    }

    private fun updateData(username: String, email: String, phone: String, location: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        val user = mapOf<String,String>(
            "etUsername" to username,
            "etEmail" to email,
            "etPhone" to phone,
            "etLocation" to location
        )
        databaseReference.child(uid).updateChildren(user).addOnCompleteListener {
            val useroff = firebaseAuth.currentUser
                   useroff?.updateEmail(email)?.addOnCompleteListener {
                       if(it.isSuccessful){
                           Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()

                       }
                   }

            val myIntent = Intent(this, ProfileActivity::class.java)
            startActivity(myIntent)
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }
    }
}
