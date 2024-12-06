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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home)



        val openSheetButton: CardView = findViewById(R.id.card_filter_fav)

        openSheetButton.setOnClickListener {
            val bottomSheet = ExpandableCardActivity()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        val homeItem: LinearLayout = findViewById(R.id.iconHome)
        val redeemItem: LinearLayout= findViewById(R.id.iconRedeem)
        val gamesItem: LinearLayout= findViewById(R.id.iconGames)

        redeemItem.setOnClickListener {
            navigateToActivity(Redeem::class.java)
        }

        gamesItem.setOnClickListener {
            navigateToActivity(Games::class.java)
        }


        val profileButton: ImageButton = findViewById(R.id.btnPerfil)

        profileButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("source", "home")
            startActivity(intent)
        }

    }
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}