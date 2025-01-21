package com.example.betmasters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val rvHorizontal: RecyclerView = itemView.findViewById(R.id.rvCard)
    fun bind(item: ItemRedeem.HorizontalRv){
        rvHorizontal.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        rvHorizontal.adapter = CardAdapter(item.items)
    }
}