package com.example.betmasters.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val conditions = MutableLiveData<Boolean>()

    private val _loginSuccess = MutableLiveData<FormValidationResult>()
    val loginSuccess: LiveData<FormValidationResult> = _loginSuccess

    fun validateForm(){
        val usernameVal = username.value.orEmpty()
        val emailVal = email.value.orEmpty()
        val passwordVal = password.value.orEmpty()
        val confirmPasswordVal = confirmPassword.value.orEmpty()
        val conditionsVal = conditions.value ?: false

        val result = repository.validateFormDetailed(
            usernameVal, emailVal, passwordVal, confirmPasswordVal, conditionsVal
        )

        _loginSuccess.value = result.copy()
    }
}