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


class SplashScreenActivity : AppCompatActivity() { // Mendeklarasikan class SplashScreenActivity sebagai turunan dari AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) { // Metode onCreate dipanggil saat activity dibuat pertama kali
        super.onCreate(savedInstanceState) // Memanggil implementasi onCreate dari superclass
        setContentView(R.layout.activity_splash_screen) // Menetapkan layout yang digunakan untuk activity ini

        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE // Mengatur tampilan agar layout berada di belakang status bar dan stabil
            statusBarColor = Color.TRANSPARENT // Mengatur warna status bar menjadi transparan
        }

        val appNameText = findViewById<TextView>(R.id.appName) // Menghubungkan komponen TextView dari layout ke variabel appNameText
        val logoImage = findViewById<ImageView>(R.id.logoIcon) // Menghubungkan komponen ImageView dari layout ke variabel logoImage

        Handler(Looper.getMainLooper()).postDelayed({ // Menjalankan kode setelah delay 1 detik
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in) // Memuat animasi fade_in
            appNameText.visibility = View.VISIBLE // Menampilkan teks appName
            appNameText.startAnimation(anim) // Menjalankan animasi pada teks appName
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({ // Menjalankan kode setelah delay 4 detik
            startActivity(Intent(this, LoginActivity::class.java)) // Berpindah ke LoginActivity
            finish() // Menutup SplashScreenActivity agar tidak bisa dikembalikan dengan tombol back
        }, 4000)
    }
}
