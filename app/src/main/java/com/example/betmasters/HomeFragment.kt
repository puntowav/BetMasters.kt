package com.example.betmasters

import CategoriesAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

class HomeFragment : Fragment() {

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
        Match("HereticsMKoi", GameCategory.Lol),
        Match("FearXGenG", GameCategory.Lol)
    )

    companion object {
        var coins: Float = 9999f
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
    private lateinit var txtCoins: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initComponent(view)
        initUI(view)
        updateCoins()

        val buttonShowDialog = view.findViewById<Button>(R.id.fabMisApuestas)
        buttonShowDialog.setOnClickListener {
            // Instancia y muestra el DialogFragment
            val dialogFragment = MyBetsDialogFragment(MainActivity())
            dialogFragment.show(parentFragmentManager, "CustomDialog")
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        // Guardar favoritos al pausar la actividad
        saveFavorites(requireContext())
        //saveCoinsToPreferences()
    }

    private fun initComponent(view: View) {
        rvGames = view.findViewById(R.id.rvGames)
        rvMatches = view.findViewById(R.id.rvMatches)
        txtCoins = view.findViewById(R.id.txtCoins)
    }

    fun updateCoins() {
        val formattedCoins = String.format("%.0f", coins)
        txtCoins.text = "$formattedCoins"
        //saveCoinsToPreferences()
    }

    private fun initUI(view: View) {
        gamesAdapter = CategoriesAdapter(
            categorias,
            { position -> updateCategories(position) },  // Callback para la selección de la categoría
            { filterMatches() } // Callback para el filtro (filtrar los partidos)
        )
        rvGames.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvGames.adapter = gamesAdapter

        matchesAdapter = MatchesAdapter(matches)
        rvMatches.layoutManager = LinearLayoutManager(context)
        rvMatches.adapter = matchesAdapter
    }

    private fun onItemSelected(position: Int) {
        matches[position].isSelected = !matches[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int) {
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
}
