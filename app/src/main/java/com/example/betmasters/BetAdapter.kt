package com.example.betmasters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BetAdapter(var bets: List<Bet>, private val callBack: DialogCallBack):RecyclerView.Adapter<BetsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mybet, parent, false)
        return BetsViewHolder(view, callBack)
    }

    override fun onBindViewHolder(holder: BetsViewHolder, position: Int) {
        holder.render(bets[position], this)
    }


    override fun getItemCount() = bets.size

    fun updateBets(newBets: List<Bet>) {
        bets = newBets
        notifyDataSetChanged()  // Notifica al RecyclerView que los datos han cambiado
    }
}
