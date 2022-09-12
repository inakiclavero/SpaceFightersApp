package com.example.gameapp.client.profile

data class UserProfile(val first_name:String,
                       val last_name:String,
                       val age:Int,
                       val score:Int = 0,
                       val time: Float = 0f,
                       val best_score:Int=0,
                       val last_score: Int=0,
                       val last_time: Float=0f,
                       val games: Int=0)
