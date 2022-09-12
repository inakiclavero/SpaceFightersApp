package com.example.gameapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.R
import com.example.gameapp.client.signup.SignupUserView
import com.example.gameapp.client.signup.SignupViewModel
import com.example.gameapp.databinding.ActivitySignupBinding
import com.example.gameapp.utils.CheckConnection


class SignupActivity: AppCompatActivity() {


    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var signupViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val first_name = binding.firstName
        val last_name = binding.lastName
        val age = binding.age
        val signup = binding.signup
        val login = binding.loginLink
        val privacy = binding.privacy
        val privacycheck = binding.privacyCheck
        val loading = binding.loading


        login.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            this.startActivity(myIntent)
            finish()
        }

        privacy.setOnClickListener {
            val builder= AlertDialog.Builder(this)
            builder.setMessage(R.string.privacy)
            builder.setCancelable(false);
            builder
                .setNegativeButton(
                    "OK"
                ) { dialog, which ->
                }
            val alertDialog = builder.create()

            // Show the Alert Dialog box

            // Show the Alert Dialog box
            alertDialog.show()

        }




        signupViewModel.loginFormState.observe(this, Observer {
            val signupState = it ?: return@Observer

            // disable login button unless both username / password is valid
            signup.isEnabled = true //signupState.isDataValid

            if (signupState.usernameError != null) {
                username.error = getString(signupState.usernameError)
            }
            if (signupState.passwordError != null) {
                password.error = getString(signupState.passwordError)
            }
            if (signupState.first_nameError != null) {
                first_name.error = getString(signupState.first_nameError)
            }
            if (signupState.last_nameError != null) {
                last_name.error = getString(signupState.last_nameError)
            }
            if (signupState.ageError != null) {
                age.error = getString(signupState.ageError)
            }

        })

        signupViewModel.signupResult.observe(this, Observer {
            val signupResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (signupResult.error != null) {
                showLoginFailed(signupResult.error)
            }
            if (signupResult.success != null) {
                updateUiWithUser(signupResult.success)
            }
            setResult(Activity.RESULT_OK)

            Log.d("//////////////////////////////////////////////////","LOGIN ANCTIVITY")
            val myIntent = Intent(this, LoginActivity::class.java)
            this.startActivity(myIntent)

            finish()
        })

        username.afterTextChanged{
            signupViewModel.signupDataChanged(
                username.text.toString(),
                password.text.toString(),
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }

        password.afterTextChanged{
            signupViewModel.signupDataChanged(
                username.text.toString(),
                password.text.toString(),
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }

        first_name.afterTextChanged{
            signupViewModel.signupDataChanged(
                username.text.toString(),
                password.text.toString(),
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }

        last_name.afterTextChanged{
            signupViewModel.signupDataChanged(
                username.text.toString(),
                password.text.toString(),
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }


        age.afterTextChanged{
            signupViewModel.signupDataChanged(
                username.text.toString(),
                password.text.toString(),
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }


        password.apply {
            afterTextChanged {
                signupViewModel.signupDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    first_name.text.toString(),
                    last_name.text.toString(),
                    age.text.toString()
                )
            }



            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                            signupViewModel.signup(
                            username.text.toString(),
                            password.text.toString(),
                            first_name.text.toString(),
                            last_name.text.toString(),
                            age.text.toString()
                        )
                }
                false
            }


            signup.setOnClickListener {





                    if (privacycheck.isChecked) {
                        loading.visibility = View.VISIBLE
                        signupViewModel.signup(
                            username.text.toString(),
                            password.text.toString(),
                            first_name.text.toString(),
                            last_name.text.toString(),
                            age.text.toString()
                        )
                    } else{
                        Toast.makeText(
                            applicationContext,
                            R.string.privacyerror,
                            Toast.LENGTH_LONG
                        ).show()

                    }






            }
        }
    }

    private fun updateUiWithUser(model: SignupUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}


