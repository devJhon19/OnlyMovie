package com.dev.jhon.onlymovie.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.dev.jhon.onlymovie.data.repository.OnlyMovieRepository
import com.dev.jhon.onlymovie.data.room.AppRoomDatabase.Companion.getInstance
import com.dev.jhon.onlymovie.data.room.dao.MovieDao
import com.dev.jhon.onlymovie.data.room.entity.Movie
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : ViewModel() {

    private var page : Int = 1

    private val videosRepository = OnlyMovieRepository(getInstance(application))

    val movieList: LiveData<PagedList<Movie>> =
        videosRepository.movieById().toLiveData(pageSize = 20)

    fun refreshDataFromRepository() {
        page++
        viewModelScope.launch {
            videosRepository.refreshMovies(page)
        }
    }

    /**
     * Fábrica para construir HomeViewModel con parámetro
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}