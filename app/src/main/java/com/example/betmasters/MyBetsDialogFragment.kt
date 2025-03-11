package com.example.betmasters

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.ApiRetrofit.LoginAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

        // Configura el ItemTouchHelper para Swipe-to-Delete
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            private val backgroundPaint = Paint().apply { color = Color.RED }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val bet = bets.get(position)

                val context = view.context
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Confirmación")
                builder.setMessage("¿Estás seguro de que deseas eliminar esta apuesta?\n Solo recibiras el 80% de las monedas apostadas.")

                builder.setPositiveButton("Eliminar") { dialog, _ ->
                    lifecycleScope.launch {
                        try {
                            val response = LoginAPI.API().deleteBet(bet.id)
                            if(response.isSuccessful){
                                bets.removeAt(position);
                                HomeFragment.coins += (bet.bet.toFloat()* (bet.bet.toFloat()/bet.win.toFloat()))
                                betAdapter.notifyDataSetChanged()
                                callBack.onCoinsUpdated()
                            }
                        }catch (e: Exception){
                            Log.e("Error en el delete swipe", "error al fer delete de a la api amb el swipe", e)
                        }finally {
                            dialog.dismiss()
                        }
                    }
                }

                builder.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                    betAdapter.notifyItemChanged(position)
                }

                builder.create().show()
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                actionState: Int, isCurrentlyActive: Boolean
            ) {
                // Dibuja un fondo rojo mientras se desliza
                val itemView = viewHolder.itemView
                c.drawRect(
                    itemView.left.toFloat(), itemView.top.toFloat(),
                    itemView.right.toFloat(), itemView.bottom.toFloat(),
                    backgroundPaint
                )
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvBets)
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
