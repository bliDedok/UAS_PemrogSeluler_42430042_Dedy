package com.example.smarthomeiotcatalog

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailCategory: TextView
    private lateinit var tvDetailFunction: TextView
    private lateinit var tvDetailVoltage: TextView
    private lateinit var tvDetailUsage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvDetailName = findViewById(R.id.tvDetailName)
        tvDetailCategory = findViewById(R.id.tvDetailCategory)
        tvDetailFunction = findViewById(R.id.tvDetailFunction)
        tvDetailVoltage = findViewById(R.id.tvDetailVoltage)
        tvDetailUsage = findViewById(R.id.tvDetailUsage)

        val name = intent.getStringExtra("name")
        val category = intent.getStringExtra("category")
        val function = intent.getStringExtra("function")
        val voltage = intent.getStringExtra("voltage")
        val usage = intent.getStringExtra("usage")

        tvDetailName.text = name
        tvDetailCategory.text = "Kategori: $category"
        tvDetailFunction.text = "Fungsi: $function"
        tvDetailVoltage.text = "Tegangan: $voltage"
        tvDetailUsage.text = "Penggunaan: $usage"
    }
}