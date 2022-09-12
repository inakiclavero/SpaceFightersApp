package com.example.gameapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameapp.R
import com.example.gameapp.databinding.ActivityUpdateProfileBinding

import com.example.gameapp.client.update.updateViewModel
import com.example.gameapp.prefs
import com.example.gameapp.utils.CheckConnection

class UpdateProfileActivity:  AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val updateViewModel = ViewModelProvider(this).get(updateViewModel::class.java)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val first_name = binding.firstName
        val last_name = binding.lastName
        val age = binding.age
        val update = binding.update
        val loading = binding.loading


        binding.imageButtonBack.setOnClickListener{
            val myIntent = Intent(this, ProfileActivity::class.java)
            this.startActivity(myIntent)
        }



        updateViewModel.updateForm.observe(this, Observer {
            val updateState = it ?: return@Observer

            // disable login button unless both username / password is valid
            update.isEnabled = true //signupState.isDataValid


            if (updateState.first_nameError != null) {
                first_name.error = getString(updateState.first_nameError)
            }
            if (updateState.last_nameError != null) {
                last_name.error = getString(updateState.last_nameError)
            }
            if (updateState.ageError != null) {
                age.error = getString(updateState.ageError)
            }

        })

        updateViewModel.updateResult.observe(this, Observer {
            val updateResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (updateResult.error != null) {
                showUpdateFailed(updateResult.error)
            }
            setResult(Activity.RESULT_OK)


            finish()

            val myIntent = Intent(this, ProfileActivity::class.java)
            this.startActivity(myIntent)
        })



        first_name.afterTextChanged {
            updateViewModel.updateDataChanged(
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }

        last_name.afterTextChanged {
            updateViewModel.updateDataChanged(
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }


        age.afterTextChanged {
            updateViewModel.updateDataChanged(
                first_name.text.toString(),
                last_name.text.toString(),
                age.text.toString()
            )
        }





        age.apply {
            afterTextChanged {
                updateViewModel.updateDataChanged(
                    first_name.text.toString(),
                    last_name.text.toString(),
                    age.text.toString()
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        updateViewModel.update(

                            prefs.TockenPref,
                            first_name.text.toString(),
                            last_name.text.toString(),
                            age.text.toString()
                        )
                }
                false
            }




            update.setOnClickListener {

                    loading.visibility = View.VISIBLE
                    updateViewModel.update(
                        prefs.TockenPref,
                        first_name.text.toString(),
                        last_name.text.toString(),
                        age.text.toString()
                    )



            }
        }
    }



    private fun showUpdateFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
