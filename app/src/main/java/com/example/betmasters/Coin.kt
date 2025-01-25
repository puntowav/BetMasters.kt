package com.example.betmasters

import android.widget.ImageView
import android.widget.TextView

data class Coin(
    val imageView: ImageView,
    val timerTextView: TextView,
    var isAvailable: Boolean = true,
    var countDownTimer: CustomCountDownTimer? = null, // Tipo cambiado a CustomCountDownTimer
    var remainingTime: Long = 0 // Atributo para almacenar el tiempo restante
)
