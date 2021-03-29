package com.dev.jhon.onlymovie.ui.home

import com.dev.jhon.onlymovie.data.room.entity.Movie

interface IMovieAdapter {
    fun onClickItem (movie: Movie)
}