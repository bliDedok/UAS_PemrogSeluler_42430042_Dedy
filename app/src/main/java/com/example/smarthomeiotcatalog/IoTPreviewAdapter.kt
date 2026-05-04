package com.example.smarthomeiotcatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class IoTPreviewAdapter(
    private val context: Context,
    private val data: List<IoTItem>
) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_iot_preview, parent, false)

        val imgIoT = view.findViewById<ImageView>(R.id.imgIoT)
        val tvItemName = view.findViewById<TextView>(R.id.tvItemName)
        val tvItemCategory = view.findViewById<TextView>(R.id.tvItemCategory)
        val tvItemFunction = view.findViewById<TextView>(R.id.tvItemFunction)

        val item = data[position]

        imgIoT.setImageResource(item.imageResId)
        tvItemName.text = item.name
        tvItemCategory.text = item.category
        tvItemFunction.text = item.function

        return view
    }
}