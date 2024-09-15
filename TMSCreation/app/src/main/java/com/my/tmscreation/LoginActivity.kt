package com.my.tmscreation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BottomMessageActivity(), View.OnClickListener {
    private lateinit var lemail: TextView
    private lateinit var lpassword: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var login: TextView
    private lateinit var register: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // Check if user is already logged in
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            navigateToHomePage()
            return
        }

        lemail = findViewById(R.id.email)
        lpassword = findViewById(R.id.password)
        forgetPassword = findViewById(R.id.forgotPwd)
        login = findViewById(R.id.login_btn)
        register = findViewById(R.id.tms_register_now)

        // Full screen without top-bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        forgetPassword.setOnClickListener(this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.forgotPwd -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.login_btn -> {
                    loginRegUser()
                }
                R.id.tms_register_now -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLogin(): Boolean {
        return when {
            TextUtils.isEmpty(lemail.text.toString().trim { it <= ' ' }) -> {
                showBottomMessage(resources.getString(R.string.err_enter_email), true)
                false
            }
            TextUtils.isEmpty(lpassword.text.toString().trim { it <= ' ' }) -> {
                showBottomMessage(resources.getString(R.string.err_enter_pwd), true)
                false
            }
            else -> {
                //showBottomMessage(resources.getString(R.string.success), false)
                true
            }
        }
    }

    private fun loginRegUser() {
        if (validateLogin()) {
            val regEmail: String = lemail.text.toString().trim { it <= ' ' }
            val regPassword: String = lpassword.text.toString().trim { it <= ' ' }

            auth.signInWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showBottomMessage("You are logged in successfully", false)
                        navigateToHomePage()
                    } else {
                        showBottomMessage(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    private fun navigateToHomePage() {
        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
        startActivity(intent)
        finish()
    }
}
