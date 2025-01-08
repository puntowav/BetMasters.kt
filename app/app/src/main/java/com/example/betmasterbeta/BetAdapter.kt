import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betmasterbeta.MainActivity
import com.example.betmasterbeta.R


class BetAdapter(var bets: List<Bet>, private val activity: MainActivity):RecyclerView.Adapter<BetsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mybet, parent, false)
        return BetsViewHolder(view, activity)
    }

    override fun onBindViewHolder(holder: BetsViewHolder, position: Int) {
        holder.render(bets[position], this)
    }


    override fun getItemCount() = bets.size

    fun updateBets(newBets: List<Bet>) {
        bets = newBets
        notifyDataSetChanged()  // Notifica al RecyclerView que los datos han cambiado
    }
}