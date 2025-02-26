package com.example.betmasters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.ApiRetrofit.LoginAPI
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class MyBetsDialogFragment(private val callBack: DialogCallBack): DialogFragment(),
    BetActionsCallback {

    private lateinit var rvBets: RecyclerView
    private lateinit var betAdapter: BetAdapter
    companion object{
        //TODO: lista donde se hacen los add, de las Bet
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
        betAdapter = BetAdapter(bets, callBack)
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

    override fun onDeleteBet(bet: Bet) {
        lifecycleScope.launch {
            try {
                val response = LoginAPI.API().deleteBet(bet.id)
                if(response.isSuccessful){
                    bets.remove(bet)
                    betAdapter.notifyDataSetChanged()
                }else{
                    Log.e("DeleteBet", "Error: ${response.errorBody()?.string()}")
                }
            }catch (e: Exception){
                Log.e("Error on delete", "Error al eliminar la bet", e)
            }
        }
    }
}
