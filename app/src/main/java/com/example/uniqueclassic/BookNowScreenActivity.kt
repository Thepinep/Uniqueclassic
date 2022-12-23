package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.uniqueclassic.databinding.ActivityBookNowScreenBinding
import com.example.uniqueclassic.reservations.ReservationListActivity
import com.example.uniqueclassic.reservations.ReservationsActivity

class BookNowScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivityBookNowScreenBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookNowScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.myLooper()!!)

        handler.postDelayed({
            val intent = Intent(this, ReservationListActivity::class.java)
            startActivity(intent)
            finish()

        },5000)
    }
}
