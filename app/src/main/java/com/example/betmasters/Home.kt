package com.example.betmasters

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val openSheetButton: CardView = findViewById(R.id.card_filter_fav)

        openSheetButton.setOnClickListener {
            val bottomSheet = ExpandableCardActivity()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }


    }
}