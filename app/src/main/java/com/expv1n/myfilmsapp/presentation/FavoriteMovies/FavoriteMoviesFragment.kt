package com.expv1n.myfilmsapp.presentation.FavoriteMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.expv1n.myfilmsapp.R
import com.expv1n.myfilmsapp.databinding.FragmentFavoriteMoviesBinding
import com.expv1n.myfilmsapp.presentation.PopularMovies.MovieAdapter


class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding: FragmentFavoriteMoviesBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    private val adapter by lazy {
        FavoriteMoviesAdapter()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        setupAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupAdapter() {
        binding.popularRecyclerView.adapter = adapter
    }

    companion object {

        fun getInstance() = FavoriteMoviesFragment()

    }
}