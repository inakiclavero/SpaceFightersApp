package com.example.gameapp.client.profile


data class ProfileUserView(val first_name:String,
                           val last_name:String,
                           val age:Int,
                           val score:Int,
                           val time: Float,
                           val best_score: Int,
                           val last_score: Int,
                           val last_time: Float,
                           val games: Int)