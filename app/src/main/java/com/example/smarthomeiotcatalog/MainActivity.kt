package com.example.smarthomeiotcatalog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: TextView
    private lateinit var btnShowAll: TextView
    private lateinit var btnSortAZ: TextView
    private lateinit var btnSortZA: TextView
    private lateinit var listViewIoT: ListView
    private lateinit var adapter: IoTPreviewAdapter

    private val originalList = mutableListOf<IoTItem>()
    private val iotList = mutableListOf<IoTItem>()

    companion object {
        private const val TAG = "42430042"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_main)

            Log.d(TAG, "MainActivity dibuka")

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

            Log.d(TAG, "Data katalog IoT berhasil dimuat: ${iotList.size} item")

            btnSearch.setOnClickListener {
                try {
                    val input = etSearch.text.toString().trim()

                    Log.d(TAG, "Tombol Cari ditekan dengan keyword: $input")

                    if (input.isEmpty()) {
                        etSearch.error = "Masukkan nama modul terlebih dahulu"
                        Toast.makeText(this, "Input pencarian tidak boleh kosong", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Validasi gagal: input pencarian kosong")
                    } else if (input.length < 3) {
                        etSearch.error = "Minimal 3 karakter"
                        Toast.makeText(this, "Masukkan minimal 3 karakter", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Validasi gagal: input kurang dari 3 karakter")
                    } else {
                        val searchResult = linearSearch(input)

                        iotList.clear()
                        iotList.addAll(searchResult)
                        adapter.notifyDataSetChanged()

                        if (searchResult.isEmpty()) {
                            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "Pencarian selesai: data tidak ditemukan")
                        } else {
                            Toast.makeText(this, "${searchResult.size} data ditemukan", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "Pencarian selesai: ${searchResult.size} data ditemukan")
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Terjadi kesalahan saat pencarian", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error saat melakukan pencarian: ${e.message}", e)
                }
            }

            btnShowAll.setOnClickListener {
                try {
                    etSearch.setText("")

                    iotList.clear()
                    iotList.addAll(originalList)
                    adapter.notifyDataSetChanged()

                    Toast.makeText(this, "Semua data ditampilkan", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Semua data katalog IoT ditampilkan kembali")
                } catch (e: Exception) {
                    Toast.makeText(this, "Terjadi kesalahan saat menampilkan semua data", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error saat menampilkan semua data: ${e.message}", e)
                }
            }

            btnSortAZ.setOnClickListener {
                try {
                    val sortedList = bubbleSortAZ(iotList)

                    iotList.clear()
                    iotList.addAll(sortedList)
                    adapter.notifyDataSetChanged()

                    Toast.makeText(this, "Data diurutkan dari A-Z", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Sorting A-Z berhasil dilakukan")
                } catch (e: Exception) {
                    Toast.makeText(this, "Terjadi kesalahan saat sorting A-Z", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error saat sorting A-Z: ${e.message}", e)
                }
            }

            btnSortZA.setOnClickListener {
                try {
                    val sortedList = bubbleSortZA(iotList)

                    iotList.clear()
                    iotList.addAll(sortedList)
                    adapter.notifyDataSetChanged()

                    Toast.makeText(this, "Data diurutkan dari Z-A", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Sorting Z-A berhasil dilakukan")
                } catch (e: Exception) {
                    Toast.makeText(this, "Terjadi kesalahan saat sorting Z-A", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error saat sorting Z-A: ${e.message}", e)
                }
            }

            listViewIoT.setOnItemClickListener { _, _, position, _ ->
                try {
                    val selectedItem = iotList[position]

                    Log.d(TAG, "Item dipilih: ${selectedItem.name}")

                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("name", selectedItem.name)
                    intent.putExtra("category", selectedItem.category)
                    intent.putExtra("function", selectedItem.function)
                    intent.putExtra("voltage", selectedItem.voltage)
                    intent.putExtra("usage", selectedItem.usage)
                    intent.putExtra("imageResId", selectedItem.imageResId)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Terjadi kesalahan saat membuka detail", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error saat membuka DetailActivity: ${e.message}", e)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Terjadi kesalahan saat membuka aplikasi", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error pada MainActivity: ${e.message}", e)
        }
    }

    private fun loadPreviewData() {
        try {
            originalList.clear()

            originalList.add(
                IoTItem(
                    "ESP32",
                    "Mikrokontroler",
                    "Mengontrol sistem IoT dan koneksi WiFi",
                    "3.3V",
                    "Smart lamp dan monitoring rumah",
                    R.drawable.esp32
                )
            )

            originalList.add(
                IoTItem(
                    "NodeMCU ESP8266",
                    "Mikrokontroler",
                    "Board IoT dengan WiFi",
                    "3.3V",
                    "Kontrol perangkat rumah",
                    R.drawable.nodemcu
                )
            )

            originalList.add(
                IoTItem(
                    "DHT11",
                    "Sensor",
                    "Mengukur suhu dan kelembapan",
                    "3.3V - 5V",
                    "Monitoring suhu ruangan",
                    R.drawable.dht11
                )
            )

            originalList.add(
                IoTItem(
                    "DHT22",
                    "Sensor",
                    "Mengukur suhu dan kelembapan lebih akurat",
                    "3.3V - 6V",
                    "Smart weather station",
                    R.drawable.dht22
                )
            )

            originalList.add(
                IoTItem(
                    "PIR Motion Sensor",
                    "Sensor",
                    "Mendeteksi gerakan manusia",
                    "5V",
                    "Lampu otomatis dan alarm",
                    R.drawable.pir_sensor
                )
            )

            originalList.add(
                IoTItem(
                    "Relay Module",
                    "Aktuator",
                    "Menghubungkan mikrokontroler ke perangkat listrik",
                    "5V",
                    "Kontrol lampu dan kipas",
                    R.drawable.relay_module
                )
            )

            originalList.add(
                IoTItem(
                    "MQ-2 Gas Sensor",
                    "Sensor",
                    "Mendeteksi asap dan gas",
                    "5V",
                    "Peringatan kebocoran gas",
                    R.drawable.mq2_sensor
                )
            )

            originalList.add(
                IoTItem(
                    "RFID RC522",
                    "Identifikasi",
                    "Membaca kartu RFID",
                    "3.3V",
                    "Smart door lock",
                    R.drawable.rfid_rc522
                )
            )

            originalList.add(
                IoTItem(
                    "Ultrasonic HC-SR04",
                    "Sensor",
                    "Mengukur jarak objek",
                    "5V",
                    "Deteksi jarak pintu atau objek",
                    R.drawable.ultrasonic_sensor
                )
            )

            originalList.add(
                IoTItem(
                    "Buzzer Module",
                    "Output",
                    "Menghasilkan bunyi notifikasi",
                    "3.3V - 5V",
                    "Alarm rumah",
                    R.drawable.buzzer_module
                )
            )

            Log.d(TAG, "loadPreviewData berhasil menambahkan ${originalList.size} item")
        } catch (e: Exception) {
            Log.e(TAG, "Error saat memuat data katalog: ${e.message}", e)
        }
    }

    private fun linearSearch(keyword: String): MutableList<IoTItem> {
        val result = mutableListOf<IoTItem>()

        try {
            for (item in originalList) {
                if (
                    item.name.contains(keyword, ignoreCase = true) ||
                    item.category.contains(keyword, ignoreCase = true) ||
                    item.function.contains(keyword, ignoreCase = true)
                ) {
                    result.add(item)
                }
            }

            Log.d(TAG, "Linear Search dijalankan untuk keyword: $keyword")
        } catch (e: Exception) {
            Log.e(TAG, "Error pada fungsi linearSearch: ${e.message}", e)
        }

        return result
    }

    private fun bubbleSortAZ(data: List<IoTItem>): MutableList<IoTItem> {
        val sortedList = data.toMutableList()

        try {
            for (i in 0 until sortedList.size - 1) {
                for (j in 0 until sortedList.size - i - 1) {
                    if (sortedList[j].name.compareTo(sortedList[j + 1].name, ignoreCase = true) > 0) {
                        val temp = sortedList[j]
                        sortedList[j] = sortedList[j + 1]
                        sortedList[j + 1] = temp
                    }
                }
            }

            Log.d(TAG, "Bubble Sort A-Z dijalankan")
        } catch (e: Exception) {
            Log.e(TAG, "Error pada fungsi bubbleSortAZ: ${e.message}", e)
        }

        return sortedList
    }

    private fun bubbleSortZA(data: List<IoTItem>): MutableList<IoTItem> {
        val sortedList = data.toMutableList()

        try {
            for (i in 0 until sortedList.size - 1) {
                for (j in 0 until sortedList.size - i - 1) {
                    if (sortedList[j].name.compareTo(sortedList[j + 1].name, ignoreCase = true) < 0) {
                        val temp = sortedList[j]
                        sortedList[j] = sortedList[j + 1]
                        sortedList[j + 1] = temp
                    }
                }
            }

            Log.d(TAG, "Bubble Sort Z-A dijalankan")
        } catch (e: Exception) {
            Log.e(TAG, "Error pada fungsi bubbleSortZA: ${e.message}", e)
        }

        return sortedList
    }
}