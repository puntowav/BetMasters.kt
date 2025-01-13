package com.example.betmasters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExpandableCardActivity : BottomSheetDialogFragment() {

    private var isExpandedLEC = false
    private var isExpandedLCK = false
    private lateinit var sharedPreferences: SharedPreferences
    private val items = arrayOf(
        "League of Legends",
        "Valorant",
        "Counter Strike"
    )
    var adapterItems: ArrayAdapter<String>? = null

    // Claves para los iconos en SharedPreferences
    private val icon1KeyLEC = "Icon1Visible"
    private val icon2KeyLEC = "Icon2Visible"
    private val icon3KeyLEC = "Icon3Visible"
    private val icon4KeyLEC = "Icon4Visible"
    private val icon5KeyLEC = "Icon5Visible"
    private val icon6KeyLEC = "Icon6Visible"

    private val icon1KeyLCK = "Icon1Visible"
    private val icon2KeyLCK = "Icon2Visible"
    private val icon3KeyLCK = "Icon3Visible"
    private val icon4KeyLCK = "Icon4Visible"
    private val icon5KeyLCK = "Icon5Visible"
    private val icon6KeyLCK = "Icon6Visible"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflar el layout del Bottom Sheet
        return inflater.inflate(R.layout.fav_sheet_bottom_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        isCancelable = false
        val bottomSheetDialog = dialog as BottomSheetDialog
        // Ajustar la opacidad del fondo
        bottomSheetDialog?.window?.setDimAmount(0.7f)  // Valor entre 0 y 1 (1 es completamente oscuro)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeFragment.loadFavorites(requireContext())

        sharedPreferences = requireActivity().getSharedPreferences("IconVisibilityPrefs", MODE_PRIVATE)

        val lecLayout: LinearLayout = view.findViewById(R.id.lecLayout)
        val lckLayout: LinearLayout = view.findViewById(R.id.lckLayout)
        val lcsLayout: LinearLayout = view.findViewById(R.id.lcsLayout)
        val lplLayout: LinearLayout = view.findViewById(R.id.lplLayout)
        val americasLayout: LinearLayout = view.findViewById(R.id.americasLayout)
        val emeaLayout: LinearLayout = view.findViewById(R.id.emeaLayout)
        val pacificLayout: LinearLayout = view.findViewById(R.id.pacificLayout)
        val eslLayout: LinearLayout = view.findViewById(R.id.eslLayout)
        val expandableCard: CardView = view.findViewById(R.id.expandableCard)
        val expandableCardLCK: CardView = view.findViewById(R.id.expandableCardLCK)
        val arrowIcon: ImageView = view.findViewById(R.id.arrowIcon)
        val autoCompleteTextEsport: AutoCompleteTextView = view.findViewById(R.id.auto_complete_txt)
        val btnApply: Button = view.findViewById(R.id.btnApply)

        val imgTeam1: ImageView = view.findViewById(R.id.imageTeam1)
        val ovTeam1: ImageView = view.findViewById(R.id.overlayTeam1)
        val imgTeam2: ImageView = view.findViewById(R.id.imageTeam2)
        val ovTeam2: ImageView = view.findViewById(R.id.overlayTeam2)
        val imgTeam3: ImageView = view.findViewById(R.id.imageTeam3)
        val ovTeam3: ImageView = view.findViewById(R.id.overlayTeam3)
        val imgTeam4: ImageView = view.findViewById(R.id.imageTeam4)
        val ovTeam4: ImageView = view.findViewById(R.id.overlayTeam4)
        val imgTeam5: ImageView = view.findViewById(R.id.imageTeam5)
        val ovTeam5: ImageView = view.findViewById(R.id.overlayTeam5)
        val imgTeam6: ImageView = view.findViewById(R.id.imageTeam6)
        val ovTeam6: ImageView = view.findViewById(R.id.overlayTeam6)
        val txtTeam1: TextView = view.findViewById(R.id.txtTeam1)
        val txtTeam2: TextView = view.findViewById(R.id.txtTeam2)
        val txtTeam3: TextView = view.findViewById(R.id.txtTeam3)
        val txtTeam4: TextView = view.findViewById(R.id.txtTeam4)
        val txtTeam5: TextView = view.findViewById(R.id.txtTeam5)
        val txtTeam6: TextView = view.findViewById(R.id.txtTeam6)

        // Recuperar el estado de los iconos desde SharedPreferences
        restoreIconVisibility(imgTeam1, ovTeam1, icon1KeyLEC, txtTeam1.text.toString())
        restoreIconVisibility(imgTeam2, ovTeam2, icon2KeyLEC, txtTeam2.text.toString())
        restoreIconVisibility(imgTeam3, ovTeam3, icon3KeyLEC, txtTeam3.text.toString())
        restoreIconVisibility(imgTeam4, ovTeam4, icon4KeyLEC, txtTeam4.text.toString())
        restoreIconVisibility(imgTeam5, ovTeam5, icon5KeyLEC, txtTeam5.text.toString())
        restoreIconVisibility(imgTeam6, ovTeam6, icon6KeyLEC, txtTeam6.text.toString())

        adapterItems = ArrayAdapter<String>(requireContext(), R.layout.lista_desplegable, items)


        autoCompleteTextEsport.setAdapter<ArrayAdapter<String>>(adapterItems)

        autoCompleteTextEsport.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()

            when (item) {
                "League of Legends" -> {
                    lecLayout.visibility = View.VISIBLE
                    lckLayout.visibility = View.VISIBLE
                    lcsLayout.visibility = View.VISIBLE
                    lplLayout.visibility = View.VISIBLE

                    americasLayout.visibility = View.GONE
                    emeaLayout.visibility = View.GONE
                    pacificLayout.visibility = View.GONE
                    eslLayout.visibility = View.GONE
                }
                "Valorant" -> {
                    americasLayout.visibility = View.VISIBLE
                    emeaLayout.visibility = View.VISIBLE
                    pacificLayout.visibility = View.VISIBLE

                    lecLayout.visibility = View.GONE
                    lckLayout.visibility = View.GONE
                    lcsLayout.visibility = View.GONE
                    lplLayout.visibility = View.GONE
                    eslLayout.visibility = View.GONE
                }
                "Counter Strike" -> {
                    eslLayout.visibility = View.VISIBLE

                    lecLayout.visibility = View.GONE
                    lckLayout.visibility = View.GONE
                    lcsLayout.visibility = View.GONE
                    lplLayout.visibility = View.GONE
                    americasLayout.visibility = View.GONE
                    emeaLayout.visibility = View.GONE
                    pacificLayout.visibility = View.GONE
                }

            }
        }

        btnApply.setOnClickListener {
            dismiss()
        }

        lecLayout.setOnClickListener {
            if (isExpandedLEC) {
                collapse(expandableCard)
                arrowIcon.setImageResource(R.drawable.arrow_contraer) // Cambia el ícono
            } else {
                expand(expandableCard)
                arrowIcon.setImageResource(R.drawable.arrow_expandir) // Cambia el ícono
            }
            isExpandedLEC = !isExpandedLEC
        }

        lckLayout.setOnClickListener {
            if (isExpandedLCK) {
                collapse(expandableCardLCK)
                arrowIcon.setImageResource(R.drawable.arrow_contraer) // Cambia el ícono
            } else {
                expand(expandableCardLCK)
                arrowIcon.setImageResource(R.drawable.arrow_expandir) // Cambia el ícono
            }
            isExpandedLCK = !isExpandedLCK
        }

        // Configurar los listeners para los iconos
        setupIconClickListener(imgTeam1, ovTeam1, txtTeam1, icon1KeyLEC)
        setupIconClickListener(imgTeam2, ovTeam2, txtTeam2, icon2KeyLEC)
        setupIconClickListener(imgTeam3, ovTeam3, txtTeam3, icon3KeyLEC)
        setupIconClickListener(imgTeam4, ovTeam4, txtTeam4, icon4KeyLEC)
        setupIconClickListener(imgTeam5, ovTeam5, txtTeam5, icon5KeyLEC)
        setupIconClickListener(imgTeam6, ovTeam6, txtTeam6, icon6KeyLEC)
    }

    private fun restoreIconVisibility(imgTeam: ImageView, ovTeam: ImageView, iconKey: String, teamName: String) {
        // Recuperar el estado guardado en SharedPreferences
        val isVisible = sharedPreferences.getBoolean(iconKey, false)
        if (HomeFragment.favoritos.contains(teamName)) {
            ovTeam.visibility = View.VISIBLE
        } else {
            ovTeam.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private fun setupIconClickListener(imgTeam: ImageView, ovTeam: ImageView, txtTeam: TextView, iconKey: String) {
        imgTeam.setOnClickListener {
            if (ovTeam.isGone) {
                ovTeam.visibility = View.VISIBLE
                favAnimation(ovTeam)
                HomeFragment.favoritos.add(txtTeam.text.toString())
                HomeFragment.saveFavorites(requireContext())
                sharedPreferences.edit().putBoolean(iconKey, true).apply() // Guardar el estado
            } else {
                ovTeam.visibility = View.GONE
                HomeFragment.favoritos.remove(txtTeam.text.toString())
                HomeFragment.saveFavorites(requireContext())
                sharedPreferences.edit().putBoolean(iconKey, false).apply() // Guardar el estado
            }
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

    private fun favAnimation(ovTeam: ImageView) {
        // Animación de escala (efecto de pop/rebote)
        val scaleX = ObjectAnimator.ofFloat(ovTeam, View.SCALE_X, 0.5f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(ovTeam, View.SCALE_Y, 0.5f, 1.2f, 1f)

        // Animación de desvanecimiento
        val fadeIn = ObjectAnimator.ofFloat(ovTeam, View.ALPHA, 0f, 1f)

        // Combinar animaciones en un set
        AnimatorSet().apply {
            playTogether(scaleX, scaleY, fadeIn)
            duration = 500 // Duración en milisegundos
            start()
        }
    }
}

