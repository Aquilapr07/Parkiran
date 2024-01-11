package com.example.parkiran

import android.content.Intent
import android.graphics.ColorSpace.Model
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.parkiran.R
import com.example.parkiran.adapter.AdapterList
import com.example.parkiran.config.ConfigList
import com.example.parkiran.databinding.ActivityBmkgListBinding
import com.example.parkiran.model.ModelList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BmkgListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmkgListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmkgListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val _listview = findViewById<ListView>(R.id.list_gempa)

        ConfigList().getService()
            .getgempa()
            .enqueue(object : Callback<ModelList> {
                override fun onResponse(
                    call: Call<ModelList>,
                    response: Response<ModelList>
                ) {
                    Log.d("quil", "data json: " + response.body())
                    _listview.adapter = AdapterList(response.body(), this@BmkgListActivity,
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<ModelList>, t: Throwable) {
                    Log.d("quil", "error: " + t.message.toString())
                }
            })
    }
}