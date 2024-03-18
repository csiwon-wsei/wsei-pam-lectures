package pl.wsei.pam.lectures.lecture3.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import pl.wsei.pam.lectures.R

class MovieRecyclerViewAdapter(val data: List<Movie>): RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    class MovieViewHolder(val row: View): RecyclerView.ViewHolder(row){
        val title = row.findViewById<TextView>(R.id.l3ItemText)
        val rating = row.findViewById<RatingBar>(R.id.l3ItemRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return MovieViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = data.get(position).title
        holder.rating.rating = data.get(position).rating
        holder.rating.setOnRatingBarChangeListener(object: OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, r: Float, fromUser: Boolean) {
                data.get(position).rating = r
                Snackbar.make(holder.rating,"Changed", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}