package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val openSheetButton: CardView = findViewById(R.id.card_filter_fav)

        openSheetButton.setOnClickListener {
            val bottomSheet = ExpandableCardActivity()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }


        val homeItem: LinearLayout = findViewById(R.id.iconHome)
        val redeemItem: LinearLayout= findViewById(R.id.iconRedeem)
        val gamesItem: LinearLayout= findViewById(R.id.iconGames)

        homeItem.setOnClickListener {
            navigateToActivity(Home::class.java)
        }

        redeemItem.setOnClickListener {
            navigateToActivity(Redeem::class.java)
        }

        gamesItem.setOnClickListener {
            navigateToActivity(Games::class.java)
        }


        val profileButton: ImageButton = findViewById(R.id.btnPerfil)

        profileButton.setOnClickListener {
            navigateToActivity(SettingsActivity::class.java)
        }

    }
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}