package com.example.smarthomeiotcatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class IoTPreviewAdapter(
    context: Context,
    private val items: List<IoTItem>
) : ArrayAdapter<IoTItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_iot, parent, false)

        val item = items[position]

        val tvName = view.findViewById<TextView>(R.id.tvItemName)
        val tvCategory = view.findViewById<TextView>(R.id.tvItemCategory)

        tvName.text = item.name
        tvCategory.text = "Kategori: ${item.category}"

        return view
    }
}