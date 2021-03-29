package com.dev.jhon.onlymovie.data.room.entity

import androidx.room.*
import com.dev.jhon.onlymovie.data.room.converters.Converters
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = Movie.TABLE_NAME,
    indices = [Index(value = ["id_movie"], unique = true)]
)
@TypeConverters(Converters::class)
class Movie(@ColumnInfo(name = "adult") val adult: Boolean,
            @ColumnInfo(name = "backdrop_path") val backdrop_path: String?,
            @ColumnInfo(name = "genre_ids") val genre_ids: ArrayList<Int?>?,
            @ColumnInfo(name = "id_movie") val id_movie: Int,
            @ColumnInfo(name = "original_language") val original_language: String,
            @ColumnInfo(name = "original_title") val original_title: String,
            @ColumnInfo(name = "overview") val overview: String,
            @ColumnInfo(name = "popularity") val popularity: Double,
            @ColumnInfo(name = "poster_path") val poster_path: String?,
            @ColumnInfo(name = "release_date") val release_date: Date,
            @ColumnInfo(name = "title") val title: String,
            @ColumnInfo(name = "video") val video: Boolean,
            @ColumnInfo(name = "vote_average") val vote_average: Double,
            @ColumnInfo(name = "vote_count") val vote_count: Int,
            @ColumnInfo(name = "creationDate") val creationDate: Date? = Date()
                    ) : Serializable{

    companion object {
        const val TABLE_NAME = "Movie"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    override fun equals(other: Any?): Boolean {
        return (other is Movie)
                && adult == other.adult
                && backdrop_path == other.backdrop_path
                && genre_ids == other.genre_ids
                && id_movie == other.id_movie
                && original_language == other.original_language
                && original_title == other.original_title
                && overview == other.overview
                && popularity == other.popularity
                && poster_path == other.poster_path
                && release_date == other.release_date
                && title == other.title
                && video == other.video
                && vote_average == other.vote_average
                && vote_count == other.vote_count
                && creationDate == other.creationDate
    }
}