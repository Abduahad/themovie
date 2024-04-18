package com.freecast.thatmovieapp.movies

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseViewHolder
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.util.Constants

class MoviesAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var movies: List<Movie> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider_secondary, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener(onClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        private val textView: TextView = findViewById(R.id.textView)
        private val imageView: ImageView = findViewById(R.id.imageView)
        private val progressBar: View = findViewById(R.id.progressBar)
        override fun bind(item: Movie) {
            textView.text = item.title
            Glide.with(context).load(Constants.BASE_IMAGE_URL + item.posterPath).transform(RoundedCorners(16)).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            }).into(imageView)


        }
    }
}