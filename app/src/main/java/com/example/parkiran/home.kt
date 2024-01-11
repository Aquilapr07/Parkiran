package com.example.parkiran

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.parkiran.config.Config
import com.example.parkiran.model.ModelSlot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home : Fragment() {

    private lateinit var slot1: TextView
    private lateinit var slot2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize your TextViews and other views
        slot1 = rootView.findViewById(R.id.mobil_isi)
        slot2 = rootView.findViewById(R.id.motor_isi)

        // Add this block to handle ImageView click for BmkgListActivity
        val imageViewNavigate: ImageView = rootView.findViewById(R.id.imageViewNavigate)
        imageViewNavigate.setOnClickListener {
            // Handle the click event, initiate the transition to BmkgListActivity
            val intent = Intent(activity, BmkgListActivity::class.java)
            startActivity(intent)
        }

        // Add this block to handle ImageView click for DatabaseActivity
        val profileImageView: ImageView = rootView.findViewById(R.id.profile)
        profileImageView.setOnClickListener {
            // Handle the click event, initiate the transition to DatabaseActivity
            val intent = Intent(activity, DatabaseActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Config().getSlot1Service()
            .getSlot1Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("quil", "data json: " + response.body())
                    slot1.text = response.body()?.totalKendaraan.toString()
                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("quil", "error: " + t.message.toString())
                }
            })

        Config().getSlot2Service()
            .getSlot2Data()
            .enqueue(object : Callback<ModelSlot> {
                override fun onResponse(call: Call<ModelSlot>, response: Response<ModelSlot>) {
                    Log.d("quil", "data json: " + response.body())
                    slot2.text = response.body()?.totalKendaraan.toString()
                }

                override fun onFailure(call: Call<ModelSlot>, t: Throwable) {
                    Log.d("quil", "error: " + t.message.toString())
                }
            })
    }

    companion object {
        // Gunakan satu set konstanta untuk parameter
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
