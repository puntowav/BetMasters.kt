package com.example.betmasters

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class RedeemFragment : Fragment() {

    private val items = listOf(
        ItemRedeem.BigItem("Logitech G733", 6000, R.drawable.g733),
        ItemRedeem.ThinItem("Steam random key", 500, R.drawable.randomkey),
        ItemRedeem.HorizontalRv(
            listOf(
                Card("Itunes 15$ card", 1000, R.drawable.applecard),
                Card("Play Store 10$ card", 1000, R.drawable.googleplay)
            )
        )
    )

    private lateinit var rvItemRedeem: RecyclerView
    private lateinit var itemRedeemAdapter: ItemRedeemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  view = inflater.inflate(R.layout.fragment_redeem, container, false)
        rvItemRedeem = view.findViewById(R.id.rvGeneral)
        itemRedeemAdapter = ItemRedeemAdapter(items)
        rvItemRedeem.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvItemRedeem.adapter = itemRedeemAdapter
        return view
    }
}