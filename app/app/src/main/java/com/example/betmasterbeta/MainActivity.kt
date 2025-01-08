package com.example.betmasterbeta

import CategoriesAdapter
import GameCategory
import Match
import MatchesAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private val categorias = listOf(
        GameCategory.Filter,
        GameCategory.Lol,
        GameCategory.Valorant,
        GameCategory.Csgo
    )

    private val matches = listOf(
        Match("MadLionsFnatic", GameCategory.Lol),
        Match("T1GenG", GameCategory.Lol),
        Match("KTRolsterHLE", GameCategory.Lol),
        Match("G2Giants", GameCategory.Lol),
        Match("MadLionsG2", GameCategory.Lol),
        Match("T1HLE", GameCategory.Lol),
        Match("HereticsMKoi", GameCategory.Valorant),
        Match("FearXGenG", GameCategory.Lol)
    )
    companion object {
        var coins : Float = 9999f
        private const val PREFS_NAME = "app_preferences"
        private const val FAVORITES_KEY = "favoritos"
        val favoritos = mutableListOf<String>()

        fun loadFavorites(context: Context) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val favoritesSet = sharedPreferences.getStringSet(FAVORITES_KEY, emptySet())
            favoritos.clear()
            favoritos.addAll(favoritesSet ?: emptySet())
        }

        fun saveFavorites(context: Context) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putStringSet(FAVORITES_KEY, favoritos.toSet())
                apply()
            }
        }
    }

    private lateinit var rvGames: RecyclerView
    private lateinit var rvMatches: RecyclerView
    private lateinit var gamesAdapter: CategoriesAdapter
    private lateinit var matchesAdapter: MatchesAdapter
    private lateinit var txtCoins : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        loadFavorites(this)
        //loadCoinsFromPreferences()
        initComponent()
        initUI()
        updateCoins()


        val buttonShowDialog = findViewById<Button>(R.id.fabMisApuestas)
        buttonShowDialog.setOnClickListener {
            // Instancia y muestra el DialogFragment
            val dialogFragment = MyBetsDialogFragment(this)
            dialogFragment.show(supportFragmentManager, "CustomDialog")
        }
    }
    override fun onPause() {
        super.onPause()
        // Guardar favoritos al pausar la actividad
        saveFavorites(this)
        //saveCoinsToPreferences()
    }

    fun initComponent(){
        rvGames = findViewById(R.id.rvGames)
        rvMatches = findViewById(R.id.rvMatches)
        txtCoins = findViewById(R.id.txtCoins)
    }
    fun updateCoins(){
        val formattedCoins = String.format("%.0f", coins)
        txtCoins.text = "$formattedCoins"
        //saveCoinsToPreferences()
    }

    private fun initUI(){
        gamesAdapter = CategoriesAdapter(
            categorias,
            { position -> updateCategories(position) },  // Callback para la selección de la categoría
            { filterMatches() } // Callback para el filtro (filtrar los partidos)
        )
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvGames.adapter= gamesAdapter

        matchesAdapter = MatchesAdapter(matches)
        rvMatches.layoutManager = LinearLayoutManager(this)
        rvMatches.adapter = matchesAdapter
    }


    private fun onItemSelected(position:Int){
        matches[position].isSelected = !matches[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int){
        if (categorias[position] == GameCategory.Filter) {
            return
        }
        categorias[position].isSelected = !categorias[position].isSelected
        gamesAdapter.notifyItemChanged(position)
        updateTasks()
    }
    private fun updateTasks() {
        // Filtrar las categorías seleccionadas, excluyendo "Filter"
        val selectedCategories: List<GameCategory> = categorias
            .filter { it.isSelected && it != GameCategory.Filter }

        // Si no hay categorías seleccionadas, mostrar todos los partidos
        val newMatches = if (selectedCategories.isEmpty()) {
            matches
        } else {
            matches.filter { selectedCategories.contains(it.category) }
        }

        // Actualizar el adaptador con la nueva lista de partidos
        matchesAdapter.matches = newMatches
        matchesAdapter.notifyDataSetChanged()
    }
    private fun filterMatches() {

        val filteredMatches = matches.filter { match ->
            favoritos.any { team -> match.name.contains(team, ignoreCase = true) }
        }

        matchesAdapter.updateMatches(filteredMatches)
    }
    /*
    fun saveCoinsToPreferences() {
        // Obtener el SharedPreferences
        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Guardar el valor de coins
        editor.putFloat("coins", MainActivity.coins)

        // Aplicar los cambios
        editor.apply() // o editor.commit() si prefieres que sea sincrónico
    }
    fun loadCoinsFromPreferences() {
        // Obtener el SharedPreferences
        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        // Leer el valor de coins (con un valor predeterminado en caso de que no exista)
        MainActivity.coins = sharedPreferences.getFloat("coins", 9999f) // 9999f es el valor por defecto si no se ha guardado nada
    }
    */

}