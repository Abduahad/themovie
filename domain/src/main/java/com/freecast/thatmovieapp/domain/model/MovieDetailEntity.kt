import com.freecast.thatmovieapp.domain.model.GenreEntity

data class MovieDetailEntity(
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreEntity>,
    val id: Int,
    val imdbId: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int,
    val runtime: Int,
    val status: String?,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)