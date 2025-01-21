package com.example.betmasters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BigItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivCard: ImageView = itemView.findViewById(R.id.ivPordBig);
    private val tvCardName: TextView = itemView.findViewById(R.id.tvPordBig);
    private val tvPrice: TextView = itemView.findViewById(R.id.tvPriceBig);
    fun bind(item: ItemRedeem.BigItem){
        tvCardName.text = item.name;
        tvPrice.text = String.format("%d", item.price);
        ivCard.setImageResource(item.img);
    }
}