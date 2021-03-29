package com.dev.jhon.onlymovie.domain.dto

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class MoviesResponseDTO(
    val dates: DatesDTO,
    val page: Int,
    val results: ArrayList<MovieDTO>,
    val total_pages: Int,
    val total_results: Int) : Serializable

data class DatesDTO(val maximum: String, val minimum: String)

data class MovieDTO(val adult: Boolean,
                    val backdrop_path: String,
                    val genre_ids: ArrayList<Int?>?,
                    val id: Int,
                    val original_language: String,
                    val original_title: String,
                    val overview: String,
                    val popularity: Double,
                    val poster_path: String,
                    val release_date: Date,
                    val title: String,
                    val video: Boolean,
                    val vote_average: Double,
                    val vote_count: Int)


