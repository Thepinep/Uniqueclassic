package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uniqueclassic.Model.User
import com.example.uniqueclassic.Pdf.TermsActivity
import com.example.uniqueclassic.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TextTermsConditions.setOnClickListener{
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        binding.ButtonCreateAnAccount.setOnClickListener {
            val username= binding.textInputEditUsername.text.toString()
            val email= binding.textInputEditEmailAddress.text.toString()
            val pass = binding.textInputEditPassword.text.toString()
            val confirmpass = binding.textInputEditConfirmPassword.text.toString()
            val phone = ""
            val location = ""

            if(username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val uid = firebaseAuth.currentUser?.uid
                            val user = User(username,email, phone, location)
                            if( uid != null){
                                databaseReference.child(uid).setValue(user).addOnCompleteListener {  }
                            }
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
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
            Toast.makeText(this, "Fill in the blanks" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}