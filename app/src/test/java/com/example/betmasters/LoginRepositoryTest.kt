package com.example.betmasters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.betmasters.Login.LoginRepository
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginRepositoryTest {
    private val repo = LoginRepository()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //Test nom user
    @Test
    fun nomUserValid(){
        assertTrue(repo.userNameIsValid("Juan"))
    }

    @Test
    fun nomEsOfensiu(){
        assertTrue(repo.userNameIsOfensive("puta"))
    }

    @Test
    fun nomMoltCurt(){
        assertFalse(repo.userNameIsValid("a"))
    }

    @Test
    fun nomMoltLlarg(){
        assertFalse(repo.userNameIsValid("JuanSanchezBenitez"))
    }
    @Test
    fun nomConEspacios() {
        assertFalse(repo.userNameIsValid("Ana Maria"))
    }

    @Test
    fun nomConNumeros() {
        assertFalse(repo.userNameIsValid("Juan123"))
    }

    @Test
    fun nomConSimbolos() {
        assertFalse(repo.userNameIsValid("Pepe!"))
    }

    @Test
    fun nomConAcentosValido() {
        assertTrue(repo.userNameIsValid("José"))
    }

    @Test
    fun nomLongitudMinima() {
        // 4 caracteres
        assertTrue(repo.userNameIsValid("Lina"))
    }

    @Test
    fun nomLongitudMaxima() {
        // 15 caracteres
        assertTrue(repo.userNameIsValid("JoseGarciaLopez"))
    }

    //Test email
    @Test
    fun emailEsValid(){
        assertTrue(repo.isEmailValid("example@gmail.com"))
    }

    @Test
    fun emailNoEsValidFaltaArroba(){
        assertFalse(repo.isEmailValid("examplegmail.com"))
    }

    @Test
    fun emailNoEsValidFaltaPuntCom(){
        assertFalse(repo.isEmailValid("example@gmail"))
    }

    //Test password
    @Test
    fun passwordValid(){
        assertTrue(repo.isPasswordValid("Secure2025\$"))
    }
    @Test
    fun passwordMoltCurta(){
        assertFalse(repo.isPasswordValid("Ab1!"))
    }
    @Test
    fun passwordNoCaracterEspecial(){
        assertFalse(repo.isPasswordValid("Abcdefg1"))
    }
    @Test
    fun passwordNoNumeros(){
        assertFalse(repo.isPasswordValid("Abcdefg!"))
    }
    @Test
    fun passwordNoMayus(){
        assertFalse(repo.isPasswordValid("abcdef1!"))
    }
    @Test
    fun passwordNoMinus(){
        assertFalse(repo.isPasswordValid("ABCDEF1!"))
    }

    //Test from
    @Test
    fun formulariValid(){
        val result = repo.validateFormDetailed("juan", "test@email.com", "Secure2025\$", "Secure2025\$", true)
        assertTrue(result.isValid)
        assertNull(result.usernameError)
        assertNull(result.emailError)
        assertNull(result.passwordError)
        assertNull(result.confirmPasswordError)
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariNomInvalid(){
        val result = repo.validateFormDetailed("a", "test@email.com", "Secure2025\$", "Secure2025\$", true)
        assertFalse(result.isValid)
        assertNotNull(result.usernameError)
        assertEquals("Error per nom molt curt", result.usernameError, "Nombre inválido")
        assertNull(result.emailError)
        assertNull(result.passwordError)
        assertNull(result.confirmPasswordError)
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariNomOfensiu(){
        val result = repo.validateFormDetailed("puta", "test@email.com", "Secure2025\$", "Secure2025\$", true)
        assertFalse(result.isValid)
        assertNotNull(result.usernameError)
        assertEquals("Error per no ofensiu", result.usernameError, "Nombre ofensivo")
        assertNull(result.emailError)
        assertNull(result.passwordError)
        assertNull(result.confirmPasswordError)
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariMailNoValid(){
        val result = repo.validateFormDetailed("juan", "testemail.com", "Secure2025\$", "Secure2025\$", true)
        assertFalse(result.isValid)
        assertNull(result.usernameError)
        assertNotNull(result.emailError)
        assertEquals("Error per no valid", result.emailError, "Email inválido")
        assertNull(result.passwordError)
        assertNull(result.confirmPasswordError)
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariPasswordNoValida(){
        val result = repo.validateFormDetailed("juan", "test@email.com", "Abcdef12", "Abcdef12", true)
        assertFalse(result.isValid)
        assertNull(result.usernameError)
        assertNull(result.emailError)
        assertNotNull(result.passwordError)
        assertEquals("Error per password no valida", result.passwordError, "Contraseña insegura")
        assertNull(result.confirmPasswordError)
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariPasswordNoCoincident(){
        val result = repo.validateFormDetailed("juan", "test@email.com", "Secure2025\$", "Altre12!", true)
        assertFalse(result.isValid)
        assertNull(result.usernameError)
        assertNull(result.emailError)
        assertNull(result.passwordError)
        assertNotNull(result.confirmPasswordError)
        assertEquals("Error per password no coincident", result.confirmPasswordError, "Las contraseñas no coinciden")
        assertNull(result.conditionsError)
    }
    @Test
    fun formulariCondicionsNoAcceptades(){
        val result = repo.validateFormDetailed("juan", "test@email.com", "Secure2025\$", "Secure2025\$", false)
        assertFalse(result.isValid)
        assertNull(result.usernameError)
        assertNull(result.emailError)
        assertNull(result.passwordError)
        assertNull(result.confirmPasswordError)
        assertNotNull(result.conditionsError)
        assertEquals("Error condicions no acceptades", result.conditionsError, "Debes aceptar los términos")
    }
}