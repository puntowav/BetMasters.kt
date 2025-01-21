package com.example.betmasters

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import kotlin.math.min


class GamesFragment : Fragment() {

    lateinit var txtTimer1: TextView
    lateinit var txtTimer2: TextView
    lateinit var txtTimer3: TextView
    lateinit var txtTimer4: TextView
    lateinit var txtTimer5: TextView
    lateinit var ivCoin1: ImageView
    lateinit var ivCoin2: ImageView
    lateinit var ivCoin3: ImageView
    lateinit var ivCoin4: ImageView
    lateinit var ivCoin5: ImageView
    lateinit var cvTrilero: CardView

    var maxMonedas: Int = 5 // Número máximo de monedas
    var monedasActuales: Int = 5 // Monedas disponibles al inicio
    var tiempoRecarga: Long = (8 * 60 * 60 * 100).toLong() // 8 horas en milisegundos




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)
        initComponent(view)

        var sharedPreferences = requireContext().getSharedPreferences("staminaPrefs", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var ultimoUso: Long = sharedPreferences.getLong("ultimoUso", 0)
        var tiempoActual: Long = System.currentTimeMillis()

        if (ultimoUso > 0) {
            val tiempoPasado = tiempoActual - ultimoUso
            val monedasRecargadas = (tiempoPasado / tiempoRecarga).toInt()
            monedasActuales =
                min(maxMonedas.toDouble(), (monedasActuales + monedasRecargadas).toDouble()).toInt()

            // Actualiza el tiempo del último uso si aún hay monedas que recargar
            if (monedasActuales < maxMonedas) {
                val tiempoRestante = tiempoRecarga - (tiempoPasado % tiempoRecarga)
                editor.putLong("tiempoRestante", tiempoRestante)
            }
        }

        val tiempoRestante = sharedPreferences.getLong("tiempoRestante", tiempoRecarga)

        object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val horas = (millisUntilFinished / (1000 * 60 * 60)).toInt()
                val minutos = ((millisUntilFinished / (1000 * 60)) % 60).toInt()
                val segundos = ((millisUntilFinished / 1000) % 60).toInt()

                val tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos)
                txtTimer5.setText(tiempo) // Actualiza un TextView con el tiempo restante
            }

            override fun onFinish() {
            }

        }.start()

        cvTrilero.setOnClickListener {
            // abre un fragment
        }


        return view
    }

    private fun initComponent(view: View) {
        txtTimer1 = view.findViewById(R.id.txtTimer1)
        txtTimer2 = view.findViewById(R.id.txtTimer2)
        txtTimer3 = view.findViewById(R.id.txtTimer3)
        txtTimer4 = view.findViewById(R.id.txtTimer4)
        txtTimer5 = view.findViewById(R.id.txtTimer5)
        ivCoin1 = view.findViewById(R.id.ivCoin1)
        ivCoin2 = view.findViewById(R.id.ivCoin2)
        ivCoin3 = view.findViewById(R.id.ivCoin3)
        ivCoin4 = view.findViewById(R.id.ivCoin4)
        ivCoin5 = view.findViewById(R.id.ivCoin5)
        cvTrilero = view.findViewById(R.id.cvGameTrilero)
    }

}