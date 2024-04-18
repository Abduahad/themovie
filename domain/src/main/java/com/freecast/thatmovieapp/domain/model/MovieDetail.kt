import com.freecast.thatmovieapp.domain.model.Genre

data class MovieDetail(
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val imdb_id: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Float,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int,
    val runtime: Int,
    val status: String?,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)