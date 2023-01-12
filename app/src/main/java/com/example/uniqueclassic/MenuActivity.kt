package com.example.uniqueclassic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



class MenuActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val button: View?= findViewById(R.id.ic_add_button)
        button?.setOnClickListener {
        //    navController.navigate(R.id.to_add)
            val myIntent = Intent(this, AddActivity::class.java)
            startActivity(myIntent)

        }



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setupWithNavController(bottomNavigationView,navController)







    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(
        navController.currentDestination?.id!=R.id.profilFragment
        )
        navController.navigate(R.id.to_profile)

    }
}