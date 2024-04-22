package com.freecast.thatmovieapp.presentation.detail


import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.presentation.movies.MoviesFragment
import com.freecast.thatmovieapp.util.Constants


class DetailMovieFragment : BaseFragment<DetailMovieViewModel>(R.layout.fragment_detail_movie, DetailMovieViewModel::class.java), View.OnClickListener {
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
    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var frameLayoutPlayer: FrameLayout
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
        playerView = findViewByID(R.id.playerView)
        player = ExoPlayer.Builder(requireContext()).build()
        playerView.player = player
        fragmentContainerRelated = findViewByID(R.id.fragmentContainerRelated)
        frameLayoutPlayer = findViewByID(R.id.frameLayoutPlayer)
        transaction(R.id.fragmentContainerRelated, MoviesFragment.newInstance(viewModel.movieId, getString(R.string.detail_similar_movies)), false, isReplace = true)
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.fetchMovieDetail().observe(viewLifecycleOwner) {
            textViewTitle.text = it.title
            textViewReleaseDate.text = it.releaseDate
            textViewSummary.text = it.overview
            textViewDuration.text = getString(R.string.detail_duration_min, it.runtime)
            textViewRating.text = getString(R.string.detail_rated, it.voteAverage.toString(), it.voteCount)
            textViewBudget.text = it.budget.toString()
            it.backdropPath?.let { posterPath -> loadPoster(posterPath) }
            linearLayoutGenres.removeAllViews()
            it.genres.forEach { genre ->
                linearLayoutGenres.addView(getGenreItemView(genre.name))
            }
        }
    }

    override fun onInitListeners() {
        super.onInitListeners()
        imageView.setOnClickListener(this)
        player.addListener(object : Player.Listener {
            @OptIn(UnstableApi::class)
            override fun onPlayerError(error: PlaybackException) {
                Log.e("PlayerError", "An error occurred during playback", error)
            }
        })
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            frameLayoutPlayer.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            frameLayoutPlayer.visibility = View.VISIBLE
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

    //ToDo:ExoPlayer does not directly support playing YouTube videos due to YouTube's terms of service. YouTube provides its own API for playing videos, which is the YouTube Android Player API.
    @OptIn(UnstableApi::class)
    private fun initializePlayer(videoUri: String) {
        val httpDataSourceFactory: HttpDataSource.Factory = DefaultHttpDataSource.Factory()
            .setUserAgent(Util.getUserAgent(requireContext(), "app-name"))
        val mediaSourceFactory = ProgressiveMediaSource.Factory(httpDataSourceFactory)
        val mediaSource: MediaSource = mediaSourceFactory.createMediaSource(MediaItem.fromUri(Uri.parse("https://dl.dropbox.com/s/5y0ma68jhp0731t/sample.mp4")))

        player.setMediaSource(mediaSource)
        player.prepare()
        player.play()

    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.imageView -> {
                    imageView.visibility = View.GONE
                    playerView.visibility = View.VISIBLE
                    viewModel.fetchMovieVideo().observe(viewLifecycleOwner) {
                        initializePlayer(Constants.BASE_VIDEO_URL + it.key)
                    }
                }
            }
        }
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