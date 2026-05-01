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

    private val originalList = mutableListOf<IoTItem>()

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

        iotList.clear()
        iotList.addAll(originalList)

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
                val searchResult = linearSearch(input)

                iotList.clear()
                iotList.addAll(searchResult)
                adapter.notifyDataSetChanged()

                if (searchResult.isEmpty()) {
                    Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "${searchResult.size} data ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnShowAll.setOnClickListener {
            etSearch.setText("")

            iotList.clear()
            iotList.addAll(originalList)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Semua data ditampilkan", Toast.LENGTH_SHORT).show()
        }

        btnSortAZ.setOnClickListener {
            val sortedList = bubbleSortAZ(iotList)

            iotList.clear()
            iotList.addAll(sortedList)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Data diurutkan dari A-Z", Toast.LENGTH_SHORT).show()
        }

        btnSortZA.setOnClickListener {
            val sortedList = bubbleSortZA(iotList)

            iotList.clear()
            iotList.addAll(sortedList)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Data diurutkan dari Z-A", Toast.LENGTH_SHORT).show()
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
        originalList.add(
            IoTItem(
                "ESP32",
                "Mikrokontroler",
                "Mengontrol sistem IoT dan koneksi WiFi",
                "3.3V",
                "Smart lamp dan monitoring rumah"
            )
        )

        originalList.add(
            IoTItem(
                "NodeMCU ESP8266",
                "Mikrokontroler",
                "Board IoT dengan WiFi",
                "3.3V",
                "Kontrol perangkat rumah"
            )
        )

        originalList.add(
            IoTItem(
                "DHT11",
                "Sensor",
                "Mengukur suhu dan kelembapan",
                "3.3V - 5V",
                "Monitoring suhu ruangan"
            )
        )

        originalList.add(
            IoTItem(
                "DHT22",
                "Sensor",
                "Mengukur suhu dan kelembapan lebih akurat",
                "3.3V - 6V",
                "Smart weather station"
            )
        )

        originalList.add(
            IoTItem(
                "PIR Motion Sensor",
                "Sensor",
                "Mendeteksi gerakan manusia",
                "5V",
                "Lampu otomatis dan alarm"
            )
        )

        originalList.add(
            IoTItem(
                "Relay Module",
                "Aktuator",
                "Menghubungkan mikrokontroler ke perangkat listrik",
                "5V",
                "Kontrol lampu dan kipas"
            )
        )

        originalList.add(
            IoTItem(
                "MQ-2 Gas Sensor",
                "Sensor",
                "Mendeteksi asap dan gas",
                "5V",
                "Peringatan kebocoran gas"
            )
        )

        originalList.add(
            IoTItem(
                "RFID RC522",
                "Identifikasi",
                "Membaca kartu RFID",
                "3.3V",
                "Smart door lock"
            )
        )

        originalList.add(
            IoTItem(
                "Ultrasonic HC-SR04",
                "Sensor",
                "Mengukur jarak objek",
                "5V",
                "Deteksi jarak pintu atau objek"
            )
        )

        originalList.add(
            IoTItem(
                "Buzzer Module",
                "Output",
                "Menghasilkan bunyi notifikasi",
                "3.3V - 5V",
                "Alarm rumah"
            )
        )
    }

    private fun linearSearch(keyword: String): MutableList<IoTItem> {
        val result = mutableListOf<IoTItem>()

        for (item in originalList) {
            if (
                item.name.contains(keyword, ignoreCase = true) ||
                item.category.contains(keyword, ignoreCase = true) ||
                item.function.contains(keyword, ignoreCase = true)
            ) {
                result.add(item)
            }
        }

        return result
    }

    private fun bubbleSortAZ(data: List<IoTItem>): MutableList<IoTItem> {
        val sortedList = data.toMutableList()

        for (i in 0 until sortedList.size - 1) {
            for (j in 0 until sortedList.size - i - 1) {
                if (sortedList[j].name.compareTo(sortedList[j + 1].name, ignoreCase = true) > 0) {
                    val temp = sortedList[j]
                    sortedList[j] = sortedList[j + 1]
                    sortedList[j + 1] = temp
                }
            }
        }

        return sortedList
    }

    private fun bubbleSortZA(data: List<IoTItem>): MutableList<IoTItem> {
        val sortedList = data.toMutableList()

        for (i in 0 until sortedList.size - 1) {
            for (j in 0 until sortedList.size - i - 1) {
                if (sortedList[j].name.compareTo(sortedList[j + 1].name, ignoreCase = true) < 0) {
                    val temp = sortedList[j]
                    sortedList[j] = sortedList[j + 1]
                    sortedList[j + 1] = temp
                }
            }
        }

        return sortedList
    }
}