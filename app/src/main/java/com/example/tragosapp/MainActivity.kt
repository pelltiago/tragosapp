 package com.example.tragosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.tragosapp.domain.RepoImpl
import com.example.tragosapp.ui.viewmodel.MainViewModel
import com.example.tragosapp.ui.viewmodel.VMFactory

 class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

     override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
     }

}