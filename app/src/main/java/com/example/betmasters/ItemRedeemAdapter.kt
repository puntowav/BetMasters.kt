package com.example.betmasters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemRedeemAdapter(private val items: List<ItemRedeem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val BigItem = 1
        private const val ThinItem = 2
        private const val rvHorizontal = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemRedeem.BigItem -> BigItem
            is ItemRedeem.ThinItem -> ThinItem
            is ItemRedeem.HorizontalRv -> rvHorizontal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            BigItem -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_big_redeem, parent, false)
                BigItemViewHolder(view)
            }
            ThinItem -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_thin_redeem, parent, false)
                ThinItemViewHolder(view)
            }
            rvHorizontal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_horizontal_card_redeem, parent, false)
                HorizontalRvViewHolder(view)
            }
            else -> throw  IllegalArgumentException("Tipo no valido")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is BigItemViewHolder -> holder.bind(items[position] as ItemRedeem.BigItem)
            is ThinItemViewHolder -> holder.bind(items[position] as ItemRedeem.ThinItem)
            is HorizontalRvViewHolder -> holder.bind(items[position] as ItemRedeem.HorizontalRv)
        }
    }
}