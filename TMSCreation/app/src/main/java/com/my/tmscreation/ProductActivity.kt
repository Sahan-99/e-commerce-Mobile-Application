package com.my.tmscreation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        // Set up image click listeners
        setupImageClickListeners()

        // Set up BottomNavigationView
        setupBottomNavigationView()
    }

    private fun setupImageClickListeners() {
        val rec01: ImageView = findViewById(R.id.rec01)
        val rec02: ImageView = findViewById(R.id.rec02)
        val rec03: ImageView = findViewById(R.id.rec03)
        val rec04: ImageView = findViewById(R.id.rec04)
        val rec05: ImageView = findViewById(R.id.rec05)
        val rec06: ImageView = findViewById(R.id.rec06)
        val rec07: ImageView = findViewById(R.id.rec07)
        val rec08: ImageView = findViewById(R.id.rec08)

        rec01.setOnClickListener { openProductDetail(R.drawable.vector02, "LKR.2800", "Dog Vector Illustrate", "140+ Downloads", "Rating 4.4") }
        rec02.setOnClickListener { openProductDetail(R.drawable.template01, "LKR.500", "Magazine Mockup", "500+ Downloads", "Rating 4.9") }
        rec03.setOnClickListener { openProductDetail(R.drawable.business01, "LKR.1000", "Professional Business Flyer", "140+ Downloads", "Rating 4.2") }
        rec04.setOnClickListener { openProductDetail(R.drawable.nft01, "LKR.3500", "BITCOIN Crypto Robot NFT Art", "200+ Downloads", "Rating 4.8") }
        rec05.setOnClickListener { openProductDetail(R.drawable.template02, "LKR.700", "Flyer Mockup", "500+ Downloads", "Rating 4.9") }
        rec06.setOnClickListener { openProductDetail(R.drawable.vector01, "LKR.1900", "Digital Vector Art", "50+ Downloads", "Rating 4.5") }
        rec07.setOnClickListener { openProductDetail(R.drawable.nft02, "LKR.5000", "Digital Ape NFT", "900+ Downloads", "Rating 4.9") }
        rec08.setOnClickListener { openProductDetail(R.drawable.business02, "LKR.800", "Business Card", "600+ Downloads", "Rating 4.7") }
    }

    private fun openProductDetail(imageResId: Int, price: String, name: String, downloads: String, rating: String) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("imageResId", imageResId)
        intent.putExtra("price", price)
        intent.putExtra("name", name)
        intent.putExtra("downloads", downloads)
        intent.putExtra("rating", rating)
        startActivity(intent)
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
