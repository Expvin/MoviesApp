package com.expv1n.myfilmsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.databinding.MovieItemBinding
import com.expv1n.myfilmsapp.domain.models.Film

class MovieAdapter: ListAdapter<Film, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    var onClickListener: ((Film) -> Unit)? = null
    class MovieViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val film = getItem(position)
        holder.binding.apply {
            Glide.with(holder.itemView).load(film.posterUrl).into(titleImageView)
            titleTextView.text = film.nameRu
            val genre = film.genres[0].genre
            val year = genre + "(" + film.year + ")"
            genreTextView.text = year
        }
        holder.itemView.apply {
            setOnClickListener { onClickListener?.invoke(film)}
        }

    }

}