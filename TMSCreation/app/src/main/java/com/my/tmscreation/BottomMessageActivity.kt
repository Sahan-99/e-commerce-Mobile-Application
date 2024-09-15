package com.my.tmscreation

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open class BottomMessageActivity : AppCompatActivity() {

    fun showBottomMessage(message: String, errorMessage: Boolean){
        val bottomMessage = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val bottomMessageView = bottomMessage.view

        if (errorMessage){
            bottomMessageView.setBackgroundColor(
                ContextCompat.getColor(this@BottomMessageActivity, R.color.error)
            )
        }
        else{
            bottomMessageView.setBackgroundColor(
                ContextCompat.getColor(this@BottomMessageActivity, R.color.success)
            )
        }
        bottomMessage.show()
    }
}


