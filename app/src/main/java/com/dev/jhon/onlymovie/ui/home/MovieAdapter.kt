package com.dev.jhon.onlymovie.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dev.jhon.onlymovie.BuildConfig
import com.dev.jhon.onlymovie.R
import com.dev.jhon.onlymovie.data.room.entity.Movie

class MovieAdapter(private val context: Context, private val iMovieAdapter: IMovieAdapter) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    var positionItem = 0
    class MovieViewHolder(private val context: Context, view: View, private val iMovieAdapter: IMovieAdapter?) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val ivPoster: ImageView = view.findViewById(R.id.ivPoster)

        fun bindTo(movie: Movie?){
            if(movie == null)
                return
            tvTitle.text = movie.title

            if(movie.poster_path != null)
                Glide.with(context)
                    .load(BuildConfig.UrlImageTMDb + movie.poster_path)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_no_image_900x497)
                    .into(ivPoster)

            ivPoster.setOnClickListener{
                iMovieAdapter?.onClickItem(movie)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)
        return MovieViewHolder(context, view, iMovieAdapter)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie? = getItem(position)
        holder.bindTo(movie)
        positionItem = position
    }

    fun isNearingTheEnd() : Boolean{
        return (super.getItemCount() - positionItem) <= 5
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldMovie: Movie,
                                         newMovie: Movie
            ) = oldMovie.id == newMovie.id

            override fun areContentsTheSame(oldMovie: Movie,
                                            newMovie: Movie
            ) = oldMovie == newMovie
        }
    }

}