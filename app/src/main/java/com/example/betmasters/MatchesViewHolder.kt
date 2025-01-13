package com.example.betmasters

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasters.ExpandableCardActivity2

class MatchesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val ivTeam1: ImageView = view.findViewById(R.id.ivTeam1)
    private val ivTeam2: ImageView = view.findViewById(R.id.ivTeam2)
    private val tvTeam1: TextView = view.findViewById(R.id.tvTeam1)
    private val tvTeam2: TextView = view.findViewById(R.id.tvTeam2)
    private val tvLiga: TextView = view.findViewById(R.id.tvLiga)
    private val tvFecha: TextView = view.findViewById(R.id.tvFecha)
    private val tvResultado: TextView = view.findViewById(R.id.tvResultado)
    private val tvMulti1: TextView = view.findViewById(R.id.tvMulti1)
    private val tvMulti2: TextView = view.findViewById(R.id.tvMulti2)



    fun render(match: Match, isVisible: Boolean){

        itemView.visibility = if (isVisible) View.VISIBLE else View.GONE

        itemView.setOnClickListener {
            if(match.name == "MadLionsFnatic") {
                ivTeam1.setImageResource(R.drawable.mad)
                ivTeam2.setImageResource(R.drawable.fnatic)
                tvTeam1.text = "MadLions"
                tvTeam2.text = "Fnatic"
                tvMulti1.text = "1.8"
                tvMulti2.text = "1.2"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.mad)
                    putInt("team2_image", R.drawable.fnatic)
                    putString("liga", "LEC")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "MadLions VS Fnatic")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "T1GenG") {
                ivTeam1.setImageResource(R.drawable.t1)
                ivTeam2.setImageResource(R.drawable.geng)
                tvTeam1.text = "T1"
                tvTeam2.text = "GenG"
                tvMulti1.text = "1.9"
                tvMulti2.text = "2.0"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.t1)
                    putInt("team2_image", R.drawable.geng)
                    putString("liga", "LCK")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "T1 VS GenG")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "KTRolsterHLE") {
                ivTeam1.setImageResource(R.drawable.kt_rolster)
                ivTeam2.setImageResource(R.drawable.hle)
                tvTeam1.text = "KTRolster"
                tvTeam2.text = "HLE"
                tvMulti1.text = "2.8"
                tvMulti2.text = "1.4"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.kt_rolster)
                    putInt("team2_image", R.drawable.hle)
                    putString("liga", "LCK")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "KTRolster VS HLE")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "G2Giants") {
                ivTeam1.setImageResource(R.drawable.g2)
                ivTeam2.setImageResource(R.drawable.giants)
                tvTeam1.text = "G2"
                tvTeam2.text = "Giants"
                tvMulti1.text = "1.1"
                tvMulti2.text = "2.4"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.g2)
                    putInt("team2_image", R.drawable.giants)
                    putString("liga", "LEC")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "G2 VS Giants")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "MadLionsG2") {
                ivTeam1.setImageResource(R.drawable.mad)
                ivTeam2.setImageResource(R.drawable.g2)
                tvTeam1.text = "MadLions"
                tvTeam2.text = "G2"
                tvMulti1.text = "2.2"
                tvMulti2.text = "1.3"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.mad)
                    putInt("team2_image", R.drawable.g2)
                    putString("liga", "LEC")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "MadLions VS G2")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "T1HLE") {
                ivTeam1.setImageResource(R.drawable.t1)
                ivTeam2.setImageResource(R.drawable.hle)
                tvTeam1.text = "T1"
                tvTeam2.text = "HLE"
                tvMulti1.text = "2.1"
                tvMulti2.text = "2.3"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.t1)
                    putInt("team2_image", R.drawable.hle)
                    putString("liga", "LCK")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "T1 VS HLE")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "FearXGenG") {
                ivTeam1.setImageResource(R.drawable.fearx)
                ivTeam2.setImageResource(R.drawable.geng)
                tvTeam1.text = "FearX"
                tvTeam2.text = "GengG"
                tvMulti1.text = "3.5"
                tvMulti2.text = "1.2"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.fearx)
                    putInt("team2_image", R.drawable.geng)
                    putString("liga", "LCK")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "FearX VS GenG")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "HereticsMKoi") {
                ivTeam1.setImageResource(R.drawable.heretics)
                ivTeam2.setImageResource(R.drawable.movistarkoi)
                tvTeam1.text = "Heretics"
                tvTeam2.text = "MKoi"
                tvMulti1.text = "1.2"
                tvMulti2.text = "2.7"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.heretics)
                    putInt("team2_image", R.drawable.movistarkoi)
                    putString("liga", "EMEA")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "Heretics VS MKoi")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "KCorpFUTEsports") {
                ivTeam1.setImageResource(R.drawable.karminecorp)
                ivTeam2.setImageResource(R.drawable.fut_esports)
                tvTeam1.text = "KCorp"
                tvTeam2.text = "FUTEsports"
                tvMulti1.text = "1.4"
                tvMulti2.text = "2.1"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.karminecorp)
                    putInt("team2_image", R.drawable.fut_esports)
                    putString("liga", "EMEA")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "KCorp VS FUTEsports")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "100TTeamLiquid") {
                ivTeam1.setImageResource(R.drawable.thieves)
                ivTeam2.setImageResource(R.drawable.team_liquid)
                tvTeam1.text = "100T"
                tvTeam2.text = "TeamLiquid"
                tvMulti1.text = "1.8"
                tvMulti2.text = "1.9"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.thieves)
                    putInt("team2_image", R.drawable.team_liquid)
                    putString("liga", "EMEA")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "100T VS TeamLiquid")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "TeamLiquidMKoi") {
                ivTeam1.setImageResource(R.drawable.team_liquid)
                ivTeam2.setImageResource(R.drawable.movistarkoi)
                tvTeam1.text = "TeamLiquid"
                tvTeam2.text = "MKoi"
                tvMulti1.text = "1.1"
                tvMulti2.text = "3.4"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.team_liquid)
                    putInt("team2_image", R.drawable.movistarkoi)
                    putString("liga", "EMEA")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "TeamLiquid VS MKoi")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "HereticsKCorp") {
                ivTeam1.setImageResource(R.drawable.heretics)
                ivTeam2.setImageResource(R.drawable.karminecorp)
                tvTeam1.text = "Heretics"
                tvTeam2.text = "KCorp"
                tvMulti1.text = "1.3"
                tvMulti2.text = "1.9"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.heretics)
                    putInt("team2_image", R.drawable.karminecorp)
                    putString("liga", "EMEA")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "Heretics VS KCorp")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "NAVIMouz") {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.mouz)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Mouz"
                tvMulti1.text = "1.4"
                tvMulti2.text = "1.6"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.navi)
                    putInt("team2_image", R.drawable.mouz)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "NAVI VS Mouz")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "NAVIHeroic") {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.heroic)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Heroic"
                tvMulti1.text = "1.3"
                tvMulti2.text = "1.8"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.navi)
                    putInt("team2_image", R.drawable.heroic)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "NAVI VS Heroic")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "FazeClanAstralis") {
                ivTeam1.setImageResource(R.drawable.fazeclan)
                ivTeam2.setImageResource(R.drawable.astralis)
                tvTeam1.text = "FazeClan"
                tvTeam2.text = "Astralis"
                tvMulti1.text = "1.2"
                tvMulti2.text = "2.3"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.fazeclan)
                    putInt("team2_image", R.drawable.astralis)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "FazeClan VS Astralis")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "FazeClanNAVI") {
                ivTeam1.setImageResource(R.drawable.fazeclan)
                ivTeam2.setImageResource(R.drawable.navi)
                tvTeam1.text = "FazeClan"
                tvTeam2.text = "NAVI"
                tvMulti1.text = "1.5"
                tvMulti2.text = "1.5"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.fazeclan)
                    putInt("team2_image", R.drawable.navi)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "FazeClan VS NAVI")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "NAVIVitality") {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.vitality)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Vitality"
                tvMulti1.text = "1.2"
                tvMulti2.text = "1.8"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.navi)
                    putInt("team2_image", R.drawable.vitality)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "NAVI VS Vitality")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }
            if(match.name == "VitalityFazeClan") {
                ivTeam1.setImageResource(R.drawable.vitality)
                ivTeam2.setImageResource(R.drawable.fazeclan)
                tvTeam1.text = "Vitality"
                tvTeam2.text = "FazeClan"
                tvMulti1.text = "2.6"
                tvMulti2.text = "1.2"
                val bottomSheetFragment = ExpandableCardActivity2()
                val bundle = Bundle().apply {
                    putString("team1", tvTeam1.text.toString())
                    putString("team2", tvTeam2.text.toString())
                    putString("multi1", tvMulti1.text.toString())
                    putString("multi2", tvMulti2.text.toString())
                    putInt("team1_image", R.drawable.vitality)
                    putInt("team2_image", R.drawable.fazeclan)
                    putString("liga", "ESL")
                    putString("fecha", "Fecha")
                    putString("resultado", "Resultado")
                    putString("matchname", "Vitality VS FazeClan")

                }
                bottomSheetFragment.arguments = bundle

                bottomSheetFragment.show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    "ExpandableCardActivity2"
                )
            }

        }

        when (match.name) {
            "MadLionsFnatic" -> {
                ivTeam1.setImageResource(R.drawable.mad)
                ivTeam2.setImageResource(R.drawable.fnatic)
                tvTeam1.text = "MadLions"
                tvTeam2.text = "Fnatic"
                tvMulti1.text = "1.8"
                tvMulti2.text = "1.2"
                tvLiga.text = "LEC"
            }
            "T1GenG" -> {
                ivTeam1.setImageResource(R.drawable.t1)
                ivTeam2.setImageResource(R.drawable.geng)
                tvTeam1.text = "T1"
                tvTeam2.text = "GenG"
                tvMulti1.text = "1.9"
                tvMulti2.text = "2.0"
                tvLiga.text = "LCK"
            }
            "KTRolsterHLE" -> {
                ivTeam1.setImageResource(R.drawable.kt_rolster)
                ivTeam2.setImageResource(R.drawable.hle)
                tvTeam1.text = "KTRoltster"
                tvTeam2.text = "HLE"
                tvMulti1.text = "2.8"
                tvMulti2.text = "1.4"
                tvLiga.text = "LCK"
            }
            "G2Giants" -> {
                ivTeam1.setImageResource(R.drawable.g2)
                ivTeam2.setImageResource(R.drawable.giants)
                tvTeam1.text = "G2"
                tvTeam2.text = "Giants"
                tvMulti1.text = "1.1"
                tvMulti2.text = "2.4"
                tvLiga.text = "LEC"
            }
            "MadLionsG2" -> {
                ivTeam1.setImageResource(R.drawable.mad)
                ivTeam2.setImageResource(R.drawable.g2)
                tvTeam1.text = "MadLions"
                tvTeam2.text = "G2"
                tvMulti1.text = "2.2"
                tvMulti2.text = "1.3"
                tvLiga.text = "LEC"
            }
            "T1HLE" -> {
                ivTeam1.setImageResource(R.drawable.t1)
                ivTeam2.setImageResource(R.drawable.hle)
                tvTeam1.text = "T1"
                tvTeam2.text = "HLE"
                tvMulti1.text = "2.1"
                tvMulti2.text = "2.3"
                tvLiga.text = "LCK"
            }
            "FearXGenG" -> {
                ivTeam1.setImageResource(R.drawable.fearx)
                ivTeam2.setImageResource(R.drawable.geng)
                tvTeam1.text = "FearX"
                tvTeam2.text = "GenG"
                tvMulti1.text = "3.5"
                tvMulti2.text = "1.2"
                tvLiga.text = "LCK"
            }
            "HereticsMKoi" -> {
                ivTeam1.setImageResource(R.drawable.heretics)
                ivTeam2.setImageResource(R.drawable.movistarkoi)
                tvTeam1.text = "Heretics"
                tvTeam2.text = "MKoi"
                tvMulti1.text = "1.2"
                tvMulti2.text = "2.7"
                tvLiga.text = "EMEA"
            }
            "KCorpFUTEsports" -> {
                ivTeam1.setImageResource(R.drawable.karminecorp)
                ivTeam2.setImageResource(R.drawable.fut_esports)
                tvTeam1.text = "KCorp"
                tvTeam2.text = "FUTEsports"
                tvMulti1.text = "1.4"
                tvMulti2.text = "2.1"
                tvLiga.text = "EMEA"
            }
            "100TTeamLiquid" -> {
                ivTeam1.setImageResource(R.drawable.thieves)
                ivTeam2.setImageResource(R.drawable.team_liquid)
                tvTeam1.text = "100T"
                tvTeam2.text = "TeamLiquid"
                tvMulti1.text = "1.8"
                tvMulti2.text = "1.9"
                tvLiga.text = "EMEA"
            }
            "TeamLiquidMKoi" -> {
                ivTeam1.setImageResource(R.drawable.team_liquid)
                ivTeam2.setImageResource(R.drawable.movistarkoi)
                tvTeam1.text = "TeamLiquid"
                tvTeam2.text = "MKoi"
                tvMulti1.text = "1.1"
                tvMulti2.text = "3.4"
                tvLiga.text = "EMEA"
            }
            "HereticsKCorp" -> {
                ivTeam1.setImageResource(R.drawable.heretics)
                ivTeam2.setImageResource(R.drawable.karminecorp)
                tvTeam1.text = "Heretics"
                tvTeam2.text = "KCorp"
                tvMulti1.text = "1.3"
                tvMulti2.text = "1.9"
                tvLiga.text = "EMEA"
            }
            "NAVIMouz" -> {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.mouz)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Mouz"
                tvMulti1.text = "1.4"
                tvMulti2.text = "1.6"
                tvLiga.text = "ESL"
            }
            "NAVIHeroic" -> {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.heroic)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Heroic"
                tvMulti1.text = "1.3"
                tvMulti2.text = "1.8"
                tvLiga.text = "ESL"
            }
            "FazeClanAstralis" -> {
                ivTeam1.setImageResource(R.drawable.fazeclan)
                ivTeam2.setImageResource(R.drawable.astralis)
                tvTeam1.text = "FazeClan"
                tvTeam2.text = "Astralis"
                tvMulti1.text = "1.2"
                tvMulti2.text = "2.3"
                tvLiga.text = "ESL"
            }
            "FazeClanNAVI" -> {
                ivTeam1.setImageResource(R.drawable.fazeclan)
                ivTeam2.setImageResource(R.drawable.navi)
                tvTeam1.text = "FAzeClan"
                tvTeam2.text = "NAVI"
                tvMulti1.text = "1.5"
                tvMulti2.text = "1.5"
                tvLiga.text = "ESL"
            }
            "NAVIVitality" -> {
                ivTeam1.setImageResource(R.drawable.navi)
                ivTeam2.setImageResource(R.drawable.vitality)
                tvTeam1.text = "NAVI"
                tvTeam2.text = "Vitality"
                tvMulti1.text = "1.2"
                tvMulti2.text = "1.8"
                tvLiga.text = "ESL"
            }
            "VitalityFazeClan" -> {
                ivTeam1.setImageResource(R.drawable.vitality)
                ivTeam2.setImageResource(R.drawable.fazeclan)
                tvTeam1.text = "Vitality"
                tvTeam2.text = "FazeClan"
                tvMulti1.text = "2.6"
                tvMulti2.text = "1.2"
                tvLiga.text = "ESL"
            }
            else -> {

            }
        }

    }
}
