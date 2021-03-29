package com.dev.jhon.onlymovie.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.jhon.onlymovie.R
import com.dev.jhon.onlymovie.data.room.entity.Movie
import com.dev.jhon.onlymovie.ui.BaseActivity
import com.dev.jhon.onlymovie.ui.movieDetail.MovieDetailActivity


class HomeActivity : BaseActivity(), IMovieAdapter{

    private lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this, HomeViewModel.Factory(application)).get(HomeViewModel::class.java)

        initViews()
    }

    private fun initViews(){
        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)
        val linearLayout = LinearLayoutManager(this@HomeActivity)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayout

        val adapter = MovieAdapter(this, this)

        viewModel.movieList.observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
        })

        recyclerView.adapter = adapter

        viewModel.refreshDataFromRepository()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val movieAdapter = recyclerView.adapter as MovieAdapter
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE && movieAdapter != null && movieAdapter.isNearingTheEnd()) {
                    viewModel.refreshDataFromRepository()
                }
            }
        })
    }

    /**
     * Se ejecuta al presionar un item
     */
    override fun onClickItem(movie: Movie) {
        val intent = Intent(this@HomeActivity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }


}