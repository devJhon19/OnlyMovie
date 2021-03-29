package com.dev.jhon.onlymovie.data.room.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.dev.jhon.onlymovie.data.room.entity.Movie

@Dao
interface MovieDao : BaseDao<Movie>{
    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie ORDER BY creationDate DESC LIMIT 1")
    fun getLast() : Movie?

    @Query("DELETE FROM Movie")
    fun deleteAll()

    @Query("SELECT * FROM Movie WHERE id_movie = :id_movie LIMIT 1")
    fun findByIdMovie(id_movie : Int) : Movie?

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    fun movieById(): DataSource.Factory<Int, Movie>

}