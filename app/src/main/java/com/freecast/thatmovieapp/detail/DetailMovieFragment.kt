package com.freecast.thatmovieapp.detail


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.movies.MoviesFragment
import com.freecast.thatmovieapp.util.Constants

class DetailMovieFragment : BaseFragment<DetailMovieViewModel>(R.layout.fragment_detail_movie, DetailMovieViewModel::class.java) {
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarPoster: ProgressBar
    private lateinit var textViewTitle: TextView
    private lateinit var textViewBudget: TextView
    private lateinit var textViewReleaseDate: TextView
    private lateinit var textViewSummary: TextView
    private lateinit var linearLayoutGenres: LinearLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var fragmentContainerRelated: FrameLayout
    private lateinit var textViewDuration: TextView
    private lateinit var textViewRating: TextView
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.movieId = it.getInt(MOVIE_ID)
        }
    }

    override fun onInitViews() {
        super.onInitViews()
        progressBar = findViewByID(R.id.progressBar)
        progressBarPoster = findViewByID(R.id.progressBarPoster)
        textViewTitle = findViewByID(R.id.textViewTitle)
        textViewBudget = findViewByID(R.id.textViewBudget)
        textViewDuration = findViewByID(R.id.textViewDuration)
        textViewRating = findViewByID(R.id.textViewRating)
        textViewReleaseDate = findViewByID(R.id.textViewReleaseDate)
        textViewSummary = findViewByID(R.id.textViewSummary)
        linearLayoutGenres = findViewByID(R.id.linearLayoutGenres)
        linearLayout = findViewByID(R.id.linearLayout)
        imageView = findViewByID(R.id.imageView)
        fragmentContainerRelated = findViewByID(R.id.fragmentContainerRelated)
        transaction(R.id.fragmentContainerRelated, MoviesFragment.newInstance(viewModel.movieId, getString(R.string.detail_similar_movies)), false, isReplace = true)
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.fetchMovieDetail().observe(viewLifecycleOwner) {
            textViewTitle.text = it.title
            textViewReleaseDate.text = it.release_date
            textViewSummary.text = it.overview
            textViewDuration.text = getString(R.string.detail_duration_min, it.runtime)
            textViewRating.text = getString(R.string.detail_rated, it.vote_average.toString(), it.vote_count)
            textViewBudget.text = it.budget.toString()
            it.backdrop_path?.let { posterPath -> loadPoster(posterPath) }
            linearLayoutGenres.removeAllViews()
            it.genres.forEach { genre ->
                linearLayoutGenres.addView(getGenreItemView(genre.name))
            }
        }
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            linearLayout.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            linearLayout.visibility = View.VISIBLE
        }
    }

    private fun getGenreItemView(genre: String): TextView {
        val textView = LayoutInflater.from(requireContext()).inflate(R.layout.item_genre, linearLayoutGenres, false).findViewById<TextView>(R.id.textView) as TextView
        textView.text = genre
        return textView
    }

    private fun loadPoster(posterName: String) {
        Glide.with(requireContext()).load(Constants.BASE_IMAGE_URL + posterName).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBarPoster.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBarPoster.visibility = View.GONE
                return false
            }
        }).into(imageView)

    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        @JvmStatic
        fun newInstance(movieId: Int) =
            DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
    }

}