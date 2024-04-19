package com.freecast.thatmovieapp.presentation.genres

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.Genre

class GenresAdapter(
    private val genres: List<Genre>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {
    var selectedPosition: Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        return GenresViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(genres[position])
        holder.itemView.apply {
            tag = position
            setOnClickListener(onClickListener)
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun getGenreByPosition(position: Int): Genre {
        return genres[position]
    }

    inner class GenresViewHolder(itemView: View) : BaseViewHolder<Genre>(itemView) {
        private val textView: TextView = findViewById(R.id.textView)
        override fun bind(item: Genre) {
            textView.text = item.name
            if (selectedPosition > -1 && item.id == genres[selectedPosition].id) {
                textView.setBackgroundColor(getColor(R.color.selected_genre_color))
            } else {
                textView.setBackgroundColor(getColor(R.color.dark_blue))
            }
        }
    }
}