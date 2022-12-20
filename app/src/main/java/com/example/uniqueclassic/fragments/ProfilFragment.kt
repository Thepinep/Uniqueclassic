package com.example.uniqueclassic.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uniqueclassic.AdsActivity
import com.example.uniqueclassic.LoginActivity
import com.example.uniqueclassic.Model.User
import com.example.uniqueclassic.Pdf.PolicyActivity
import com.example.uniqueclassic.ProfileActivity
import com.example.uniqueclassic.databinding.FragmentProfilBinding
import com.example.uniqueclassic.reservations.ReservationsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference


class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private  lateinit var  uid : String
    private lateinit var  user : User



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        if (uid.isNotEmpty()){
            raedData()
        }
        val addContactDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("Deleting this account will reset in completely removing your " +" account form the system and you won,t be able to access the app.")
            .setPositiveButton("Account Deleted") { dialogInterface, i ->
                val auth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance()
                val childRef = databaseReference.getReference("User").child(uid)
                val childRef2 = databaseReference.getReference("Reservations").child(uid)
               // val childRef3 = databaseReference.getReference("Directory").child(uid)
                childRef.removeValue()
                childRef2.removeValue()
               // childRef3.removeValue()
                val user = auth.currentUser
                user?.delete()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(requireContext(), LoginActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else {
                        }
                    }
            }
            .setNegativeButton("Dismiss") { dialogInterface, i ->
            }.create()

        binding.EditButton.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        binding.AdsButton.setOnClickListener {
            startActivity(Intent(requireContext(), AdsActivity::class.java))
        }
        binding.ReserButton.setOnClickListener {
            startActivity(Intent(requireContext(), ReservationsActivity::class.java))
        }
        binding.DeleteButton.setOnClickListener {
            addContactDialog.show()
        }
        binding.PrivacyButton.setOnClickListener {
            startActivity(Intent(requireContext(), PolicyActivity::class.java))
        }
        return binding.root
    }

    private fun raedData() {

        databaseReference.child(uid).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                user = snapshot.getValue(User::class.java)!!
                binding.TextName.setText(user.etUsername)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        /*val uid = firebaseAuth.currentUser?.uid
        firebaseDataBase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDataBase?.getReference("User")
            //.child(uid!!)*/

  //          val username = child("etUsername").value
  //          binding.TextName.text = username.toString()
        }
}