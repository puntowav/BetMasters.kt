package com.example.betmasters
import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton


class BetsViewHolder(view: View, private val callBack: DialogCallBack) : RecyclerView.ViewHolder(view){

    private val txtMatch: TextView = view.findViewById(R.id.txtMatch)
    private val txtBet: TextView = view.findViewById(R.id.txtBet)
    private val txtTeam: TextView = view.findViewById(R.id.txtTeam)
    private val txtWin: TextView = view.findViewById(R.id.txtWin)
    private val btnModify: MaterialButton = view.findViewById(R.id.fabModificar)
    private val btnDelete: MaterialButton = view.findViewById(R.id.fabEliminar)

    //TODO: Añadir funcion de modificar
    fun render(bet : Bet, adapter: BetAdapter){
        txtMatch.text = bet.match
        txtBet.text = bet.bet
        txtTeam.text = bet.team
        txtWin.text = bet.win


        btnModify.setOnClickListener {

            Toast.makeText(it.context, "Modificar: ${bet.match}", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener { view ->

            val context = view.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de que deseas eliminar esta apuesta?\n Solo recibiras el 80% de las monedas apostadas.")

            builder.setPositiveButton("Eliminar") { dialog, _ ->
                //TODO: Hacer el delete aquí
                MyBetsDialogFragment.bets.remove(Bet(bet.match, bet.team, bet.bet, bet.win))
                HomeFragment.coins += (bet.bet.toFloat()* (bet.bet.toFloat()/bet.win.toFloat()))
                adapter.notifyDataSetChanged()
                callBack.onCoinsUpdated()
                dialog.dismiss()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
    }
}
