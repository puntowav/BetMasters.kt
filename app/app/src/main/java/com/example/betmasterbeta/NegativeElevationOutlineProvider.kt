package com.example.betmasterbeta

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.cardview.widget.CardView

class NegativeElevationOutlineProvider : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        // Configura el contorno normalmente
        outline.setRect(0, 4, view.width, view.height + 4) // Ajusta para simular sombra inversa
    }
}