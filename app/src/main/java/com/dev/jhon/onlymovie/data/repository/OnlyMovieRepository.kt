package com.dev.jhon.onlymovie.data.repository

import android.accounts.NetworkErrorException
import androidx.paging.DataSource
import com.dev.jhon.onlymovie.BuildConfig
import com.dev.jhon.onlymovie.data.network.TMDbNetwork
import com.dev.jhon.onlymovie.domain.extensions.asDatabaseModel
import com.dev.jhon.onlymovie.data.room.AppRoomDatabase
import com.dev.jhon.onlymovie.data.room.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IOnlyMovieRepository{
    fun insertAllMovies(data: List<Movie>)
    fun findByIdMovie(idMovie : Int) : Movie?
    fun findRangeOfMovies(amount : Int) : List<Movie>
    fun movieById() : DataSource.Factory<Int, Movie>
}

class OnlyMovieRepository(private val database: AppRoomDatabase) : IOnlyMovieRepository {

    override fun insertAllMovies(data: List<Movie>) {
        return database.movieDao().upsertAll(data)
    }

    override fun findByIdMovie(idMovie: Int): Movie? {
        return database.movieDao().findByIdMovie(idMovie)
    }

    override fun findRangeOfMovies(amount: Int): List<Movie> {
//        return movieDao.findByIdMovie(idMovie)
        return ArrayList<Movie>()
    }

    override fun movieById(): DataSource.Factory<Int, Movie> {
        return database.movieDao().movieById()
    }

    suspend fun refreshMovies(page : Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = TMDbNetwork.api.getMovies(page, BuildConfig.ApiTMDb)
                if(response.isSuccessful)
                    database.movieDao().upsertAll(response.body()!!.asDatabaseModel())
                else
                    throw NetworkErrorException()
            }catch (e : Exception){
                e.printStackTrace()
            }

        }
    }
}