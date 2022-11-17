package com.example.uniqueclassic

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uniqueclassic.databinding.ActivityLoginBinding

import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.SignUp2.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
        }
        binding.ForgotPassword.setOnClickListener {
            val myIntent = Intent(this, ForgetPassActivity::class.java)
            startActivity(myIntent)
        }
        binding.ButtonLogin.setOnClickListener {
            val email= binding.textInputEditEmail.text.toString()
            val pass = binding.textInputEditPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                           val Verification = firebaseAuth.currentUser?.isEmailVerified
                            if (Verification == true){
                                val intent = Intent(this, SearchActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this, "Test" , Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Fill in the blanks" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}
