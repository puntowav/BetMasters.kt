package com.example.betmasters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.betmasters.ApiRetrofit.LoginAPI
import com.example.betmasters.HomeFragment.Companion.coins
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class Main : AppCompatActivity(), DialogCallBack {
    private lateinit var txtCoins : TextView
    private lateinit var txtCoinMenu : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val drawer_menu: NavigationView = findViewById(R.id.drawer_menu)
        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val profileButton: ImageButton = findViewById(R.id.btnPerfil)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val headerView = drawer_menu.getHeaderView(0) // Obtén el diseño inflado del menú
        val cvNavigate = headerView.findViewById<androidx.cardview.widget.CardView>(R.id.cvProfile)
        txtCoinMenu = headerView.findViewById(R.id.txtCoinsDrawer)
        txtCoins = findViewById(R.id.txtCoins)

        updateCoins()

        val buttonShowDialog = findViewById<MaterialButton>(R.id.fabMisApuestas)
        //TODO: Hacer peticion GET
        buttonShowDialog.setOnClickListener {
            // Instancia y muestra el DialogFragment
            val dialogFragment = MyBetsDialogFragment(this)
            dialogFragment.show(supportFragmentManager, "CustomDialog")
        }

        profileButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(drawer_menu)) {
                drawerLayout.closeDrawer(drawer_menu) // Cierra el Drawer si está abierto
            } else {
                drawerLayout.openDrawer(drawer_menu) // Abre el Drawer si está cerrado
            }
        }

        cvNavigate.setOnClickListener {
            val intent = Intent(this, Edit_profile::class.java)
            startActivity(intent)
        }

        drawer_menu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
                R.id.titulos -> {
                    showToast("Titulos seleccionado")
                }
                R.id.tienda -> {
                    showToast("Tienda seleccionado")
                }
                R.id.stats -> {
                    val intent2 = Intent(this, Estadisticas::class.java)
                    startActivity(intent2)
                }
                else -> {
                    showToast("Opción no reconocida")
                }
            }
            // Cerrar el Drawer después de seleccionar una opción
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when(item.itemId){
                R.id.home -> selectedFragment = HomeFragment()
                R.id.redeem -> selectedFragment = RedeemFragment()
                R.id.game -> selectedFragment = GamesFragment()
            }
            if(selectedFragment != null){
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            true
        }
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.home
        }

        lifecycleScope.launch {
            try {
                val response = LoginAPI.API().getBets()
                if(response.isSuccessful){
                    val bets = response.body() ?: emptyList()
                    MyBetsDialogFragment.bets.clear()
                    MyBetsDialogFragment.bets.addAll(bets)
                }
            }catch (e: Exception){
                Log.e("Add all bets", "Error al añadir todas las bets de la API", e)
            }
        }
    }

    fun updateCoins() {
        val formattedCoins = String.format("%.0f", coins)
        txtCoins.text = "$formattedCoins"
        txtCoinMenu.text = "$formattedCoins"
        //saveCoinsToPreferences()
    }

    override fun onCoinsUpdated() {
        updateCoins()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}