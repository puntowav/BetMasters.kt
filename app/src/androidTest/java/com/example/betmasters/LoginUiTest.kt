package com.example.betmasters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.betmasters.Registre
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUiTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(Registre::class.java)

    @Test
    fun registreCorrecte() {
        onView(withId(R.id.etUsername)).perform(typeText("Juanito"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanito@email.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())
        onView(withId(R.id.register)).perform(click())

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre inválido"))))
        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre ofensivo"))))
        onView(withId(R.id.etMail)).check(matches(not(hasErrorText("Email inválido"))))
        onView(withId(R.id.etPass)).check(matches(not(hasErrorText("Contraseña insegura"))))
        onView(withId(R.id.etPass2)).check(matches(not(hasErrorText("Las contraseñas no coinciden"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

    @Test
    fun registreNomMoltCurt() {
        onView(withId(R.id.etUsername)).perform(typeText("a"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanito@email.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())

        onView(withId(R.id.register)).perform(click())
        Thread.sleep(500)

        onView(withId(R.id.etUsername)).check(matches(hasErrorText("Nombre inválido")))

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre ofensivo"))))
        onView(withId(R.id.etMail)).check(matches(not(hasErrorText("Email inválido"))))
        onView(withId(R.id.etPass)).check(matches(not(hasErrorText("Contraseña insegura"))))
        onView(withId(R.id.etPass2)).check(matches(not(hasErrorText("Las contraseñas no coinciden"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

    @Test
    fun registreNomOfensiu() {
        onView(withId(R.id.etUsername)).perform(typeText("Puta"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanito@email.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())
        onView(withId(R.id.register)).perform(click())

        onView(withId(R.id.etUsername)).check(matches(hasErrorText("Nombre ofensivo")))

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre inválido"))))
        onView(withId(R.id.etMail)).check(matches(not(hasErrorText("Email inválido"))))
        onView(withId(R.id.etPass)).check(matches(not(hasErrorText("Contraseña insegura"))))
        onView(withId(R.id.etPass2)).check(matches(not(hasErrorText("Las contraseñas no coinciden"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

    @Test
    fun registreMailInvalid() {
        onView(withId(R.id.etUsername)).perform(typeText("Juanito"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanitoemail.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())
        onView(withId(R.id.register)).perform(click())

        onView(withId(R.id.etMail)).check(matches(hasErrorText("Email inválido")))

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre inválido"))))
        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre ofensivo"))))
        onView(withId(R.id.etPass)).check(matches(not(hasErrorText("Contraseña insegura"))))
        onView(withId(R.id.etPass2)).check(matches(not(hasErrorText("Las contraseñas no coinciden"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

    @Test
    fun registrePasswordInsegura() {
        onView(withId(R.id.etUsername)).perform(typeText("Juanito"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanito@email.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Insegura"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Insegura"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())
        onView(withId(R.id.register)).perform(click())

        onView(withId(R.id.etPass)).check(matches(hasErrorText("Contraseña insegura")))

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre inválido"))))
        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre ofensivo"))))
        onView(withId(R.id.etMail)).check(matches(not(hasErrorText("Email inválido"))))
        onView(withId(R.id.etPass2)).check(matches(not(hasErrorText("Las contraseñas no coinciden"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

    @Test
    fun registrePasswordNoCoincideix() {
        onView(withId(R.id.etUsername)).perform(typeText("Juanito"), closeSoftKeyboard())
        onView(withId(R.id.etMail)).perform(typeText("juanito@email.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("Secure2025\$"), closeSoftKeyboard())
        onView(withId(R.id.etPass2)).perform(typeText("Secure2026\$"), closeSoftKeyboard())
        onView(withId(R.id.cbTerms)).perform(click())
        onView(withId(R.id.register)).perform(click())

        onView(withId(R.id.etPass2)).check(matches(hasErrorText("Las contraseñas no coinciden")))

        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre inválido"))))
        onView(withId(R.id.etUsername)).check(matches(not(hasErrorText("Nombre ofensivo"))))
        onView(withId(R.id.etMail)).check(matches(not(hasErrorText("Email inválido"))))
        onView(withId(R.id.etPass)).check(matches(not(hasErrorText("Contraseña insegura"))))

        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }

}