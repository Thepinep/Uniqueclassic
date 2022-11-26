package com.example.uniqueclassic.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uniqueclassic.FiltersActivity
import com.example.uniqueclassic.LoginActivity
import com.example.uniqueclassic.databinding.FragmentLogoutBinding
import com.google.firebase.auth.FirebaseAuth

class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding
    private lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)

        user = FirebaseAuth.getInstance()


        binding.ButtonLogout.setOnClickListener {
            user.signOut()
            startActivity(
                Intent(
                    requireContext(), LoginActivity::class.java
                )
            )
            activity?.finish()
        }

        return binding.root
    }
}