package com.example.gameapp.utils

import com.example.gameapp.client.ApiURL
import java.net.HttpURLConnection
import java.net.URL


class CheckConnection {


    fun isConnectedToServer(): Boolean {
        val timeout = 7000
        try {
            var myUrl = URL(ApiURL().appURL+"api/profile")
            var connection = myUrl.openConnection()
            connection.setConnectTimeout(timeout)
            connection.connect()
            return true;
        } catch (e: Exception) {
            // Handle your exceptions
            return true;
        }
    }


}