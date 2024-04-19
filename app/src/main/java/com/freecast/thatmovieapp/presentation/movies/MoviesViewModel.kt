package com.freecast.thatmovieapp.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import com.freecast.thatmovieapp.domain.usecase.GetMoviesUseCase
import com.freecast.thatmovieapp.util.Constants
import org.koin.java.KoinJavaComponent.inject


open class MoviesViewModel : BaseViewModel() {
    private val movieRepository: MovieRepository by inject(MovieRepository::class.java)
    private val useCase: GetMoviesUseCase = GetMoviesUseCase(movieRepository)
    var endPoint: String = Constants.MoviesEndPoint.POPULAR
    var title: String = ""
    var movieId: Int = 0
    fun fetchMovies(): LiveData<List<Movie>> {
        val result: MutableLiveData<List<Movie>> = MutableLiveData()
        launchCoroutine {

            val response = if (movieId > 0) useCase.execute("movie/${movieId}/similar") else useCase.execute(endPoint)
            response.collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(it.isLoading)
                    }

                    is Resource.Success -> {
                        result.postValue(it.data)
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                    }
                }
            }
        }
        return result
    }

    fun fetchMoviesByGenreId(genreId: Int): LiveData<List<Movie>> {
        val result: MutableLiveData<List<Movie>> = MutableLiveData()
        launchCoroutine {
            useCase.execute(genreId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(it.isLoading)
                    }

                    is Resource.Success -> {
                        result.postValue(it.data)
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                    }
                }
            }
        }
        return result
    }
}