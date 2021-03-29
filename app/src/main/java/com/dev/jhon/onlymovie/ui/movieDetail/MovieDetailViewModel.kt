package com.dev.jhon.onlymovie.ui.movieDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.jhon.onlymovie.data.room.entity.Movie

class MovieDetailViewModel(application: Application) : ViewModel() {

    lateinit var movie: Movie

    /**
     * Fábrica para construir HomeViewModel con parámetro
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}