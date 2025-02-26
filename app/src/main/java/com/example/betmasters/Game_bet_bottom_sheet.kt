package com.example.betmasters

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.betmasters.ApiRetrofit.ApiResponse
import com.example.betmasters.ApiRetrofit.LoginAPI
import com.example.betmasters.ApiRetrofit.LoginService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import kotlin.math.round

class ExpandableCardActivity2: BottomSheetDialogFragment() {

    private lateinit var tvTeam1: TextView
    private lateinit var tvTeam2: TextView
    private lateinit var tvLiga: TextView
    private lateinit var tvFecha: TextView
    private lateinit var ivTeam1: ImageView
    private lateinit var ivTeam2: ImageView
    private lateinit var txtWin: TextView
    private lateinit var matchNameString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflar el layout del Bottom Sheet
        return inflater.inflate(R.layout.game_details_bottom_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTeam1 = view.findViewById(R.id.txtTeam1)
        tvTeam2 = view.findViewById(R.id.txtTeam2)
        tvLiga = view.findViewById(R.id.txtLeague)
        tvFecha = view.findViewById(R.id.txtFecha)
        ivTeam1 = view.findViewById(R.id.imageTeam1)
        ivTeam2 = view.findViewById(R.id.imageTeam2)
        val txtMulti1: TextView = view.findViewById(R.id.txtMulti1)
        val txtMulti2: TextView = view.findViewById(R.id.txtMulti2)

        // Obtener los datos del Bundle
        val bundle = arguments
        val team1 = bundle?.getString("team1")
        val team2 = bundle?.getString("team2")
        val liga = bundle?.getString("liga")
        val fecha = bundle?.getString("fecha")
        val team1Image = bundle?.getInt("team1_image") ?: R.drawable.t1
        val team2Image = bundle?.getInt("team2_image") ?: R.drawable.heretics
        val multi1 = bundle?.getString("multi1")
        val multi2 = bundle?.getString("multi2")
        val matchName = bundle?.getString("matchname")

        tvTeam1.text = team1
        tvTeam2.text = team2
        tvLiga.text = liga
        tvFecha.text = fecha
        ivTeam1.setImageResource(team1Image)
        ivTeam2.setImageResource(team2Image)
        txtMulti1.text = multi1
        txtMulti2.text = multi2

        matchNameString = matchName.toString()
        val cvTeam1: CardView = view.findViewById(R.id.cvTeam1)
        val cvTeam2: CardView = view.findViewById(R.id.cvTeam2)
        val cv5: CardView = view.findViewById(R.id.cv5)
        val cv20: CardView = view.findViewById(R.id.cv20)
        val cv50: CardView = view.findViewById(R.id.cv50)
        val cv100: CardView = view.findViewById(R.id.cv100)
        val cv200: CardView = view.findViewById(R.id.cv200)
        val cv500: CardView = view.findViewById(R.id.cv500)
        val editTextCustom: EditText = view.findViewById(R.id.etxtCustom)
        val txtBet: TextView = view.findViewById(R.id.txtBet)
        txtWin = view.findViewById(R.id.txtWin)
        val btnBet : Button = view.findViewById(R.id.btnBet)
        val colorBlanco = ContextCompat.getColor(cvTeam1.context, R.color.white)
        val colorGris = ContextCompat.getColor(cvTeam1.context, R.color.gris)
        val colorBlue = ContextCompat.getColor(cvTeam1.context, R.color.cardBackground)



        cvTeam1.setOnClickListener {
            val currentColor = cvTeam1.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cvTeam1.setCardBackgroundColor(colorGris)
                cvTeam1.cardElevation= 0f
                cvTeam2.setCardBackgroundColor(colorBlanco)
                cvTeam2.cardElevation= 8f
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)
            } else {
                cvTeam1.setCardBackgroundColor(colorBlanco)
                cvTeam1.cardElevation= 8f
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)
            }
        }
        cvTeam2.setOnClickListener {
            val currentColor = cvTeam2.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cvTeam2.setCardBackgroundColor(colorGris)
                cvTeam2.cardElevation= 0f
                cvTeam1.setCardBackgroundColor(colorBlanco)
                cvTeam1.cardElevation= 8f
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)
            } else {
                cvTeam2.setCardBackgroundColor(colorBlanco)
                cvTeam1.cardElevation= 8f
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)
            }
        }
        cv5.setOnClickListener {
            val currentColor = cv5.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv5.setCardBackgroundColor(colorGris)
                cv5.cardElevation= 0f
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "5"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        cv20.setOnClickListener {
            val currentColor = cv20.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv20.setCardBackgroundColor(colorGris)
                cv20.cardElevation= 0f
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "20"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        cv50.setOnClickListener {
            val currentColor = cv50.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv50.setCardBackgroundColor(colorGris)
                cv50.cardElevation= 0f
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "50"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        cv100.setOnClickListener {
            val currentColor = cv100.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv100.setCardBackgroundColor(colorGris)
                cv100.cardElevation= 0f
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "100"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        cv200.setOnClickListener {
            val currentColor = cv200.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv200.setCardBackgroundColor(colorGris)
                cv200.cardElevation= 0f
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "200"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        cv500.setOnClickListener {
            val currentColor = cv500.cardBackgroundColor.defaultColor

            if (currentColor == colorBlanco) {
                cv500.setCardBackgroundColor(colorGris)
                cv500.cardElevation= 0f
                cv20.setCardBackgroundColor(colorBlanco)
                cv20.cardElevation= 8f
                cv50.setCardBackgroundColor(colorBlanco)
                cv50.cardElevation= 8f
                cv100.setCardBackgroundColor(colorBlanco)
                cv100.cardElevation= 8f
                cv200.setCardBackgroundColor(colorBlanco)
                cv200.cardElevation= 8f
                cv5.setCardBackgroundColor(colorBlanco)
                cv5.cardElevation= 8f
                editTextCustom.setText("")
                txtBet.text = "500"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            } else {
                cv500.setCardBackgroundColor(colorBlanco)
                cv500.cardElevation= 8f
                txtBet.text = "0"
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }
        }
        editTextCustom.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtBet.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                updateTxtWin(cvTeam1, cvTeam2, txtMulti1, txtMulti2, txtBet, txtWin, editTextCustom)
                updateBtnBetState(cvTeam1, cvTeam2, txtBet, btnBet)

            }

        })

    }

    private fun updateTxtWin(cvTeam1: CardView, cvTeam2: CardView, txtMulti1: TextView, txtMulti2: TextView, txtBet: TextView, txtWin: TextView, editText: EditText ) {
        val colorGris = ContextCompat.getColor(cvTeam1.context, R.color.gris)
        var bet = txtBet.text.toString().trim().toFloatOrNull() ?: 0f
        var resultado = 0f

        if(editText.text.toString().isNotBlank()){
            bet = editText.text.toString().trim().toFloatOrNull() ?: 0f
        }

        if (cvTeam1.cardBackgroundColor.defaultColor == colorGris) {
            val multi = txtMulti1.text.toString().trim().toFloatOrNull() ?: 0f
            resultado = round(multi * bet)
        }
        else if (cvTeam2.cardBackgroundColor.defaultColor == colorGris) {
            val multi = txtMulti2.text.toString().trim().toFloatOrNull() ?: 0f
            resultado = round(multi * bet)

        }
        val formattedBet = String.format("%.0f", bet)
        val formattedWin = String.format("%.0f", resultado)
        txtBet.text = "$formattedBet"
        txtWin.text = "$formattedWin"

    }
    private fun updateBtnBetState(cvTeam1: CardView, cvTeam2: CardView, txtBet: TextView, btnBet: Button) {
        val colorGris = ContextCompat.getColor(cvTeam1.context, R.color.gris)
        val isTeam1Selected = cvTeam1.cardBackgroundColor.defaultColor == colorGris
        val isTeam2Selected = cvTeam2.cardBackgroundColor.defaultColor == colorGris
        val betAmount = txtBet.text.toString().toFloatOrNull()

        if ((isTeam1Selected || isTeam2Selected) && betAmount != null && betAmount > 0.0) {
            btnBet.backgroundTintList = ContextCompat.getColorStateList(cvTeam1.context, R.color.verdeAgree)
            btnBet.isEnabled = true
            btnBet.setOnClickListener {
                HomeFragment.coins -= txtBet.text.toString().toFloat()
                val mainActivity = activity as? Main
                mainActivity?.updateCoins()
                //TODO: Añadir aqui la instancia de la BET, para hacer el add
                //TODO: No tiene que hacer add si no solo hacer post a la API
               val newBet = if(isTeam1Selected) {
                    Bet(0, matchNameString, tvTeam1.text.toString(), txtBet.text.toString(), txtWin.text.toString())
                }else{
                    Bet(0, matchNameString, tvTeam2.text.toString(), txtBet.text.toString(), txtWin.text.toString())
                }
                //TODO: Añadir aqui el post a la API
                lifecycleScope.launch {
                    try {
                        val createdBet = LoginAPI.API().createBet(newBet).body()
                        if(createdBet != null){
                            MyBetsDialogFragment.bets.add(createdBet)
                        }else {
                            Log.e("CreateBet", "La respuesta fue exitosa, pero el body es null")
                        }
                    }catch (e: Exception){
                        Log.e("Create Bet", "Excepción al usar add pasando una Bet", e)
                    }finally {
                        dismiss()
                    }
                }
            }
        } else {
            btnBet.backgroundTintList = ContextCompat.getColorStateList(cvTeam1.context, R.color.grisBtnBetOff)
            btnBet.isEnabled = false
        }
    }

}