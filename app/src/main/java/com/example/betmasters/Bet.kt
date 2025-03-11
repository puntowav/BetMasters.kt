package com.example.betmasters

import com.google.gson.annotations.SerializedName

data class Bet (
    val id: Int = 0,
    @SerializedName("partido") val match: String,
    val team: String,
    var bet: String,
    var win: String) {
}
