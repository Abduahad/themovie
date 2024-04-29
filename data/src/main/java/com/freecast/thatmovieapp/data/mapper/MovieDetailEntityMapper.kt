import com.freecast.thatmovieapp.data.mapper.GenreEntityMapper
import com.freecast.thatmovieapp.data.model.GenreResult
import com.freecast.thatmovieapp.data.model.MovieDetailModel

class MovieDetailEntityMapper : (MovieDetailModel) -> MovieDetailEntity {
    override fun invoke(movieDetailModel: MovieDetailModel): MovieDetailEntity {
        return MovieDetailEntity(
            backdropPath = movieDetailModel.backdropPath,
            budget = movieDetailModel.budget,
            genres = GenreEntityMapper().invoke(GenreResult(movieDetailModel.genres)),
            id = movieDetailModel.id,
            imdbId = movieDetailModel.imdbId,
            originalTitle = if (movieDetailModel.originalTitle.isNullOrEmpty()) movieDetailModel.originalName else movieDetailModel.originalTitle,
            overview = movieDetailModel.overview,
            popularity = movieDetailModel.popularity,
            posterPath = movieDetailModel.posterPath,
            releaseDate = movieDetailModel.releaseDate,
            revenue = movieDetailModel.revenue,
            runtime = movieDetailModel.runtime,
            status = movieDetailModel.status,
            tagline = movieDetailModel.tagline,
            title = if (movieDetailModel.title.isNullOrEmpty()) movieDetailModel.name else movieDetailModel.title,
            video = movieDetailModel.video,
            voteAverage = movieDetailModel.voteAverage,
            voteCount = movieDetailModel.voteCount
        )
    }
}