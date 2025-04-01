package com.example.betmasters

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class Trilero : AppCompatActivity() {

    private lateinit var mytext: TextView
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView

    private val WINS_KEY = intPreferencesKey("wins")
    private val LOSSES_KEY = intPreferencesKey("losses")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilero)
        mytext = findViewById(R.id.textView)
    }

    private suspend fun updateGameStats(isWin: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                val currentWins = preferences[WINS_KEY] ?: 0
                val currentLosses = preferences[LOSSES_KEY] ?: 0
                if (isWin) {
                    preferences[WINS_KEY] = currentWins + 1
                } else {
                    preferences[LOSSES_KEY] = currentLosses + 1
                }
            }
        }
    }

    fun boton1(view: View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView = findViewById(R.id.imageView)
        val isWin = (randomNumber == 1)

        if (isWin) {
            imageView.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")
        } else {
            imageView.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            updateGameStats(isWin)
        }
    }

    fun boton2(view: View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView2 = findViewById(R.id.imageView2)
        val isWin = (randomNumber == 1)

        if (isWin) {
            imageView2.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")
        } else {
            imageView2.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            updateGameStats(isWin)
        }
    }

    fun boton3(view: View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView3 = findViewById(R.id.imageView3)
        val isWin = (randomNumber == 1)

        if (isWin) {
            imageView3.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")
        } else {
            imageView3.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            updateGameStats(isWin)
        }
    }

    private fun showPopupDialog(message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Pulsa REINICIAR para volver a jugar")
            .setMessage(message)
            .setPositiveButton("REINICIAR (50 coins)") { dialog, _ ->
                dialog.dismiss()
                restartActivity()
            }
            .setNegativeButton("VOLVER") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .create()

        dialog.show()

        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        val params = window?.attributes
        params?.y = 100
        window?.attributes = params
    }

    private fun restartActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}