package com.example.newsapp // Menentukan package dari file ini

// Import library yang dibutuhkan
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewsDashboardActivity : AppCompatActivity() {

    // Deklarasi view dan variabel
    private lateinit var listViewNews: ListView
    private lateinit var tvUsername: TextView
    private lateinit var newsAdapter: AdapterNewsList
    private val newsList = ArrayList<NewsList>() // List untuk menyimpan data berita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_dashboard) // Mengatur layout activity

        // Membuat status bar transparan
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            statusBarColor = Color.TRANSPARENT
        }

        // Mengambil username yang dikirim dari LoginActivity atau RegisterActivity
        val username = intent.getStringExtra("USERNAME") ?: "User"

        // Inisialisasi view dari layout
        listViewNews = findViewById(R.id.listViewNews)
        tvUsername = findViewById(R.id.tvUsername)
        tvUsername.text = username // Menampilkan nama user di tampilan

        // Log untuk menunjukkan bahwa Dashboard sudah dibuka
        Log.d("NewsDashboardActivity", "Dashboard dibuka oleh: $username")

        // Tampilkan pesan selamat datang
        Toast.makeText(this, "Selamat datang $username di NewsApps", Toast.LENGTH_LONG).show()

        // Menyiapkan data berita
        prepareNewsData()

        // Membuat dan mengatur adapter untuk ListView
        newsAdapter = AdapterNewsList(this, newsList)
        listViewNews.adapter = newsAdapter

        // Event ketika item list diklik
        listViewNews.setOnItemClickListener { _, _, position, _ ->
            val selectedNews = newsList[position] // Ambil berita yang dipilih
            Toast.makeText(this, "Berita dipilih: ${selectedNews.title}", Toast.LENGTH_SHORT).show()
            Log.d("NewsDashboardActivity", "Berita dipilih: ${selectedNews.title}")
        }
    }

    // Fungsi untuk menambahkan data berita ke dalam list
    private fun prepareNewsData() {

        // Menambahkan berita secara manual ke dalam list
        newsList.add(NewsList(
            "Indonesia Berhasil Kurangi Emisi Karbon",
            "Indonesia mencapai penurunan emisi karbon sebesar 20% dalam dua tahun terakhir melalui program reboisasi nasional.",
            "https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXJsfGVufDB8fDB8fHww",
            "10 Mei 2025"
        ))

        newsList.add(NewsList(
            "Teknologi AI Lokal Tembus Pasar Global",
            "Startup AI dari Indonesia berhasil mendapatkan pendanaan seri B sebesar $50 juta dari investor internasional.",
            "https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXJsfGVufDB8fDB8fHww",
            "9 Mei 2025"
        ))

        newsList.add(NewsList(
            "Prestasi Tim Voli Indonesia di Asian Games",
            "Tim voli Indonesia berhasil meraih medali emas setelah mengalahkan Jepang dengan skor 3-1 di final.",
            "https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXJsfGVufDB8fDB8fHww",
            "8 Mei 2025"
        ))

        newsList.add(NewsList(
            "Inovasi Transportasi Ramah Lingkungan",
            "Kendaraan listrik buatan dalam negeri mulai diproduksi massal dan akan dijual dengan harga terjangkau tahun depan.",
            "https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXJsfGVufDB8fDB8fHww",
            "7 Mei 2025"
        ))

        newsList.add(NewsList(
            "Festival Film Indonesia Mendapat Pengakuan Internasional",
            "Film Indonesia 'Pulang' berhasil memenangkan penghargaan di Festival Film Cannes tahun ini.",
            "https://via.placeholder.com/150",
            "6 Mei 2025"
        ))

        newsList.add(NewsList(
            "Perkembangan Ekonomi Digital Indonesia",
            "Ekonomi digital Indonesia tumbuh 15% selama pandemi, didukung oleh inovasi e-commerce dan fintech.",
            "https://via.placeholder.com/150",
            "5 Mei 2025"
        ))

        newsList.add(NewsList(
            "Riset Vaksin Baru Karya Anak Bangsa",
            "Tim peneliti dari ITB mengembangkan vaksin baru untuk mencegah penyakit tropis yang efektif dan terjangkau.",
            "https://via.placeholder.com/150",
            "4 Mei 2025"
        ))

        newsList.add(NewsList(
            "Persib Juara Liga 1 Indonesia",
            "Tim Persib Bandung Menjuarai liga 1 indonesia dan akan bermain di asia.",
            "https://juaranews.com/wp-content/uploads/2025/05/persib-juara.webp",
            "2 Mei 2025"
        ))
    }
}
