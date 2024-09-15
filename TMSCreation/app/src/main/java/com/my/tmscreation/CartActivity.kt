package com.my.tmscreation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CartActivity : AppCompatActivity() {
    private lateinit var cartImageView: ImageView
    private lateinit var cartPriceTextView: TextView
    private lateinit var cartNameTextView: TextView
    private lateinit var buyButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartImageView = findViewById(R.id.cart_image)
        cartPriceTextView = findViewById(R.id.cart_price)
        cartNameTextView = findViewById(R.id.cart_name)
        buyButton = findViewById(R.id.buy_button)

        // Get data from intent
        val imageResId = intent.getIntExtra("imageResId", 0)
        val price = intent.getStringExtra("price")
        val name = intent.getStringExtra("name")

        // Set data to views
        Glide.with(this).load(imageResId).into(cartImageView)
        cartPriceTextView.text = price
        cartNameTextView.text = name

        // Implement buy button functionality
        buyButton.setOnClickListener {
            // Implement purchase logic
        }
    }
}
