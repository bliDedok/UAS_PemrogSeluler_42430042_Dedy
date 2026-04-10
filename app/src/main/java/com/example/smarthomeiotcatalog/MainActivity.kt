package com.example.smarthomeiotcatalog

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
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        btnShowAll.setOnClickListener {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        btnSortAZ.setOnClickListener {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }

        btnSortZA.setOnClickListener {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadPreviewData() {
        iotList.add(IoTItem("ESP32", "Mikrokontroler"))
        iotList.add(IoTItem("NodeMCU ESP8266", "Mikrokontroler"))
        iotList.add(IoTItem("DHT11", "Sensor"))
        iotList.add(IoTItem("DHT22", "Sensor"))
        iotList.add(IoTItem("PIR Motion Sensor", "Sensor"))
        iotList.add(IoTItem("Relay Module", "Aktuator"))
        iotList.add(IoTItem("MQ-2 Gas Sensor", "Sensor"))
        iotList.add(IoTItem("RFID RC522", "Identifikasi"))
        iotList.add(IoTItem("Ultrasonic HC-SR04", "Sensor"))
        iotList.add(IoTItem("Buzzer Module", "Output"))
    }
}