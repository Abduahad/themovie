import com.freecast.thatmovieapp.data.mapper.GenreEntityMapper
import com.freecast.thatmovieapp.data.model.GenreResult
import com.freecast.thatmovieapp.data.model.MovieDetailEntity

class MovieDetailEntityMapper : (MovieDetailEntity) -> MovieDetail {
    override fun invoke(movieDetailEntity: MovieDetailEntity): MovieDetail {
        return MovieDetail(
            backdrop_path = movieDetailEntity.backdropPath,
            budget = movieDetailEntity.budget,
            genres = GenreEntityMapper().invoke(GenreResult(movieDetailEntity.genres)),
            id = movieDetailEntity.id,
            imdb_id = movieDetailEntity.imdbId,
            original_title = movieDetailEntity.originalTitle,
            overview = movieDetailEntity.overview,
            popularity = movieDetailEntity.popularity,
            poster_path = movieDetailEntity.posterPath,
            release_date = movieDetailEntity.releaseDate,
            revenue = movieDetailEntity.revenue,
            runtime = movieDetailEntity.runtime,
            status = movieDetailEntity.status,
            tagline = movieDetailEntity.tagline,
            title = movieDetailEntity.title,
            video = movieDetailEntity.video,
            vote_average = movieDetailEntity.voteAverage,
            vote_count = movieDetailEntity.voteCount
        )
    }
}