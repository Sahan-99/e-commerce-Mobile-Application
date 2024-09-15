package com.my.tmscreation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomePageActivity : AppCompatActivity() {
    private lateinit var welcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        welcomeMessage = findViewById(R.id.welcomeback)

        // Get the current user
        val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        // Display welcome message
        if (currentUser != null) {
            val displayName = currentUser.displayName ?: "User"
            welcomeMessage.text = "Welcome back, $displayName"
        } else {
            welcomeMessage.text = "Welcome back"
        }

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

        rec01.setOnClickListener { openProductDetail(R.drawable.vector02, "LKR.2800", "Dog Vector Illustrate", "140+ Downloads", "4.4","This high-quality \"Dog Vector Illustrate\" image features a charming and detailed dog illustration, perfect for adding a playful touch to your projects. Ideal for use in web design, marketing materials, or as a unique decorative element. Compatible with various design software for easy customization.") }
        rec02.setOnClickListener { openProductDetail(R.drawable.template01, "LKR.500", "Magazine Mockup", "500+ Downloads", "4.9", "This premium \"Magazine Mockup\" provides a realistic and professional way to showcase your magazine designs. Featuring high-resolution details, it’s perfect for presentations, marketing materials, or portfolio displays. Easily customizable with smart objects, it ensures your artwork stands out with an impressive, photorealistic finish. Ideal for designers and publishers.") }
        rec03.setOnClickListener { openProductDetail(R.drawable.business01, "LKR.1000", "Professional Business Flyer", "140+ Downloads", "4.2", "This sleek \"Professional Business Flyer\" template is designed to elevate your marketing efforts. Featuring a modern and clean layout, it is perfect for promoting events, services, or products. Fully customizable, it allows you to effortlessly insert your brand's colors, logos, and information, ensuring a polished and professional presentation. Ideal for businesses and marketers.") }
        rec04.setOnClickListener { openProductDetail(R.drawable.nft01, "LKR.3500", "BITCOIN Crypto Robot NFT Art", "200+ Downloads", "4.8", "This exclusive \"BITCOIN Crypto Robot NFT Art\" features a unique digital artwork of a futuristic crypto robot. Perfect for NFT collectors and crypto enthusiasts, it combines cutting-edge design with the intrigue of cryptocurrency. Ideal for digital galleries, personal collections, or as an investment in the growing NFT market.") }
        rec05.setOnClickListener { openProductDetail(R.drawable.template02, "LKR.700", "Flyer Mockup", "500+ Downloads", "4.9", "This versatile \"Flyer Mockup\" offers a realistic and professional way to display your flyer designs. Perfect for presentations, marketing materials, or portfolio showcases, it features high-resolution details and easy customization with smart objects. Highlight your creative work with this photorealistic mockup, ideal for designers and marketers seeking to make an impactful impression.") }
        rec06.setOnClickListener { openProductDetail(R.drawable.vector01, "LKR.1900", "Digital Vector Art", "50+ Downloads", "4.5", "This stunning \"Digital Vector Art\" showcases intricate and high-quality illustrations perfect for various creative projects. Easily scalable without losing resolution, it’s ideal for web design, print materials, and digital media. Versatile and customizable, this vector art adds a touch of sophistication and creativity to any project, making it a must-have for designers.") }
        rec07.setOnClickListener { openProductDetail(R.drawable.nft02, "LKR.5000", "Digital Ape NFT", "900+ Downloads", "4.9", "This exclusive \"Digital Ape NFT\" presents a unique and captivating digital artwork of an ape, perfect for NFT collectors and enthusiasts. Rich in detail and vibrant in design, it stands out in any digital gallery or collection. Ideal for investment and showcasing cutting-edge digital art in the evolving NFT marketplace.") }
        rec08.setOnClickListener { openProductDetail(R.drawable.business02, "LKR.800", "Business Card", "600+ Downloads", "4.7", "This elegant \"Business Card\" design provides a sleek and professional way to represent your brand. Featuring a modern layout and customizable fields for your contact information, it’s perfect for networking and leaving a lasting impression. Ideal for professionals and businesses seeking a refined, polished look to enhance their brand identity.") }
    }

    private fun openProductDetail(imageResId: Int, price: String, name: String, downloads: String, rating: String, description: String) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("imageResId", imageResId)
        intent.putExtra("price", price)
        intent.putExtra("name", name)
        intent.putExtra("downloads", downloads)
        intent.putExtra("rating", rating)
        intent.putExtra("description", description)
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

                    true
                }
                R.id.navigation_profile -> {
                    // Navigate to UserAccountActivity
                    val intent = Intent(this, UserAccountActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
