package com.example.betmasters
import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.ApiRetrofit.LoginAPI
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import okhttp3.internal.notifyAll


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
                GlobalScope.launch(Dispatchers.Main){
                    try {
                        val response = LoginAPI.API().deleteBet(bet.id)
                        if(response.isSuccessful){
                            MyBetsDialogFragment.bets.remove(bet)
                            HomeFragment.coins += (bet.bet.toFloat()* (bet.bet.toFloat()/bet.win.toFloat()))
                            adapter.notifyDataSetChanged()
                            callBack.onCoinsUpdated()
                        }
                    }catch (e: Exception){
                        Log.e("Error delete", "error al usar delete de la bet", e)
                    }finally {
                        dialog.dismiss()
                    }
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
    }
}
