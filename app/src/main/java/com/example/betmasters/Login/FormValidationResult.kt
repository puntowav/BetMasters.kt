package com.example.betmasters.Login

data class FormValidationResult(
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val conditionsError: String? = null,
    val isValid: Boolean = false
)