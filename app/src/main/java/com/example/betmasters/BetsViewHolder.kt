package com.example.betmasters
import android.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.ApiRetrofit.LoginAPI
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers



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


        btnModify.setOnClickListener{ view ->

            val context = view.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Modificacion")
            builder.setMessage("¿Estás seguro de que deseas modificar esta apuesta?\n La cantidad que apuestes ahora se sumará a la que ya has apostado.")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.hint = "Monedas a añadir"
            builder.setView(input)

            builder.setPositiveButton("Apostar") { dialog, _ ->
                val amountToAdd = input.text.toString().toFloatOrNull()

                if (amountToAdd == null || amountToAdd <= 0 || amountToAdd > HomeFragment.coins) {
                    Toast.makeText(context, "Ingresa un número válido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val multi = (bet.win).toFloat().div((bet.bet).toFloat())
                val newBetAmount = bet.bet.toFloat() + amountToAdd
                val newWin = newBetAmount * multi
                val newBet = Bet(bet.id, bet.match, bet.team, newBetAmount.toString(), newWin.toString())


                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val response = LoginAPI.API().updateBet(bet.id, newBet)
                        if (response.isSuccessful) {
                            bet.bet = String.format("%.0f", newBetAmount)
                            bet.win = String.format("%.0f", newWin)
                            HomeFragment.coins -= amountToAdd
                            adapter.notifyDataSetChanged()
                            callBack.onCoinsUpdated()
                            Toast.makeText(context, "Apuesta modificada exitosamente", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error al modificar la apuesta", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Log.e("Error modify", "Error al modificar la apuesta", e)
                        Toast.makeText(context, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                    } finally {
                        dialog.dismiss()
                    }
                }
            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()

        }

        btnDelete.setOnClickListener { view ->

            val context = view.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de que deseas eliminar esta apuesta?\n Solo recibiras el 80% de las monedas apostadas.")

            builder.setPositiveButton("Eliminar") { dialog, _ ->

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
