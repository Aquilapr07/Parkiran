package com.example.parkiran.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.parkiran.BmkgListActivity
import com.example.parkiran.R
import com.example.parkiran.model.GempaItem
import com.example.parkiran.model.ModelList

class AdapterList(val data: ModelList?, val context: BmkgListActivity, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.list_view, _g) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_view, null, true)

        //indexing
        var _idx = rowView.findViewById<TextView>(R.id.lst_nomor)
        //tanggal
        var _tgl = rowView.findViewById<TextView>(R.id.lst_tanggal)
        //koordinat
        var _koordinat = rowView.findViewById<TextView>(R.id.lst_koordinat)
        //wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.lst_wilayah)

        //pengisian data
        _idx.setText((position + 1).toString())
        Log.d("quil", "posisi: " + (position + 1))

        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        Log.d("quil", "Tanggal: " + data?.infogempa?.gempa?.get(position)?.tanggal)

        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        Log.d("quil", "Koordinat: " + data?.infogempa?.gempa?.get(position)?.coordinates)

        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        Log.d("quil", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.wilayah)

        return rowView
    }
}