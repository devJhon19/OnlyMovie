package com.dev.jhon.onlymovie.data.network

import com.dev.jhon.onlymovie.domain.dto.MoviesResponseDTO
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TMDbApi {

    @GET("/3/movie/upcoming/?")
    suspend fun getMovies(
        @Query("page") page : Int,
        @Query("api_key") api_key : String
    ): Response<MoviesResponseDTO>

}