import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasterbeta.R


class CategoriesAdapter(private val categories: List<GameCategory>, private val onItemSelected:(Int) -> Unit, private val onFilterSelected: () -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected, onFilterSelected)
    }


    override fun getItemCount() = categories.size

}