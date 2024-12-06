package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings2)


        val backBtn = findViewById<ImageButton>(R.id.btnBack)

        val source = intent.getStringExtra("source")
        val bundle = Bundle()
        bundle.putString("source", source)

        val settingsFragment = SettingsFragment()
         settingsFragment.arguments = bundle

        backBtn.setOnClickListener {v ->
            val source = intent.getStringExtra("source")
            when (source){
                "home" -> startActivity(Intent(this, Home::class.java))
                "redeem" -> startActivity(Intent(this, Redeem::class.java))
                "games" -> startActivity(Intent(this, Games::class.java))
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentSettings, settingsFragment).commit()
    }
}