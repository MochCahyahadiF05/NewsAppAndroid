package com.example.newsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            statusBarColor = Color.TRANSPARENT
        }


        val appNameText = findViewById<TextView>(R.id.appName)
        val logoImage = findViewById<ImageView>(R.id.logoIcon)

        Handler(Looper.getMainLooper()).postDelayed({
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            appNameText.visibility = View.VISIBLE
            appNameText.startAnimation(anim)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 4000)
    }
}