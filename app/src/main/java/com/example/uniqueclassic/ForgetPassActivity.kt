package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.uniqueclassic.databinding.ActivityForgetPassBinding
import com.google.firebase.auth.FirebaseAuth


class ForgetPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPassBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.ButtonSubmit.setOnClickListener {
            val email = binding.textInputEditEmailAddress.text.toString()
            if(email.isNotEmpty()) {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please Check you Email", Toast.LENGTH_SHORT).show()
                }
            }
            }else {
                Toast.makeText(this, "Fill in the blanks", Toast.LENGTH_SHORT).show()
            }
        }
    }
}