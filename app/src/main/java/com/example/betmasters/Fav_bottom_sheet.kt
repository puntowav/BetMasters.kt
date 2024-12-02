package com.example.betmasters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExpandableCardActivity : BottomSheetDialogFragment() {

    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del Bottom Sheet
        return inflater.inflate(R.layout.fav_sheet_bottom_layout, container, false)
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        // Ajustar la opacidad del fondo
        bottomSheetDialog?.window?.setDimAmount(0.7f)  // Valor entre 0 y 1 (1 es completamente oscuro)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referenciar vistas del layout
        val headerLayout: LinearLayout = view.findViewById(R.id.headerLayout)
        val expandableCard: CardView = view.findViewById(R.id.expandableCard)
        val arrowIcon: ImageView = view.findViewById(R.id.arrowIcon)

        headerLayout.setOnClickListener {
            if (isExpanded) {
                collapse(expandableCard)
                arrowIcon.setImageResource(R.drawable.arrow_contraer) // Cambia el ícono
            } else {
                expand(expandableCard)
                arrowIcon.setImageResource(R.drawable.arrow_expandir) // Cambia el ícono
            }
            isExpanded = !isExpanded
        }
    }

    // Método para expandir
    private fun expand(view: View) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 0
        view.visibility = View.VISIBLE

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f) LinearLayout.LayoutParams.WRAP_CONTENT
                    else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }
        animation.duration = 300
        view.startAnimation(animation)
    }

    // Método para colapsar
    private fun collapse(view: View) {
        val initialHeight = view.measuredHeight

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }
        }
        animation.duration = 300
        view.startAnimation(animation)
    }
}

