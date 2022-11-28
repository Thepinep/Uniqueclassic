package com.example.uniqueclassic.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uniqueclassic.LoginActivity
import com.example.uniqueclassic.Pdf.PolicyActivity
import com.example.uniqueclassic.StartActivity
import com.example.uniqueclassic.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var user: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)



        val addContactDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("Deleting this account will reset in completely removing your " +" account form the system and you won,t be able to access the app.")
            .setPositiveButton("Account Deleted") { dialogInterface, i ->
              /*  user = FirebaseAuth.getInstance()
                user.delete()*/
                /*val user = firebaseAuth.currentUser
                user.delete()?*/
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton("Dismiss") { dialogInterface, i ->
            }.create()

        binding.DeleteButton.setOnClickListener {
            addContactDialog.show()
        }
        binding.PrivacyButton.setOnClickListener {
            startActivity(Intent(requireContext(), PolicyActivity::class.java))

        }

        return binding.root
    }
}