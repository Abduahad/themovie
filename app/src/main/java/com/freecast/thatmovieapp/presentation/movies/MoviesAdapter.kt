package com.freecast.thatmovieapp.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.util.Constants

class MoviesAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var movies: List<MovieEntity> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider_secondary, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.apply {
            setOnClickListener(onClickListener)
            tag = movies[position].id
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovies(movies: List<MovieEntity>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : BaseViewHolder<MovieEntity>(itemView) {
        override fun bind(item: MovieEntity) {
            findViewById<TextView>(R.id.textView).text = item.title
            loadImage(findViewById(R.id.imageView), findViewById(R.id.progressBar), Constants.BASE_IMAGE_URL + item.posterPath)
        }
    }
}