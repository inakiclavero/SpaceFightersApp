package com.example.gameapp.client.signup

import android.util.Log
import android.util.Patterns
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gameapp.client.APIService
import com.example.gameapp.R
import com.example.gameapp.client.ApiURL
import com.example.gameapp.client.profile.ProfileResult
import com.example.gameapp.client.profile.UserProfile

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupViewModel: ViewModel()  {


    private val _signupForm = MutableLiveData<SignupFormState>()
    val loginFormState: LiveData<SignupFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiURL().appURL)
            .addConverterFactory( GsonConverterFactory.create())
            .build()
    }


    fun signup(user:String,psswrd:String,first_name:String,last_name:String,age:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
            val profile = UserProfile(first_name,last_name,age.toInt())
            Log.d("MENSAJE LOGIN",age.toInt().toString())
            val userReg= UserReg(user,psswrd,profile)
            val gson = Gson()
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonTut: String = gson.toJson( userReg)

            val call = getRetrofit().create(APIService::class.java).signup(userReg)
            Log.d("MENSAJE respuesta",call.body().toString())
            if(call.isSuccessful){
                _signupResult.postValue(SignupResult(success = SignupUserView(displayName = user)))

                Log.d("MENSAJE LOGIN",user)
            }else{
                Log.d("MENSAJE LOGIN","login error")
                _signupResult.postValue(SignupResult(error = R.string.signup_failed))
                Log.d("MENSAJE LOGIN","login error")
            }
            } catch (e: Exception){
                _signupResult.postValue(SignupResult(error = R.string.connection))
                Log.d("MENSAJE LOGIN", "Connection Failed")
            }
        }
    }

    fun signupDataChanged(user:String,psswrd:String,first_name:String,last_name:String,age:String) {
        if (!isUserNameValid(user)) {
            _signupForm.value = SignupFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(psswrd)) {
            _signupForm.value = SignupFormState(passwordError = R.string.invalid_password)
        } else if (!isNameValid(first_name)) {
            _signupForm.value = SignupFormState(first_nameError = R.string.invalid_name)
        } else if (!isNameValid(last_name)) {
            _signupForm.value = SignupFormState(last_nameError = R.string.invalid_name)
        } else if (!isAgeValid(age)) {
            _signupForm.value = SignupFormState(ageError = R.string.invalid_age)
        } else {
            _signupForm.value = SignupFormState(isDataValid = true)
        }
    }

    private fun isGenderValid(gender: String): Boolean {
        return gender == "M" || gender == "F" || gender == "Other"
    }

    private fun isAgeValid(age: String): Boolean{
        if (age.isDigitsOnly() && !age.isNullOrEmpty()) {
            return (age.toInt() in 1..149)
        }else{
            return false
        }

    }

    private fun isNameValid(first_name: String): Boolean {
        val USER_NAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{2,29}$"
        return USER_NAME_REGEX.toRegex().matches(first_name)
    }

    private fun isPhoneValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()

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
