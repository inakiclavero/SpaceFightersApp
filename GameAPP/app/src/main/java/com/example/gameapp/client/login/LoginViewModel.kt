package com.example.gameapp.client.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.gameapp.client.APIService
import com.example.gameapp.R
import com.example.gameapp.client.ApiURL
import com.example.gameapp.client.profile.ProfileResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel() : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiURL().appURL)
            .addConverterFactory( GsonConverterFactory.create())
            .build()
    }


    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
            val userCred = UserCred(username,password)
            val gson = Gson()
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonTut: String = gson.toJson( userCred)

            val call = getRetrofit().create(APIService::class.java).login(userCred)

            if(call.isSuccessful){
                val token = call.body()!!.token
                _loginResult.postValue(LoginResult(success = LoggedInUserView(displayName = username,tocken= token)))
                Log.d("MENSAJE LOGIN",token)
            }else{
                _loginResult.postValue(LoginResult(error = R.string.login_failed))
                Log.d("MENSAJE LOGIN",call.body().toString())
            }
            } catch (e: Exception){
                _loginResult.postValue(LoginResult(error = R.string.connection))
                Log.d("MENSAJE LOGIN", "Connection Failed")
            }

        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}