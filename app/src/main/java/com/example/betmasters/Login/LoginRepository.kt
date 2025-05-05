package com.example.betmasters.Login

import android.util.Patterns

class LoginRepository {
    fun userNameIsValid(name: String): Boolean {
        val regex = Regex("^\\p{L}{4,15}$")
        return name.trim().matches(regex)
    }

    fun userNameIsOfensive(name: String): Boolean{
        val blacklist = listOf(
            "fuck", "shit", "bitch", "nigger", "puta", "mierda", "cabrón", "maricón",
            "zorra", "polla", "coño", "pendejo", "imbécil", "idiota", "retard", "faggot",
            "slut", "cunt", "whore", "motherfucker", "fuk", "sh1t", "b1tch", "n1gga", "m4r1c0n"
        )

        val normalizer = name.lowercase()
            .replace("4", "a")
            .replace("3", "e")
            .replace("0", "o")
            .replace("!", "i")
            .replace("@", "a")

        return blacklist.any { normalizer.contains(it) }
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
        return password.trim().matches(regex)
    }

    fun isEmailValid(email: String): Boolean{
        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return regex.matches(email)
    }

    fun validateFormDetailed(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        acceptedTerms: Boolean
    ): FormValidationResult {
        var isValid = true

        val usernameError = when {
            !userNameIsValid(username) -> {
                isValid = false
                "Nombre inválido"
            }
            userNameIsOfensive(username) -> {
                isValid = false
                "Nombre ofensivo"
            }
            else -> null
        }

        val emailError = when {
            !isEmailValid(email) -> {
                isValid = false
                "Email inválido"
            }
            else -> null
        }

        val passwordError = when {
            !isPasswordValid(password) -> {
                isValid = false
                "Contraseña insegura"
            }
            else -> null
        }

        val confirmPasswordError = when {
            password != confirmPassword -> {
                isValid = false
                "Las contraseñas no coinciden"
            }
            else -> null
        }

        val conditionsError = when {
            !acceptedTerms -> {
                isValid = false
                "Debes aceptar los términos"
            }
            else -> null
        }

        return FormValidationResult(
            usernameError = usernameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
            conditionsError = conditionsError,
            isValid = isValid
        )
    }
}