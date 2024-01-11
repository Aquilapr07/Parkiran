package com.example.parkiran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        loadFragment(home())


        //definisi widget
        var buttomnav = findViewById<BottomNavigationView>(R.id.buttomnavview)
        buttomnav.setOnItemSelectedListener {

            when(it.itemId){
                R.id.bot_menu_home -> {
                    loadFragment(home())
                    true
                }
                R.id.bot_menu_smob -> {
                    loadFragment(smob())
                    true
                }
                R.id.bot_menu_smot -> {
                    loadFragment(DataFragment())
                    true
                }
                R.id.bot_menu_data -> {
                    loadFragment(Data())
                    true
                }
                else -> {false}
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_countainer,fragment)
        transaction.commit()
    }
}