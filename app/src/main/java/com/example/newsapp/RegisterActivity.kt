package com.example.newsapp

import android.content.Intent
import android.content.res.Resources
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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() { // Deklarasi class RegisterActivity

    // Deklarasi variabel untuk input dan tombol
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) { // Fungsi utama saat Activity dibuat
        super.onCreate(savedInstanceState) // Panggil onCreate superclass
        enableEdgeToEdge() // Opsional, untuk layout modern penuh layar (fitur baru Jetpack)
        setContentView(R.layout.activity_register) // Atur tampilan dari layout XML

        // Inisialisasi semua komponen UI
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // Logika ketika tombol "Daftar" diklik
        btnRegister.setOnClickListener {
            val fullName = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editTextConfirmPassword.text.toString().trim()

            // Validasi form, jika valid baru lanjut
            if (validateForm(fullName, email, password, confirmPassword)) {
                // Catat log untuk percobaan registrasi
                Log.d("RegisterActivity", "Registration attempt - Name: $fullName, Email: $email")

                // Tampilkan Toast pertama
                Toast.makeText(this, "Proses pendaftaran...", Toast.LENGTH_SHORT).show()

                // (Simulasi) Lanjut ke proses pendaftaran sebenarnya di sini jika ada backend

                // Tampilkan Toast kedua setelah pendaftaran berhasil
                Toast.makeText(this, "Pendaftaran berhasil! Selamat datang $fullName", Toast.LENGTH_LONG).show()

                // Pindah ke halaman dashboard
                val intent = Intent(this, NewsDashboardActivity::class.java)
                intent.putExtra("USER_NAME", fullName) // Kirim nama user ke activity berikutnya
                startActivity(intent)
                finish() // Tutup activity registrasi agar tidak bisa kembali dengan tombol back
            }
        }

        // Atur teks yang bisa diklik "Login"
        setupLoginText()
    }

    // Fungsi untuk mengatur teks "Sudah punya akun? Login"
    private fun setupLoginText() {
        val loginTextView = findViewById<TextView>(R.id.loginTextView)
        val fullText = "Sudah punya akun? Login"
        val spannable = SpannableString(fullText)

        val loginStartIndex = fullText.indexOf("Login") // Temukan posisi kata "Login"

        // Warnai teks "Login" dengan warna biru
        val blueColor = ForegroundColorSpan(Color.parseColor("#0033A0"))
        spannable.setSpan(blueColor, loginStartIndex, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Buat teks "Login" bisa diklik
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Pindah ke halaman Login
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish() // Tutup activity saat ini
            }
        }
        spannable.setSpan(clickableSpan, loginStartIndex, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Terapkan teks dan aktifkan link movement
        loginTextView.text = spannable
        loginTextView.movementMethod = LinkMovementMethod.getInstance()
    }

    // Fungsi validasi data dari form
    private fun validateForm(fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        if (fullName.isEmpty()) {
            editTextName.error = "Silakan masukkan nama lengkap Anda"
            editTextName.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            editTextEmail.error = "Silakan masukkan email Anda"
            editTextEmail.requestFocus()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Silakan masukkan alamat email yang valid"
            editTextEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Silakan masukkan password"
            editTextPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            editTextPassword.error = "Password harus minimal 6 karakter"
            editTextPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.error = "Silakan konfirmasi password Anda"
            editTextConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            editTextConfirmPassword.error = "Password tidak cocok"
            editTextConfirmPassword.requestFocus()
            return false
        }

        return true // Semua validasi berhasil
    }
}