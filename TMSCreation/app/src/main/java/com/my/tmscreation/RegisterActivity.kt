package com.my.tmscreation

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : BottomMessageActivity() {
    private lateinit var fName: TextView
    private lateinit var lName: TextView
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var confirmPassword: TextView
    private lateinit var termsCheckbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val loginNow: TextView = findViewById(R.id.tms_login_now)
        val register: TextView = findViewById(R.id.register_btn)
        fName = findViewById(R.id.fname)
        lName = findViewById(R.id.lname)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.re_password)
        termsCheckbox = findViewById(R.id.term_checkbox)

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

        loginNow.setOnClickListener {
            onBackPressed()
        }

        register.setOnClickListener {
            registerUser()
        }
    }

    private fun validateRegister(): Boolean {
        return when {
            TextUtils.isEmpty(fName.text.toString().trim{ it <= ' '}) ->{
                showBottomMessage(resources.getString(R.string.err_enter_fname), true)
                false
            }
            TextUtils.isEmpty(lName.text.toString().trim{ it <= ' '}) ->{
                showBottomMessage(resources.getString(R.string.err_enter_lname), true)
                false
            }
            TextUtils.isEmpty(email.text.toString().trim{ it <= ' '}) ->{
                showBottomMessage(resources.getString(R.string.err_enter_email), true)
                false
            }
            TextUtils.isEmpty(password.text.toString().trim{ it <= ' '}) ->{
                showBottomMessage(resources.getString(R.string.err_enter_pwd), true)
                false
            }
            TextUtils.isEmpty(confirmPassword.text.toString().trim{ it <= ' '}) ->{
                showBottomMessage(resources.getString(R.string.err_enter_repwd), true)
                false
            }
            password.text.toString().trim{ it <= ' '} != confirmPassword.text.toString().trim{ it <= ' '} ->{
                showBottomMessage(resources.getString(R.string.err_pwd_repwd_mismatch), true)
                false
            }
            !termsCheckbox.isChecked -> {
                showBottomMessage(resources.getString(R.string.err_agree), true)
                false
            }
            else -> {
                // showBottomMessage(resources.getString(R.string.success), false)
                true
            }
        }
    }

    private fun registerUser() {
        if (validateRegister()) {
            val regEmail:String = email.text.toString().trim{ it <= ' '}
            val regPassword:String = password.text.toString().trim{ it <= ' '}

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener(OnCompleteListener <AuthResult> {task ->
                    if(task.isSuccessful){
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val displayName = "${fName.text.toString().trim()} "
                        updateProfile(firebaseUser, displayName)
                    }
                    else{
                        showBottomMessage(task.exception!!.message.toString(),true)
                    }
                })
        }
    }

    private fun updateProfile(user: FirebaseUser, displayName: String) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .build()

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showBottomMessage("You are registered successfully. Your user ID is ${user.uid}", false)
                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    showBottomMessage(task.exception!!.message.toString(), true)
                }
            }
    }
}
