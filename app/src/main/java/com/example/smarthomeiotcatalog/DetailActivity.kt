package com.example.smarthomeiotcatalog

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class DetailActivity : AppCompatActivity() {

    private lateinit var imgDetailIoT: ImageView
    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailCategory: TextView
    private lateinit var tvDetailFunction: TextView
    private lateinit var tvDetailVoltage: TextView
    private lateinit var tvDetailUsage: TextView

    private lateinit var btnBack: Button

    companion object {
        private const val TAG = "42430042"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_detail)

            Log.d(TAG, "DetailActivity dibuka")

            imgDetailIoT = findViewById(R.id.imgDetail)
            tvDetailName = findViewById(R.id.tvDetailName)
            tvDetailCategory = findViewById(R.id.tvDetailCategory)
            tvDetailFunction = findViewById(R.id.tvDetailFunction)
            tvDetailVoltage = findViewById(R.id.tvDetailVoltage)
            tvDetailUsage = findViewById(R.id.tvDetailUsage)
            btnBack = findViewById(R.id.btnBack)



            val name = intent.getStringExtra("name") ?: "Data tidak tersedia"
            val category = intent.getStringExtra("category") ?: "Data tidak tersedia"
            val function = intent.getStringExtra("function") ?: "Data tidak tersedia"
            val voltage = intent.getStringExtra("voltage") ?: "Data tidak tersedia"
            val usage = intent.getStringExtra("usage") ?: "Data tidak tersedia"

            // Mengambil gambar dari MainActivity.
            // Jika data gambar tidak terbaca, aplikasi otomatis memakai gambar default esp32.
            val imageResId = intent.getIntExtra("imageResId", R.drawable.esp32)

            imgDetailIoT.setImageResource(imageResId)
            tvDetailName.text = name
            tvDetailCategory.text = "Kategori: $category"
            tvDetailFunction.text = "Fungsi: $function"
            tvDetailVoltage.text = "Tegangan: $voltage"
            tvDetailUsage.text = "Penggunaan: $usage"

            Log.d(TAG, "Detail item berhasil ditampilkan: $name")
        } catch (e: Exception) {
            Toast.makeText(this, "Terjadi kesalahan saat menampilkan detail", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error pada DetailActivity: ${e.message}", e)
        }

        btnBack.setOnClickListener {
            Log.d(TAG, "Tombol kembali ditekan")
            finish()
        }
    }
}