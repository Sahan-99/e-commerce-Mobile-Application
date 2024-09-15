package com.my.tmscreation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserAccountActivity : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var logoutButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_account)

        username = findViewById(R.id.user)
        logoutButton = findViewById(R.id.logoutBtn)

        // Get the current user
        val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        // Display username
        if (currentUser != null) {
            val displayName = currentUser.displayName ?: "User"
            username.text = displayName
        } else {
            username.text = "User"
        }

        // Set up BottomNavigationView
        setupBottomNavigationView()

        // Handle logout
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@UserAccountActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_product -> {
                    val intent = Intent(this, ProductActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_cart -> {
                    // Handle recommendations navigation
                    true
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this, UserAccountActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
