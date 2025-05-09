package com.example.newsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            statusBarColor = Color.TRANSPARENT
        }


        val registerTextView = findViewById<TextView>(R.id.registerTextView)
        val fullText = "Belum punya akun? Daftar"
        val spannable = SpannableString(fullText)

        val blueColor = ForegroundColorSpan(Color.parseColor("#0033A0"))
        spannable.setSpan(blueColor, 18, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
        spannable.setSpan(clickableSpan, 18, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        registerTextView.text = spannable
        registerTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}