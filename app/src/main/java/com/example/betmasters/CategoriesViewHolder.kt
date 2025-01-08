import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasterbeta.ExpandableCardActivity
import com.example.betmasterbeta.MainActivity
import com.example.betmasterbeta.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view){
    
    private val ivCategoryImg: ImageView = view.findViewById(R.id.ivCategoryImg)
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val viewContainer: CardView = view.findViewById(R.id.card_categories)
    
    
    fun render(gameCategory: GameCategory, onItemSelected: (Int) -> Unit, onFilterSelected: () -> Unit){

        val color = if (gameCategory.isSelected) {
            R.color.cardBackgroundSelected
        } else {
            R.color.cardBackground
        }

        viewContainer.setCardBackgroundColor(
            ContextCompat.getColor(viewContainer.context, color)
        )


        itemView.setOnClickListener {

            if(gameCategory != GameCategory.Filter && !GameCategory.Filter.isSelected) {

                gameCategory.isSelected = !gameCategory.isSelected
            }

            // Cambia el color del fondo según el nuevo estado
            val newColor = if (gameCategory.isSelected && gameCategory != GameCategory.Filter) {
                R.color.cardBackgroundSelected
            } else {
                R.color.cardBackground
            }

            viewContainer.setCardBackgroundColor(
                ContextCompat.getColor(viewContainer.context, newColor)
            )

            // Notifica que se seleccionó un elemento
            onItemSelected(layoutPosition)

            if (layoutPosition == 0) {
                // Mostrar PopupMenu para la posición 0
                val popupMenu = PopupMenu(itemView.context, itemView)
                popupMenu.menu.add("Filtrar")
                popupMenu.menu.add("Añadir a Favoritos")

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title) {
                        "Filtrar" -> {
                            gameCategory.isSelected = !gameCategory.isSelected

                            val filterColor = if (gameCategory.isSelected) {
                                R.color.cardBackgroundSelected
                            } else {
                                R.color.cardBackground
                            }

                            viewContainer.setCardBackgroundColor(
                                ContextCompat.getColor(viewContainer.context, filterColor)
                            )
                            onItemSelected(layoutPosition)
                            onFilterSelected()
                            true
                        }
                        "Añadir a Favoritos" -> {
                            val bottomSheetFragment = ExpandableCardActivity()
                            bottomSheetFragment.show(
                                (itemView.context as AppCompatActivity).supportFragmentManager,
                                "ExpandableCardActivity"
                            )
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }else {
                if(layoutPosition == 0) {
                    if (GameCategory.Filter.isSelected) {
                        GameCategory.Filter.isSelected =
                            false  // Cambiar el estado a no seleccionado
                        val defaultColor = R.color.cardBackground  // Color por defecto
                        viewContainer.setCardBackgroundColor(
                            ContextCompat.getColor(viewContainer.context, defaultColor)
                        )
                    }
                }
                // Acción para el resto de las posiciones
                val color = if (gameCategory.isSelected) {
                    R.color.cardBackground
                } else {
                    R.color.cardBackgroundSelected
                }

                viewContainer.setCardBackgroundColor(
                    ContextCompat.getColor(viewContainer.context, color)
                )

                // Llamar al callback de selección de elemento
                onItemSelected(layoutPosition)
            }
        }

        when(gameCategory){
            GameCategory.Filter -> {
                ivCategoryImg.setImageResource(R.drawable.add_24)
                tvCategoryName.text = "Add Filter"
            }
            GameCategory.Lol -> {
                ivCategoryImg.setImageResource(R.drawable.lol)
                tvCategoryName.text = "LOL"
            }
            GameCategory.Valorant -> {
                ivCategoryImg.setImageResource(R.drawable.valorant)
                tvCategoryName.text = "Valorant"
            }
            GameCategory.Csgo -> {
                ivCategoryImg.setImageResource(R.drawable.cs2)
                tvCategoryName.text = "CSGO"
            }

            else -> {}
        }

    }
}