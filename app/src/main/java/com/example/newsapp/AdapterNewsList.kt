package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AdapterNewsList(context: Context, private val newsList: List<NewsList>) :
    ArrayAdapter<NewsList>(context, R.layout.item_news, newsList) {

    // Fungsi ini akan dipanggil untuk setiap item dalam list
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Jika convertView null, inflate tampilan dari item_news.xml
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)

        // Ambil objek NewsList pada posisi tertentu
        val news = newsList[position]

        // Ambil view dari layout item_news
        val ivNewsImage = view.findViewById<ImageView>(R.id.ivNewsImage)
        val tvNewsTitle = view.findViewById<TextView>(R.id.tvNewsTitle)
        val tvNewsDescription = view.findViewById<TextView>(R.id.tvNewsDescription)
        val tvNewsDate = view.findViewById<TextView>(R.id.tvNewsDate)

        // Gunakan Glide untuk memuat gambar dari URL ke dalam ImageView
        Glide.with(context)
            .load(news.imageUrl) // URL gambar
            .placeholder(R.drawable.no_image) // Gambar default saat loading
            .error(R.drawable.no_image) // Gambar default jika gagal memuat
            .into(ivNewsImage) // Target ImageView

        // Set data berita ke tampilan
        tvNewsTitle.text = news.title
        tvNewsDescription.text = news.description
        tvNewsDate.text = news.date

        // Kembalikan view yang telah dimodifikasi
        return view
    }
}