package com.my.tmscreation

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var fileTypeSpinner: Spinner
    private lateinit var userName: EditText
    private lateinit var userPhone: EditText
    private lateinit var userEmail: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cardHolderName: EditText
    private lateinit var cardExpiryDate: EditText
    private lateinit var cardCvv: EditText
    private lateinit var buyNowButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val back: ImageView = findViewById(R.id.backBtn)
        back.setOnClickListener {
            onBackPressed()
        }

        productName = findViewById(R.id.product_name)
        productPrice = findViewById(R.id.product_price)
        fileTypeSpinner = findViewById(R.id.file_type_spinner)
        userName = findViewById(R.id.user_name)
        userPhone = findViewById(R.id.user_phone)
        userEmail = findViewById(R.id.user_email)
        cardNumber = findViewById(R.id.card_number)
        cardHolderName = findViewById(R.id.card_holder_name)
        cardExpiryDate = findViewById(R.id.card_expiry_date)
        cardCvv = findViewById(R.id.card_cvv)
        buyNowButton = findViewById(R.id.buy_now_button)

        // Get product details from intent
        val name = intent.getStringExtra("name") ?: "N/A"
        val price = intent.getStringExtra("price") ?: "N/A"

        // Set product details
        productName.text = name
        productPrice.text = price

        // Setup file type spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.file_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fileTypeSpinner.adapter = adapter
        }

        // Buy now button click listener
        buyNowButton.setOnClickListener {
            if (validateInput()) {
                // Intent to navigate to InvoiceActivity after successful validation
                val intent = Intent(this, InvoiceActivity::class.java).apply {
                    putExtra("name", productName.text.toString())
                    putExtra("price", productPrice.text.toString())
                    putExtra("userName", userName.text.toString())
                    putExtra("userEmail", userEmail.text.toString())
                }
                startActivity(intent)
                Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(): Boolean {
        if (userName.text.isBlank()) {
            userName.error = "Name is required"
            return false
        }
        if (userPhone.text.isBlank()) {
            userPhone.error = "Phone number is required"
            return false
        }
        if (userEmail.text.isBlank()) {
            userEmail.error = "Email is required"
            return false
        }
        if (cardNumber.text.isBlank()) {
            cardNumber.error = "Card number is required"
            return false
        }
        if (cardHolderName.text.isBlank()) {
            cardHolderName.error = "Card holder name is required"
            return false
        }
        if (cardExpiryDate.text.isBlank()) {
            cardExpiryDate.error = "Expiry date is required"
            return false
        }
        if (cardCvv.text.isBlank()) {
            cardCvv.error = "CVV is required"
            return false
        }
        return true
    }
}
