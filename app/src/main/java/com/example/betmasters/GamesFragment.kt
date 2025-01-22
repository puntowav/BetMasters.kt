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
import android.widget.Toast
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
    var tiempoRecarga: Long = (1000 * 10).toLong() // 10 seg en milisegundos

    // Variables globales para manejar los temporizadores y estados
    // Inicializar con 5 elementos para las monedas
    private var temporizadores = MutableList<CountDownTimer?>(5) { null } // Inicialmente todos son null
    private var tiemposRestantes = MutableList<Long>(5) { 10000L } // Todos empiezan con 10 segundos
    private var temporizadorActivo = -1 // Indica cuál temporizador está activo (-1 significa ninguno)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)
        initComponent(view)

        var sharedPreferences = requireContext().getSharedPreferences("staminaPrefs", Context.MODE_PRIVATE)
        monedasActuales = sharedPreferences.getInt("monedasActuales", 5)
        tiemposRestantes = MutableList(5) { i ->
            sharedPreferences.getLong("tiempoRestante_$i", 10000L)
        }
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

        /*object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val horas = (millisUntilFinished / (1000 * 60 * 60)).toInt()
                val minutos = ((millisUntilFinished / (1000 * 60)) % 60).toInt()
                val segundos = ((millisUntilFinished / 1000) % 60).toInt()

                //val tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos)
                val tiempo = String.format("%02d:%02d", minutos, segundos)
                txtTimer5.setText(tiempo) // Actualiza un TextView con el tiempo restante
            }

            override fun onFinish() {
            }

        }.start()*/

        /*cvTrilero.setOnClickListener {
            if (monedasActuales > 0) {
                when (monedasActuales) {
                    5 -> {
                        ivCoin5.setImageResource(R.drawable.coin_stamina_gray)
                        txtTimer5.visibility = View.VISIBLE
                        iniciarContador(txtTimer5, 5)
                    }
                    4 -> {
                        ivCoin4.setImageResource(R.drawable.coin_stamina_gray)
                        txtTimer4.visibility = View.VISIBLE
                        iniciarContador(txtTimer4, 4)
                    }
                    3 -> {
                        ivCoin3.setImageResource(R.drawable.coin_stamina_gray)
                        txtTimer3.visibility = View.VISIBLE
                        iniciarContador(txtTimer3, 3)
                    }
                    2 -> {
                        ivCoin2.setImageResource(R.drawable.coin_stamina_gray)
                        txtTimer2.visibility = View.VISIBLE
                        iniciarContador(txtTimer2, 2)
                    }
                    1 -> {
                        ivCoin1.setImageResource(R.drawable.coin_stamina_gray)
                        txtTimer1.visibility = View.VISIBLE
                        iniciarContador(txtTimer1, 1)
                    }
                }
                monedasActuales -= 1
                guardarEstado() // Guardar monedas y último uso en SharedPreferences
            } else {
                Toast.makeText(context, "No tienes más monedas disponibles", Toast.LENGTH_SHORT).show()
            }
        }*/
        cvTrilero.setOnClickListener {
            if (monedasActuales > 0) {
                for (i in 4 downTo 0) { // Iteramos de la última moneda activa hacia la primera
                    if (tiemposRestantes[i] == 10000L && (temporizadorActivo == -1 || i < temporizadorActivo)) {
                        activarTemporizador(i)
                        break
                    }
                }
            } else {
                Toast.makeText(context, "No tienes más monedas disponibles", Toast.LENGTH_SHORT).show()
            }
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


    private fun guardarEstado() {
        val sharedPreferences = requireContext().getSharedPreferences("staminaPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("monedasActuales", monedasActuales)
        editor.putLong("ultimoUso", System.currentTimeMillis())
        editor.apply()
    }

    private fun activarTemporizador(posicion: Int) {
        if (posicion !in 0..4) return // Verifica que la posición sea válida

        if (temporizadorActivo != -1) {
            // Si hay un temporizador activo, pausarlo y reiniciarlo con 10 segundos
            detenerTemporizador(temporizadorActivo)
            tiemposRestantes[temporizadorActivo] = 10000L
        }

        temporizadorActivo = posicion // Establecemos cuál es el temporizador activo
        iniciarContador(posicion) // Iniciamos el temporizador seleccionado
    }



    private fun iniciarContador(posicion: Int) {
        if (posicion !in 0..4) return // Verifica que la posición sea válida

        val txtTimer = when (posicion) {
            0 -> txtTimer1
            1 -> txtTimer2
            2 -> txtTimer3
            3 -> txtTimer4
            4 -> txtTimer5
            else -> return
        }
        val ivCoin = when (posicion) {
            0 -> ivCoin1
            1 -> ivCoin2
            2 -> ivCoin3
            3 -> ivCoin4
            4 -> ivCoin5
            else -> return
        }

        txtTimer.visibility = View.VISIBLE
        ivCoin.setImageResource(R.drawable.coin_stamina_gray)

        temporizadores[posicion]?.cancel() // Cancela cualquier temporizador existente en esta posición

        temporizadores[posicion] = object : CountDownTimer(tiemposRestantes[posicion], 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tiemposRestantes[posicion] = millisUntilFinished
                val minutos = (millisUntilFinished / (1000 * 60)) % 60
                val segundos = (millisUntilFinished / 1000) % 60
                txtTimer.text = String.format("%02d:%02d", minutos, segundos)
            }

            override fun onFinish() {
                tiemposRestantes[posicion] = 10000L // Reiniciamos el tiempo del temporizador terminado
                txtTimer.visibility = View.GONE
                ivCoin.setImageResource(R.drawable.coin_stamina)

                monedasActuales += 1
                guardarEstado() // Guardar monedas en SharedPreferences

                // Reactiva el siguiente temporizador pausado, si existe
                for (i in 1..4) { // Recorremos de menor a mayor prioridad
                    if (tiemposRestantes[i] in 1..9999L) {
                        // Si el temporizador está pausado (null) y tiene tiempo restante válido
                        activarTemporizador(i) // Reactivamos el temporizador pausado
                        return
                    }
                }

                // Si no hay temporizadores pausados, no hay temporizador activo
                temporizadorActivo = -1
            }
        }.start()
    }



    private fun detenerTemporizador(posicion: Int) {
        temporizadores[posicion]?.cancel()
        temporizadores[posicion] = null
    }





}