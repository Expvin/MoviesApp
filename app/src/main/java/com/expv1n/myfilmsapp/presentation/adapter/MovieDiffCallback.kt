package com.expv1n.myfilmsapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.expv1n.myfilmsapp.domain.models.Film

class MovieDiffCallback(): DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}