package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView = findViewById<ImageView>(R.id.logoImageView)
        val betMastersImageView = findViewById<ImageView>(R.id.txtBetMasters)

        // Cargar la animación del logo
        val pumpBounceAnimation = AnimationUtils.loadAnimation(this, R.anim.pump_bounce)
        logoImageView.startAnimation(pumpBounceAnimation)

        // Configurar el listener para la animación del logo
        pumpBounceAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                // Iniciar animación fade in para la imagen del texto
                val fadeInAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.fade_in)
                betMastersImageView.visibility = View.VISIBLE
                betMastersImageView.startAnimation(fadeInAnimation)

                // Opcional: Navegar a la siguiente pantalla después del fade in
                fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: android.view.animation.Animation?) {}

                    override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }

                    override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
                })
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}

