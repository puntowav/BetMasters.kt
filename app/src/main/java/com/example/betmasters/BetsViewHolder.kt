package com.example.betmasters
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton


class BetsViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txtMatch: TextView = view.findViewById(R.id.txtMatch)
    private val txtBet: TextView = view.findViewById(R.id.txtBet)
    private val txtTeam: TextView = view.findViewById(R.id.txtTeam)
    private val txtWin: TextView = view.findViewById(R.id.txtWin)
    private val btnModify: MaterialButton = view.findViewById(R.id.fabModificar)
    private val btnDelete: MaterialButton = view.findViewById(R.id.fabEliminar)


    fun render(bet : Bet, adapter: BetAdapter){
        txtMatch.text = bet.match
        txtBet.text = bet.bet
        txtTeam.text = bet.team
        txtWin.text = bet.win

        // Acciones de los botones (si es necesario)
        btnModify.setOnClickListener {
            // Lógica para modificar la apuesta
            Toast.makeText(it.context, "Modificar: ${bet.match}", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener {
            MyBetsDialogFragment.bets.remove(Bet(bet.match, bet.team, bet.bet, bet.win))
            HomeFragment.coins += bet.bet.toFloat()
            adapter.notifyDataSetChanged()
        }
    }
}
