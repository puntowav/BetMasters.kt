package com.example.betmasters

import android.os.CountDownTimer

open class CustomCountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    private val onTickCallback: (millisUntilFinished: Long) -> Unit,
    private val onFinishCallback: () -> Unit
) : CountDownTimer(millisInFuture, countDownInterval) {

    var remainingTime: Long = millisInFuture
        private set

    override fun onTick(millisUntilFinished: Long) {
        remainingTime = millisUntilFinished
        onTickCallback(millisUntilFinished)
    }

    override fun onFinish() {
        remainingTime = 0
        onFinishCallback()
    }
}
