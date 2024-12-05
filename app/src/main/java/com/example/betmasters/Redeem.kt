package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Redeem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_redeem)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val homeItem: LinearLayout = findViewById(R.id.iconHome)
        val redeemItem: LinearLayout = findViewById(R.id.iconRedeem)
        val gamesItem: LinearLayout = findViewById(R.id.iconGames)

        homeItem.setOnClickListener {
            navigateToActivity(Home::class.java)
        }

        gamesItem.setOnClickListener {
            navigateToActivity(Games::class.java)
        }


        val profileButton: ImageButton = findViewById(R.id.btnPerfil)

        profileButton.setOnClickListener {
            navigateToActivity(SettingsActivity::class.java)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}