package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class Trilero : AppCompatActivity() {

    private lateinit var mytext: TextView
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilero)

        mytext = findViewById(R.id.textView)
    }

    fun boton1(view: android.view.View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView = findViewById(R.id.imageView)

        if (randomNumber == 1) {
            imageView.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")


        } else {
            imageView.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
        }
    }

    fun boton2(view: android.view.View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView2 = findViewById(R.id.imageView2)

        if (randomNumber == 1) {
            imageView2.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")


        } else {
            imageView2.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
        }
    }

    fun boton3(view: android.view.View) {
        val randomNumber = Random().nextInt(3) + 1
        imageView3 = findViewById(R.id.imageView3)

        if (randomNumber == 1) {
            imageView3.setImageResource(R.drawable.baso_bola)
            showPopupDialog("CORRECTO!!!")


        } else {
            imageView3.setImageResource(R.drawable.baso_levantado_vacio)
            showPopupDialog("ERROR!!!")
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
                val intent = Intent(this, GamesFragment::class.java)
                startActivity(intent)
            }
            .create()

        dialog.show()

        // Modificar la posición del diálogo
        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)  // Moverlo hacia abajo
        val params = window?.attributes
        params?.y = 100  // Ajustar distancia desde la parte inferior (ajústalo según necesidad)
        window?.attributes = params
    }


    private fun restartActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}
