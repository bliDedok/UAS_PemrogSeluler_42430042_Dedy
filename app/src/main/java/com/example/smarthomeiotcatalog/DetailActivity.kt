package com.example.smarthomeiotcatalog

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailCategory: TextView
    private lateinit var tvDetailFunction: TextView
    private lateinit var tvDetailVoltage: TextView
    private lateinit var tvDetailUsage: TextView

    companion object {
        private const val TAG = "42430042"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_detail)

            Log.d(TAG, "DetailActivity dibuka")

            tvDetailName = findViewById(R.id.tvDetailName)
            tvDetailCategory = findViewById(R.id.tvDetailCategory)
            tvDetailFunction = findViewById(R.id.tvDetailFunction)
            tvDetailVoltage = findViewById(R.id.tvDetailVoltage)
            tvDetailUsage = findViewById(R.id.tvDetailUsage)

            val name = intent.getStringExtra("name") ?: "Data tidak tersedia"
            val category = intent.getStringExtra("category") ?: "Data tidak tersedia"
            val function = intent.getStringExtra("function") ?: "Data tidak tersedia"
            val voltage = intent.getStringExtra("voltage") ?: "Data tidak tersedia"
            val usage = intent.getStringExtra("usage") ?: "Data tidak tersedia"

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
    }
}