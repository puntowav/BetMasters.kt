package com.example.betmasters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

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

    lateinit var coins: List<Coin>

    var maxMonedas: Int = 5
    var monedasActuales: Int = 5
    var tiempoRecarga: Long = 8 * 1000

    private var currentTimer: CustomCountDownTimer? = null
    private var activeCoin: Coin? = null
    private val pendingCoins = mutableListOf<Coin>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)
        initComponent(view)
        cvTrilero.setOnClickListener {
            gastaMonedas()
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

        coins = listOf(
            Coin(ivCoin1, txtTimer1),
            Coin(ivCoin2, txtTimer2),
            Coin(ivCoin3, txtTimer3),
            Coin(ivCoin4, txtTimer4),
            Coin(ivCoin5, txtTimer5)
        )

        coins.forEach { it.remainingTime = tiempoRecarga }
        coins.forEach { it.timerTextView.visibility = View.INVISIBLE }
    }

    private fun gastaMonedas(){
        if(monedasActuales > 0){
            val moneda = coins.lastOrNull { it.isAvailable }
            moneda?.let{
                it.isAvailable = false
                monedasActuales--
                it.imageView.setImageResource(R.drawable.coin_stamina_gray)
                val newTime = activeCoin?.remainingTime ?: tiempoRecarga
                if(activeCoin != null){
                    activeCoin!!.countDownTimer?.cancel()
                    activeCoin!!.remainingTime = tiempoRecarga
                    pendingCoins.add(activeCoin!!)
                }
                it.remainingTime = newTime
                it.timerTextView.visibility = View.VISIBLE
                it.timerTextView.text = formatTime(it.remainingTime)
                activeCoin = it
                iniciarTemporizador(it)
            }
        }
        else{
            Toast.makeText(context, "No hay monedas disponibles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun iniciarTemporizador(moneda: Coin){
        currentTimer = CustomCountDownTimer(moneda.remainingTime, 1000,
            onTickCallback = { millisUntilFinished ->
                moneda.remainingTime = millisUntilFinished
                moneda.timerTextView.text = formatTime(millisUntilFinished)
            },
            onFinishCallback = {
                moneda.isAvailable = true
                monedasActuales++
                moneda.imageView.setImageResource(R.drawable.coin_stamina)
                moneda.timerTextView.visibility = View.INVISIBLE
                moneda.countDownTimer = null
                activeCoin = null
                currentTimer = null
                if(pendingCoins.isNotEmpty()){
                    val siguienteMoneda = pendingCoins.removeAt(0)
                    activeCoin = siguienteMoneda
                    iniciarTemporizador(siguienteMoneda)
                }
            }
        )
        moneda.countDownTimer = currentTimer
        currentTimer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val segundos = (millisUntilFinished / 1000).toInt()
        val minutos = (segundos / 60) % 60
        val segundosRestantes = segundos % 60
        return String.format("%02d:%02d", minutos, segundosRestantes)
    }
}
