package com.example.gameapp.client.update

data class Stats(
    val score:Int = 0,
    val time: Float = 0f,
    val last_score: Int=0,
    val last_time: Float=0f,
    val games: Int=0
)
