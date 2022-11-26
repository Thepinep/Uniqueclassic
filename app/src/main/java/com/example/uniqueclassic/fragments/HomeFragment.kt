package com.example.uniqueclassic.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uniqueclassic.FiltersActivity

import com.example.uniqueclassic.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.ButtonHome.setOnClickListener{
            val intent = Intent (requireContext(), FiltersActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}

