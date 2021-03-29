package com.dev.jhon.onlymovie.ui.movieDetail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dev.jhon.onlymovie.BuildConfig
import com.dev.jhon.onlymovie.R
import com.dev.jhon.onlymovie.data.room.entity.Movie
import com.dev.jhon.onlymovie.domain.utils.Util
import com.dev.jhon.onlymovie.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    companion object{
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }


    private lateinit var viewModel : MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModel = ViewModelProvider(this, MovieDetailViewModel.Factory(application)).get(MovieDetailViewModel::class.java)

        if(intent != null && intent.extras != null){
            viewModel.movie = intent.extras!!.getSerializable(EXTRA_MOVIE) as Movie
        }else{
            finish()
        }

        initViews()
    }

    private fun initViews(){

        if(viewModel.movie.backdrop_path != null)
            ivBackDrop.let {
                Glide.with(this@MovieDetailActivity)
                    .load(BuildConfig.UrlImageTMDb + viewModel.movie.backdrop_path)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_no_image_900x497)
                    .into(ivBackDrop)
            }

        tvTitle?.text = viewModel.movie.title
        tvTitle?.movementMethod = ScrollingMovementMethod()
        rbMovie?.rating = if(viewModel.movie.vote_average != 0.0) ((viewModel.movie.vote_average / 2).toFloat()) else 0f
        tvReleaseDate?.text = Util.parseDateString(viewModel.movie.release_date)
        tvOverView?.text = viewModel.movie.overview

        ivBack.setOnClickListener{
            finish()
        }

    }
}