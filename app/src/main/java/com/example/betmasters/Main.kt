package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val profileButton: ImageButton = findViewById(R.id.btnPerfil)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        profileButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            val selectedItemId = bottomNavigationView.selectedItemId
            val source = when(selectedItemId){
                R.id.home -> "home"
                R.id.game -> "game"
                R.id.redeem -> "redeem"
                else -> "home"
            }
            intent.putExtra("source", "home")
            startActivity(intent)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when(item.itemId){
                R.id.home -> selectedFragment = HomeFragment()
                R.id.redeem -> selectedFragment = RedeemFragment()
                R.id.game -> selectedFragment = GamesFragment()
            }
            if(selectedFragment != null){
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            true
        }
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.home
        }
    }
}