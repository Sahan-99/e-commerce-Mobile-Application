package com.my.tmscreation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InvoiceActivity : AppCompatActivity() {
    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var userNameTextView: TextView
    private lateinit var userEmailTextView: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        // Initialize views
        productNameTextView = findViewById(R.id.product_name)
        productPriceTextView = findViewById(R.id.product_price)
        userNameTextView = findViewById(R.id.user_name)
        userEmailTextView = findViewById(R.id.user_email)
        finishButton = findViewById(R.id.finish_button)

        // Get data from intent
        val productName = intent.getStringExtra("name")
        val productPrice = intent.getStringExtra("price")
        val userName = intent.getStringExtra("userName")
        val userEmail = intent.getStringExtra("userEmail")

        // Set data to views
        productNameTextView.text = productName
        productPriceTextView.text = productPrice
        userNameTextView.text = userName
        userEmailTextView.text = userEmail

        // Set up finish button click listener
        finishButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}
