package com.expv1n.myfilmsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.expv1n.myfilmsapp.R
import com.expv1n.myfilmsapp.databinding.FragmentPopularMoviesBinding
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.presentation.adapter.MovieAdapter
import com.expv1n.myfilmsapp.presentation.state.PopularError
import com.expv1n.myfilmsapp.presentation.state.PopularProgress
import com.expv1n.myfilmsapp.presentation.state.PopularResult
import com.expv1n.myfilmsapp.presentation.viewmodel.PopularMoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment() {


    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding ?: throw RuntimeException("Unknown Binding")

    private val viewModel by lazy {
        ViewModelProvider(this)[PopularMoviesViewModel::class.java]
    }
    private val adapter by lazy {
        MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)
        setupAdapter()
        setOnClickListener()
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
            when(it) {
                is PopularError -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorImageView.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorRepeatButton.visibility = View.VISIBLE
                }
                is PopularProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is PopularResult -> {
                    binding.progressBar.visibility = View.GONE
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getMovies().collectLatest { movies ->
                            adapter.submitData(movies)
                        }
                    }
                }
            }
        }

    }
    private fun setOnClickListener() {
        adapter.onClickListener = {
            launchFragment(it)
        }
    }

    private fun launchFragment(film: Film) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainerView, DetailedInfoFragment.getInstance(film))
            .commit();
    }

    private fun setupAdapter() {
        binding.popularRecyclerView.adapter = adapter
    }

    companion object {

        const val FRAGMENT_NAME = "PopularMoviesFragment"
        fun getInstance() = PopularMoviesFragment()


    }
}