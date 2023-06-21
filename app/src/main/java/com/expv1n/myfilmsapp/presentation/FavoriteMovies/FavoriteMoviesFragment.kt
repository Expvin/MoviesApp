package com.expv1n.myfilmsapp.presentation.FavoriteMovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.expv1n.myfilmsapp.databinding.FragmentFavoriteMoviesBinding
import com.expv1n.myfilmsapp.presentation.state.FavoriteError
import com.expv1n.myfilmsapp.presentation.state.FavoriteProgress
import com.expv1n.myfilmsapp.presentation.state.FavoriteResult
import kotlinx.coroutines.launch


class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding: FragmentFavoriteMoviesBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    private val adapter by lazy {
        FavoriteMoviesAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        setupAdapter()
        observeViewModel()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.getPopularMovies()
        viewModel.getPopularLiveData.observe(requireActivity()) {
            when (it) {
                is FavoriteError -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorImageView.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorRepeatButton.visibility = View.VISIBLE
                }

                is FavoriteProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is FavoriteResult -> {
                    binding.progressBar.visibility = View.GONE
                    viewLifecycleOwner.lifecycleScope.launch {
                        adapter.submitList(it.result)
                        Log.d("FavoriteMoviesViewModel", it.result.joinToString())
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.popularRecyclerView.adapter = adapter
    }

    companion object {

        fun getInstance() = FavoriteMoviesFragment()

    }
}