package com.example.betmasters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThinItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivCard: ImageView = itemView.findViewById(R.id.ivProdThin);
    private val tvCardName: TextView = itemView.findViewById(R.id.tvProdThin);
    private val tvPrice: TextView = itemView.findViewById(R.id.tvPriceThin);
    fun bind(item: ItemRedeem.ThinItem){
        tvCardName.text = item.name;
        tvPrice.text = String.format("%d", item.price);
        ivCard.setImageResource(item.img);
    }
}