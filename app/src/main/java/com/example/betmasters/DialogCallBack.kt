package com.example.betmasters

interface DialogCallBack {
    fun onCoinsUpdated()
}

interface BetActionsCallback {
    fun onDeleteBet(bet: Bet)
}