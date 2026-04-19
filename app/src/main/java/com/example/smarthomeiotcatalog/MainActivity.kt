package com.example.smarthomeiotcatalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnShowAll: Button
    private lateinit var btnSortAZ: Button
    private lateinit var btnSortZA: Button
    private lateinit var listViewIoT: ListView

    private lateinit var adapter: IoTPreviewAdapter
    private val iotList = mutableListOf<IoTItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        btnShowAll = findViewById(R.id.btnShowAll)
        btnSortAZ = findViewById(R.id.btnSortAZ)
        btnSortZA = findViewById(R.id.btnSortZA)
        listViewIoT = findViewById(R.id.listViewIoT)

        loadPreviewData()

        adapter = IoTPreviewAdapter(this, iotList)
        listViewIoT.adapter = adapter

        btnSearch.setOnClickListener {
            val input = etSearch.text.toString().trim()

            if (input.isEmpty()) {
                etSearch.error = "Masukkan nama modul terlebih dahulu"
                Toast.makeText(this, "Input pencarian tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (input.length < 3) {
                etSearch.error = "Minimal 3 karakter"
                Toast.makeText(this, "Masukkan minimal 3 karakter", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Input valid, fitur pencarian dikerjakan di Minggu 3", Toast.LENGTH_SHORT).show()
            }
        }

        btnShowAll.setOnClickListener {
            etSearch.setText("")
            Toast.makeText(this, "Semua data ditampilkan", Toast.LENGTH_SHORT).show()
        }

        btnSortAZ.setOnClickListener {
            Toast.makeText(this, "Fitur sorting A-Z dikerjakan di Minggu 3", Toast.LENGTH_SHORT).show()
        }

        btnSortZA.setOnClickListener {
            Toast.makeText(this, "Fitur sorting Z-A dikerjakan di Minggu 3", Toast.LENGTH_SHORT).show()
        }

        listViewIoT.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = iotList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", selectedItem.name)
            intent.putExtra("category", selectedItem.category)
            intent.putExtra("function", selectedItem.function)
            intent.putExtra("voltage", selectedItem.voltage)
            intent.putExtra("usage", selectedItem.usage)

            startActivity(intent)
        }
    }

    private fun loadPreviewData() {
        iotList.add(IoTItem("ESP32", "Mikrokontroler", "Mengontrol sistem IoT dan koneksi WiFi", "3.3V", "Smart lamp dan monitoring rumah"))
        iotList.add(IoTItem("NodeMCU ESP8266", "Mikrokontroler", "Board IoT dengan WiFi", "3.3V", "Kontrol perangkat rumah"))
        iotList.add(IoTItem("DHT11", "Sensor", "Mengukur suhu dan kelembapan", "3.3V - 5V", "Monitoring suhu ruangan"))
        iotList.add(IoTItem("DHT22", "Sensor", "Mengukur suhu dan kelembapan lebih akurat", "3.3V - 6V", "Smart weather station"))
        iotList.add(IoTItem("PIR Motion Sensor", "Sensor", "Mendeteksi gerakan manusia", "5V", "Lampu otomatis dan alarm"))
        iotList.add(IoTItem("Relay Module", "Aktuator", "Menghubungkan mikrokontroler ke perangkat listrik", "5V", "Kontrol lampu dan kipas"))
        iotList.add(IoTItem("MQ-2 Gas Sensor", "Sensor", "Mendeteksi asap dan gas", "5V", "Peringatan kebocoran gas"))
        iotList.add(IoTItem("RFID RC522", "Identifikasi", "Membaca kartu RFID", "3.3V", "Smart door lock"))
        iotList.add(IoTItem("Ultrasonic HC-SR04", "Sensor", "Mengukur jarak objek", "5V", "Deteksi jarak pintu atau objek"))
        iotList.add(IoTItem("Buzzer Module", "Output", "Menghasilkan bunyi notifikasi", "3.3V - 5V", "Alarm rumah"))
    }
}