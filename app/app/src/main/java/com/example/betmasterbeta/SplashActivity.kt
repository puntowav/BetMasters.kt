package com.example.betmasterbeta

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView = findViewById<ImageView>(R.id.logoImageView)

        // Cargar la animación
        val pumpBounceAnimation = AnimationUtils.loadAnimation(this, R.anim.pump_bounce)
        logoImageView.startAnimation(pumpBounceAnimation)

        // Navegar a la siguiente pantalla después de la animación
        pumpBounceAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}
