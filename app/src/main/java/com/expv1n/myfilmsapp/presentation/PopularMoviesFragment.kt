package com.expv1n.myfilmsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.expv1n.myfilmsapp.R
import com.expv1n.myfilmsapp.databinding.FragmentPopularMoviesBinding
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.presentation.adapter.MovieAdapter
import com.expv1n.myfilmsapp.presentation.viewmodel.PopularMoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PopularMoviesFragment : Fragment() {


    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding ?: throw RuntimeException("Unknown Binding")

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
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
        setOnClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        coroutineScope.launch {
            viewModel.getPopularMovies()
            viewModel.getPopularLiveData.observe(requireActivity()) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setOnClickListener() {
        adapter.onClickListener = {
            launchFragment(it)
        }
    }

    private fun launchFragment(film: Film) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainerView, DetailedInfoFragment.getInstance(film))
            .addToBackStack(DetailedInfoFragment.FRAGMENT_NAME)
            .commit();
    }

    private fun setupAdapter() {
        binding.popularRecyclerView.adapter = adapter
    }
}