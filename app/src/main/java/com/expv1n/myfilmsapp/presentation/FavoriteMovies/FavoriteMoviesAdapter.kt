package com.expv1n.myfilmsapp.presentation.FavoriteMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.databinding.MovieItemBinding
import com.expv1n.myfilmsapp.domain.models.MovieEntity


class FavoriteMoviesAdapter :
    ListAdapter<MovieEntity, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(FavoriteMoviesDiffCallback()) {

    var onClickListener: ((MovieEntity) -> Unit)? = null

    class FavoriteMoviesViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val binding = MovieItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FavoriteMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.binding.apply {
                Glide.with(holder.itemView).load(movie.posterUrl).into(titleImageView)
                titleTextView.text = movie.name
                val genre = movie.genre
                val year = genre + "(" + movie.year + ")"
                genreTextView.text = year
            }
            holder.itemView.apply {
                setOnClickListener { onClickListener?.invoke(movie) }
            }

        }
    }
}