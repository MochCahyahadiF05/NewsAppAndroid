package com.example.newsapp

// Mendefinisikan data class NewsList
data class NewsList(
    val title: String,        // Judul berita
    val description: String,  // Deskripsi atau ringkasan berita
    val imageUrl: String,     // URL gambar yang mewakili berita
    val date: String          // Tanggal publikasi berita
)
