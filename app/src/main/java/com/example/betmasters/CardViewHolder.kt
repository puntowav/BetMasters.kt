package com.example.betmasters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val ivCard: ImageView = itemView.findViewById(R.id.ivCard);
    private val tvCardName: TextView = itemView.findViewById(R.id.tvCardName);
    private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice);
    fun bind(card: Card){
        tvCardName.text = card.name;
        tvPrice.text = String.format("%d", card.price);
        ivCard.setImageResource(card.img);
    }
}