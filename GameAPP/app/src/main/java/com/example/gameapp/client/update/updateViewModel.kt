package com.example.gameapp.client.update

import android.util.Log
import android.util.Patterns
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gameapp.R
import com.example.gameapp.client.APIService
import com.example.gameapp.client.ApiURL
import com.example.gameapp.client.profile.UserProfile
import com.example.gameapp.client.signup.SignupResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class updateViewModel:  ViewModel()  {


    private val _updateForm = MutableLiveData<UpdateFormState>()
    val updateForm: LiveData<UpdateFormState> = _updateForm

    private val _updateResult = MutableLiveData<UpdateResult>()
    val updateResult: LiveData<UpdateResult> = _updateResult


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiURL().appURL)
            .addConverterFactory( GsonConverterFactory.create())
            .build()
    }


    fun update(token:String,first_name:String,last_name:String,age:String): MutableLiveData<UpdateResult> {
        CoroutineScope(Dispatchers.IO).launch {
            try{
            val profile = UserUpdateProfile(first_name,last_name,age.toInt())
            Log.d("MENSAJE UPDATE",age.toInt().toString())

            val gson = Gson()
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonTut: String = gson.toJson( profile)

            val call = getRetrofit().create(APIService::class.java).update("Bearer $token", profile)


            if(call.isSuccessful){

                var gson = Gson()
                //var profile = gson.fromJson(call.body()?.data?.get(0).get(), UserProfile::class.java)

                _updateResult.postValue(UpdateResult(success = call.body()?.data?.get(0)?.let {
                    UserProfile(
                        first_name = it.first_name, last_name=it.last_name,
                         age = it.age, score = it.score, time = it.time, last_score = it.last_score,
                        last_time = it.last_time, games = it.games)
                }))



            }else{
                _updateResult.postValue(UpdateResult(error = R.string.profile_failed))
                Log.d("MENSAJE UPDATE", call.body()?.data.toString())
            }
            } catch (e: Exception){
                _updateResult.postValue(UpdateResult(error = R.string.connection))
                Log.d("MENSAJE LOGIN", "Connection Failed")
            }
        }

        return _updateResult
    }

    fun updateDataChanged(first_name:String,last_name:String,age:String) {
        if (!isNameValid(first_name)) {
            _updateForm.value = UpdateFormState(first_nameError = R.string.invalid_name)
        } else if (!isNameValid(last_name)) {
            _updateForm.value = UpdateFormState(last_nameError = R.string.invalid_name)
        } else if (!isAgeValid(age)) {
            _updateForm.value = UpdateFormState(ageError = R.string.invalid_age)
        } else {
            _updateForm.value = UpdateFormState(isDataValid = true)
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


}
