package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.example.uniqueclassic.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.TextTermsConditions.setOnClickListener{
            val intent = Intent(this, PdfActivity::class.java)
            startActivity(intent)
        }

        binding.ButtonCreateAnAccount.setOnClickListener {
            val username= binding.textInputEditUsername.text.toString()
            val email= binding.textInputEditEmailAddress.text.toString()
            val pass = binding.textInputEditPassword.text.toString()
            val confirmpass = binding.textInputEditConfirmPassword.text.toString()


            if(username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass ).addOnCompleteListener {
                        if (it.isSuccessful) {

                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            firebaseAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                                Toast.makeText(this, "Please Verify your Email!", Toast.LENGTH_SHORT).show()

                            }
                                ?.addOnFailureListener{
                                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else {
                    Toast.makeText(this, "Password is ont matching", Toast.LENGTH_SHORT).show()
                }
            }else{
            Toast.makeText(this, "Fill in the blanks" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}








/*val button = findViewById<Button>(R.id.ButtonCreateAnAccount)
val textterms = findViewById<TextView>(R.id.TextTermsConditions)



button.setOnClickListener {
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent)
}
textterms.setOnClickListener {
    val myIntent = Intent(this@RegisterActivity, PdfActivity::class.java)
    startActivity(myIntent)
}*/