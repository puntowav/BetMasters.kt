package com.example.betmasters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.R


class MatchesAdapter(var matches: List<Match>):RecyclerView.Adapter<MatchesViewHolder>(){

    private val itemVisibilityMap = mutableListOf<Boolean>().apply {
        addAll(matches.map { true }) // Inicializamos todos los ítems como visibles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.render(matches[position], itemVisibilityMap[position])
    }


    override fun getItemCount() = matches.size

    fun updateMatches(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()  // Notifica al RecyclerView que los datos han cambiado
    }
    fun updateMatchVisibility(position: Int, isVisible: Boolean) {
        itemVisibilityMap[position] = isVisible
        notifyItemChanged(position)  // Notificamos que este ítem ha cambiado
    }
}