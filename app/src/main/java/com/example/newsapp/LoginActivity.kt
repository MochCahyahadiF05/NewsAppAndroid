package com.example.newsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() { // Deklarasi class LoginActivity yang mewarisi AppCompatActivity

    // Deklarasi variabel untuk elemen input dan tombol
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) { // Fungsi yang dipanggil saat activity pertama kali dibuat
        super.onCreate(savedInstanceState) // Memanggil method dari superclass
        setContentView(R.layout.activity_login) // Mengatur layout yang digunakan (activity_login.xml)

        // Mengatur status bar agar transparan dan tampilan fullscreen
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE // Layout ditampilkan fullscreen dan stabil saat status bar muncul
            statusBarColor = Color.TRANSPARENT // Mengubah status bar menjadi transparan
        }

        // Menghubungkan komponen UI dengan variabel
        etUsername = findViewById(R.id.etUsername) // Menghubungkan EditText username
        etPassword = findViewById(R.id.etPassword) // Menghubungkan EditText password
        btnLogin = findViewById(R.id.btnLogin) // Menghubungkan Button login

        // Membuat teks "Belum punya akun? Daftar" menjadi sebagian bisa diklik
        val registerTextView = findViewById<TextView>(R.id.registerTextView)
        val fullText = "Belum punya akun? Daftar" // Teks penuh
        val spannable = SpannableString(fullText) // Buat objek SpannableString untuk bisa diberi gaya

        val blueColor = ForegroundColorSpan(Color.parseColor("#0033A0")) // Warna biru untuk teks "Daftar"
        spannable.setSpan(blueColor, 18, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) // Terapkan warna biru dari karakter ke-18 sampai akhir

        // Membuat bagian "Daftar" dapat diklik
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java) // Intent ke RegisterActivity
                startActivity(intent) // Mulai activity register
            }
        }
        spannable.setSpan(clickableSpan, 18, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) // Terapkan span klik ke kata "Daftar"

        registerTextView.text = spannable // Menampilkan teks yang telah diformat
        registerTextView.movementMethod = LinkMovementMethod.getInstance() // Mengaktifkan klik pada TextView

        // Fungsi saat tombol login ditekan
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim() // Ambil teks dari input username
            val password = etPassword.text.toString().trim() // Ambil teks dari input password

            // Validasi: jika username atau password kosong
            if (username.isEmpty() || password.isEmpty()) {
                Log.e("LoginActivity", "Login gagal: Username atau password kosong") // Tampilkan log error
                Toast.makeText(this, "Username dan password harus diisi!", Toast.LENGTH_SHORT).show() // Tampilkan pesan ke pengguna
                return@setOnClickListener // Keluar dari fungsi klik
            }

            // Jika input tidak kosong, anggap login berhasil (simulasi)
            Log.d("LoginActivity", "Login berhasil: $username") // Tampilkan log debug
            Toast.makeText(this, "Login berhasil, selamat datang $username!", Toast.LENGTH_SHORT).show() // Tampilkan pesan sukses

            // Pindah ke NewsDashboardActivity, kirim username sebagai data
            val intent = Intent(this, NewsDashboardActivity::class.java)
            intent.putExtra("USERNAME", username) // Kirim username ke activity berikutnya
            startActivity(intent) // Jalankan NewsDashboardActivity
        }
    }
}