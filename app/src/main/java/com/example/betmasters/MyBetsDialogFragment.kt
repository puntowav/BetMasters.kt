package com.example.betmasters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyBetsDialogFragment (activity: MainActivity): DialogFragment() {

    private lateinit var rvBets: RecyclerView
    private lateinit var betAdapter: BetAdapter
    companion object{
        val bets = mutableListOf<Bet>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_bets_popup_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvBets = view.findViewById(R.id.rvBets)
        betAdapter = BetAdapter(bets, activity = MainActivity())
        rvBets.layoutManager = LinearLayoutManager(requireContext())
        rvBets.adapter = betAdapter
    }

    override fun onStart() {
        super.onStart()
        // Personalizar el tamaño del diálogo
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.8).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // Configurar el fondo del diálogo como transparente
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
