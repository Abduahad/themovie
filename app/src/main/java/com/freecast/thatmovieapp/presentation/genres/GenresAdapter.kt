package com.freecast.thatmovieapp.presentation.genres

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.presentation.movies.OnLoadMoviesListener

//ToDo:Refactor this class
class GenresAdapter(private val genres: List<GenreEntity>, private val isTv: Boolean = false) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>(), OnClickListener {
    private var selectedPosition: Int = -1
    private var onRefreshMoviesListener: OnLoadMoviesListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        return GenresViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(genres[position])
        holder.itemView.apply {
            tag = position
            setOnClickListener(this@GenresAdapter)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    //Todo: use notifyItemChanged instead of notifyDataSetChanged
    override fun onClick(v: View) {
        val position = v.tag as Int
        if (selectedPosition == position) {
            selectedPosition = -1
            onRefreshMoviesListener?.onLoadDefaultMovies()
        } else {
            val genre = genres[position]
            selectedPosition = position
            onRefreshMoviesListener?.onLoadMoviesByGenre(genre.name, genre.id)
        }
        notifyDataSetChanged()

    }
    //Todo: Remove this method
    fun setOnRefreshListener(onRefreshMoviesListener: OnLoadMoviesListener?) {
        this.onRefreshMoviesListener = onRefreshMoviesListener
    }
    inner class GenresViewHolder(itemView: View) : BaseViewHolder<GenreEntity>(itemView) {
        private val textView: TextView = findViewById(R.id.textView)
        override fun bind(item: GenreEntity) {
            textView.text = item.name
            if (selectedPosition > -1 && item.id == genres[selectedPosition].id) {
                textView.setBackgroundColor(getColor(R.color.selected_genre_color))
            } else {
                textView.setBackgroundColor(getColor(R.color.dark_blue))
            }
        }
    }
}